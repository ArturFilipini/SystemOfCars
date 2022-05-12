package groupId.SystemOfCarros.repository;

import groupId.SystemOfCarros.Carro.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarUserRepository extends JpaRepository<CarUser, Long> {


    CarUser findByUsername(String username);
}
