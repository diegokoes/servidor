package es.daw.productosrestcontroller.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, length = 255)
    private String nombre;

    @OneToMany(mappedBy = "fabricante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Producto> productos;

}
