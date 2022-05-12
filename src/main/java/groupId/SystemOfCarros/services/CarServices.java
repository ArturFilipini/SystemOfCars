package groupId.SystemOfCarros.services;


import groupId.SystemOfCarros.Carro.Carro;
import groupId.SystemOfCarros.Mapper.CarroMapper;
import groupId.SystemOfCarros.Requests.CarroPutRequestBody;
import groupId.SystemOfCarros.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServices {
    private final CarRepository carRepository;

    public CarServices(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Carro> findByTipo(String Tipo) {
        return carRepository.findByTipo(Tipo);
    }

    public List<Carro> listall(){
        return carRepository.findAll();
    }

    public Carro saveCarro(Carro carro) {
        return carRepository.save(carro);
    }

    public void deleteCarro(Long id) {
        boolean exists = carRepository.existsById(id);
        if(!exists){
            throw new IllegalArgumentException("Carro não existe");
        }
        carRepository.deleteById(id);
    }



    public List<Carro> findById(Long id) {
        Optional<Carro> carro = carRepository.findById(id);
        List<Carro> carroList = carro.stream().collect(Collectors.toList());
        if(carroList.isEmpty()){
            throw new IllegalArgumentException("Não existe esse Id");
        }
        return carroList;
    }

    public void replace(CarroPutRequestBody carroPutRequestBody){
        Optional<Carro> carro = carRepository.findById(carroPutRequestBody.getId());
        if(!carro.isPresent()){
            throw new IllegalArgumentException("Não tem um Carro com esse Id");
        }
        Carro carro1 = CarroMapper.INSTANCE.toCarro(carroPutRequestBody);
        carro1.setId(carroPutRequestBody.getId());
        carRepository.save(carro1);
        return;

    }


}
