package es.daw.jakarta.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Producto implements Serializable, Comparable<Producto>{

    private Integer codigo;
    private String nombre;
    private BigDecimal precio;
    private Integer codigo_fabricante;

    public Producto() {
    }

    public Producto(Integer codigo, String nombre, BigDecimal precio, Integer codigo_fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codigo_fabricante = codigo_fabricante;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(Integer codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", codigo_fabricante=" + codigo_fabricante +
                '}';
    }

    @Override
    public int compareTo(Producto o) {
        return 0;
    }
}
