package es.daw.productosrestcontroller.repository;

import es.daw.productosrestcontroller.entities.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {

}
