package es.daw.productosrestcontroller.repository;

import es.daw.productosrestcontroller.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    Producto findByCodigo(String codigo);
}
