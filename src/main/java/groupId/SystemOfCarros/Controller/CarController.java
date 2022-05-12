package groupId.SystemOfCarros.Controller;

import groupId.SystemOfCarros.Carro.Carro;
import groupId.SystemOfCarros.Requests.CarroPutRequestBody;
import groupId.SystemOfCarros.services.CarServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("Carro")
public class CarController {
   private final CarServices carServices;


    public CarController(CarServices carServices) {
        this.carServices = carServices;
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Carro> list(){
        return carServices.listall();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Carro>> findById(@PathVariable("id") Long id){
       return ResponseEntity.ok(carServices.findById(id));
    }
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carro> registerNewCarro(@RequestBody Carro carro){
        return new ResponseEntity<>(carServices.saveCarro(carro), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carro> deleteCarro(@PathVariable("id") Long id){
        carServices.deleteCarro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> replace(@RequestBody CarroPutRequestBody carroPutRequestBody){
        carServices.replace(carroPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/find/{Tipo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Carro>> findByName(@PathVariable("Tipo") String Tipo){
        return ResponseEntity.ok(carServices.findByTipo(Tipo));
    }

}
