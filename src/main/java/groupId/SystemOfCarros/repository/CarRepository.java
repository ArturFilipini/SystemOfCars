package groupId.SystemOfCarros.repository;

import groupId.SystemOfCarros.Carro.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByTipo(String tipo);

}
