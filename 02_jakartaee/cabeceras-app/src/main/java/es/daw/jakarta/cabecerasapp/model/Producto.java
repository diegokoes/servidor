package es.daw.jakarta.cabecerasapp.model;

import java.util.Objects;

public class Producto implements Comparable<Producto> {


    private Long id;
    private String nombre;
    private float precio;
    private String tipo;


    public Producto(Long id, String nombre, String tipo, float precio ) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Producto() {
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre='" + nombre + '\'' + ", precio=" + precio + ", tipo='" + tipo + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Producto o) {
        return 0;
    }
}
