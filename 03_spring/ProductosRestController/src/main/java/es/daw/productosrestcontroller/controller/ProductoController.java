package es.daw.productosrestcontroller.controller;

import es.daw.productosrestcontroller.dto.ProductoDTO;
import es.daw.productosrestcontroller.mapper.ProductoMapper;
import es.daw.productosrestcontroller.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor // propiedades final
@Validated
// necesita @Validated??  TODO

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
    public ResponseEntity<ProductoDTO> save(@Valid @RequestBody ProductoDTO productoDTO) {
        return productoService.save(productoDTO)
                .map(productoDTO1 -> ResponseEntity.status(HttpStatus.CREATED).body(productoDTO1))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/parse-int")
    public String parseInteger(@RequestParam(name = "numero", defaultValue = "666") String number) {
        int parsedNumber = Integer.parseInt(number);
        return "Parsed number: " + parsedNumber;
    }
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@Pattern (regexp = "^\\d{3}[A-Z]$",
            message = "The pattern must have 3 digits followed by a letter " +
                    "(CAPS)") @PathVariable String codigo) {
        return productoService.delete(codigo)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();


    }

}
