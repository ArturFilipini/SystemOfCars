package groupId.SystemOfCarros.Controller;

import groupId.SystemOfCarros.Carro.Carro;
import groupId.SystemOfCarros.Util.CarCreator;
import groupId.SystemOfCarros.repository.CarRepository;
import groupId.SystemOfCarros.services.CarServices;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Test for Carro Repository")
@Log4j2
class TestUnitarioCarController {
        @Autowired
        private CarRepository carRepository;
        @Mock
        private CarServices carServices;

        @Test
        void Save_Carro_And_Returns_Void(){
                Carro carro = CarCreator.createCarroToBeSaved();
                Carro carroSaved = this.carRepository.save(carro);
                Assertions.assertThat(carroSaved).isNotNull();
                Assertions.assertThat(carroSaved.getId()).isNotNull();
                Assertions.assertThat(carroSaved.getTipo()).isNotNull();

                Assertions.assertThat(carroSaved.getTipo()).isEqualTo(carro.getTipo());
                Assertions.assertThat(carroSaved.getModelo()).isEqualTo(carro.getModelo());
                Assertions.assertThat(carroSaved.getMarca()).isEqualTo(carro.getMarca());
                Assertions.assertThat(carroSaved.getAno()).isEqualTo(carro.getAno());
                Assertions.assertThat(carroSaved.getPrice()).isEqualTo(carro.getPrice());

        }
        @Test
        void FindCarroByTipo_AndReturnCarro_WhenSucessful(){
                Carro carro = CarCreator.createCarroToBeUpdate();
                Carro carrosaved = this.carRepository.save(carro);
                List<Carro> byTipo = this.carRepository.findByTipo(carro.getTipo());
                Assertions.assertThat(byTipo).contains(carrosaved);
                Assertions.assertThat(byTipo).isNotEmpty();
                Assertions.assertThat(byTipo).isNotNull();
        }

        @Test
        void DeleteCarro_AndReturnsVoid_WhenSucessful(){
                Carro carro = CarCreator.createCarroToBeSaved();
                Carro save = this.carRepository.save(carro);
                this.carRepository.delete(save);
                Optional<Carro> CarroOptional = this.carRepository.findById(save.getId());
                Assertions.assertThat(CarroOptional).isEmpty();
        }
        @Test
        void Save_UpdatesCarro_WhenSucessful(){
                Carro carro = CarCreator.createCarroToBeSaved();
                Carro save = this.carRepository.save(carro);
                save.setTipo("Artur");
                Carro CarroUpdate = this.carRepository.save(save);

                Assertions.assertThat(CarroUpdate).isNotNull();
                Assertions.assertThat(CarroUpdate.getId()).isNotNull();
                Assertions.assertThat(CarroUpdate.getTipo()).isEqualTo(save.getTipo());
        }

}