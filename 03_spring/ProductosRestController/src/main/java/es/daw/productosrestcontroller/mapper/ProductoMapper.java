package es.daw.productosrestcontroller.mapper;

import es.daw.productosrestcontroller.dto.ProductoDTO;
import es.daw.productosrestcontroller.entities.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDTO toProductoDTO(Producto producto);

    Producto toEntity(ProductoDTO productoDTO);

    List<ProductoDTO> toProductoDTOList(List<Producto> productoList);

    List<Producto> toEntityList(List<ProductoDTO> productoDTOList);
}
