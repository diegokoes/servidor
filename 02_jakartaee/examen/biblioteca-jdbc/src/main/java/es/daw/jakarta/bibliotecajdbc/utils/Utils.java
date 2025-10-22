package es.daw.jakarta.bibliotecajdbc.utils;


import es.daw.jakarta.bibliotecajdbc.model.Autor;
import es.daw.jakarta.bibliotecajdbc.model.Libro;

import java.math.BigInteger;
import java.util.List;

public class Utils {
    public static String obtenerNombreAutor(List<Autor> autores, BigInteger id){
        return autores.stream()
                .filter(a -> a.getId_autor().equals(id))
                .findFirst()
                .map(Autor::getNombre)
                .orElse("Desconocido");
    }
    public static List<Libro> obtenerLibrosAutor(List<Libro> libros, BigInteger id){
        return libros.stream()
                .filter(l -> l.getId_autor().equals(id))
                .sorted((l1, l2) -> l1.getTitulo().compareTo(l2.getTitulo()))
                .toList();
    }
}