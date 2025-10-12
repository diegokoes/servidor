package es.daw.jakarta.util;

import es.daw.jakarta.model.Fabricante;

import java.util.List;

public class Utils {
    public static String obtenerNombreFabricante(List<Fabricante> fabricantes,Integer codigo) {

        return fabricantes.stream()
                .filter(f -> f.getCodigo().equals(codigo))
                .findFirst()
                .map(Fabricante::getNombre)
                .orElse("Fabricante no encontrado");

    }
}
