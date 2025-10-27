package es.daw.productosrestcontroller.service;

import es.daw.productosrestcontroller.dto.ProductoDTO;
import es.daw.productosrestcontroller.entities.Fabricante;
import es.daw.productosrestcontroller.entities.Producto;
import es.daw.productosrestcontroller.mapper.ProductoMapper;
import es.daw.productosrestcontroller.repository.FabricanteRepository;
import es.daw.productosrestcontroller.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final FabricanteRepository fabricanteRepository;
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;


    public Optional<List<ProductoDTO>> findAll() {
        List<Producto> productosEntities = productoRepository.findAll();
        return Optional.ofNullable(productoMapper.toProductoDTOList(productosEntities));
    }

    public Optional<ProductoDTO> findByCodigo(String codigo) {
        Producto productoEntity = productoRepository.findByCodigo(codigo);
        return Optional.ofNullable(productoMapper.toProductoDTO(productoEntity));
    }

    public Optional<ProductoDTO> save(ProductoDTO productoDTO) {
        Producto productoEntity = productoMapper.toEntity(productoDTO);
        Optional<Fabricante> fabOpt = fabricanteRepository.findById(1);
        if (!fabOpt.isPresent()) {
            return Optional.empty();
        }
        productoEntity.setFabricante(fabOpt.get());
        productoRepository.save(productoEntity);
        return Optional.ofNullable(productoMapper.toProductoDTO(productoEntity));
    }

    public boolean delete(String codigo) {
        Optional<Producto> productoEntity =
                Optional.ofNullable(productoRepository.findByCodigo(codigo));

        if (productoEntity.isPresent()) {
            productoRepository.deleteByCodigo(codigo);
            return true;
        } else {
            return false;
        }

    }


}
