package es.daw.jakarta.cabecerasapp.service;

import es.daw.jakarta.cabecerasapp.model.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {

    private static List<Producto> productos = new ArrayList<Producto>();

    @Override
    public List<Producto> findAll() {
        return Arrays.asList(new Producto(1L, "notebook", "informática",
                        175000.99F),
                new Producto(2L, "mesa escritorio", "oficina", 10000000.25F),
                new Producto(3L, "teclado", "informatica", 400000.5F));
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return null;
    }

    @Override
    public Producto findByName(String nombre) {
        return null;
    }
}
