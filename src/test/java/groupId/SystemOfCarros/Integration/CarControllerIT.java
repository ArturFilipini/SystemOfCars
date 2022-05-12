package groupId.SystemOfCarros.Integration;

import groupId.SystemOfCarros.Carro.Carro;
import groupId.SystemOfCarros.Carro.CarUser;
import groupId.SystemOfCarros.Util.CarCreator;
import groupId.SystemOfCarros.repository.CarRepository;
import groupId.SystemOfCarros.repository.CarUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Qualifier(value = "testRestTemplateRoleAdmin")
class CarControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarUserRepository carUserRepository;
    private static final CarUser USER = CarUser.builder()
            .nome("Artur")
            .username("artur")
            .password("{bcrypt}$2a$10$IsKyLStC/KJqVlz9gYWmR.tTHvG/MgxRr2TjRlANcGr9eYDv.In4a")
            .authorities("ROLE_ADMIN")
            .build();

    @TestConfiguration
    @Lazy
    static class Config{
        @Bean(name = "testRestTemplateRoleAdmin")
        public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}")int port){
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:"+ port)
                    .basicAuthentication("artur", "ferrari");
            return new TestRestTemplate(restTemplateBuilder);
        }
    }
    @Test
    void list_ReturnsListOfCarros_WhenSucessful() throws Exception {
        carUserRepository.save(USER);
        Carro savedCarro = carRepository.save(CarCreator.createCarroToBeSaved());

       String expectedTipo = savedCarro.getTipo();

       List<Carro> carros = testRestTemplate.exchange("/Carro", HttpMethod.GET, null,
               new ParameterizedTypeReference<List<Carro>>(){}
               ).getBody();

        Assertions.assertThat(carros)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(carros.get(0).getTipo()).isEqualTo(expectedTipo);
    }
    @Test
    void DeleteCarro_AndReturnsVoid_WhenSucessful(){
        carUserRepository.save(USER);
        Carro carro = CarCreator.createCarroToBeSaved();

        Carro save = carRepository.save(carro);
        Long saveId = save.getId();
        ResponseEntity<Void> carrosdeleted = testRestTemplate.exchange("/Carro/delete/{id}", HttpMethod.DELETE,null,Void.class, saveId);

        Assertions.assertThat(carrosdeleted).isNotNull();
        Assertions.assertThat(carrosdeleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    void findByTipo_ReturnsListOfCarro_WhenFound(){
        carUserRepository.save(USER);
        Carro carro = CarCreator.createCarroToBeSaved();

        Carro save = carRepository.save(carro);
        String expectedTipo = save.getTipo();
        Carro[] carros = testRestTemplate.getForObject("/Carro/find/{tipo}", Carro[].class, expectedTipo
        );

        Assertions.assertThat(carros)
                .isNotNull()
                ;
        Assertions.assertThat(carros[0].getTipo()).isEqualTo(expectedTipo);
    }
    @Test
    void findById_ReturnsCarro_WhenFound(){
        carUserRepository.save(USER);
        Carro carro = CarCreator.createCarroToBeSaved();

        Carro save = carRepository.save(carro);
        Long expectedId = save.getId();
        Carro[] carros = testRestTemplate.getForObject("/Carro/{id}", Carro[].class, expectedId
        );

        Assertions.assertThat(carros)
                .isNotNull()
        ;
        Assertions.assertThat(carros[0].getId()).isEqualTo(expectedId);
    }
    @Test
    void save_CreateCarro_WhenSuccessful(){
        carUserRepository.save(USER);
        Carro savedCarro = CarCreator.createCarroToBeSaved();

        Carro saved = carRepository.save(savedCarro);


        ResponseEntity<Void> carro = testRestTemplate.exchange("/Carro/save",
                HttpMethod.POST,new HttpEntity<>(savedCarro), Void.class);

        Assertions.assertThat(carro).isNotNull();


        Assertions.assertThat(carro.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    @Test
    void replace_UpdatesCarro_WhenSuccessful(){
        carUserRepository.save(USER);
        Carro savedCarro = carRepository.save(CarCreator.createCarroToBeSaved());

        savedCarro.setTipo("Truck");

        ResponseEntity<Void> carro = testRestTemplate.exchange("/Carro",
                HttpMethod.PUT,new HttpEntity<>(savedCarro), Void.class);

        Assertions.assertThat(carro).isNotNull();

        Assertions.assertThat(carro.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}