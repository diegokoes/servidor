package es.daw.productosrestcontroller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {
    private String nombre;
    private BigDecimal precio;
    private String codigo;
    //private Integer codifoFabricante;
}
