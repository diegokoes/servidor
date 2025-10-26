package es.daw.productosrestcontroller.controller;

import es.daw.productosrestcontroller.dto.ProductoDTO;
import es.daw.productosrestcontroller.mapper.ProductoMapper;
import es.daw.productosrestcontroller.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor // propiedades final
public class ProductoController {
    private final ProductoService productoService;
    private final ProductoMapper productoMapper;


    @GetMapping
    public ResponseEntity<Optional<List<ProductoDTO>>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProductoDTO> findByCodigo(@PathVariable String codigo) {

        return ResponseEntity.of(productoService.findByCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@RequestBody ProductoDTO productoDTO) {
        return productoService.save(productoDTO)
                .map(productoDTO1 -> ResponseEntity.status(HttpStatus.CREATED).body(productoDTO1))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

}
