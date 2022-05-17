package groupId.SystemOfCarros.Carro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nomeImagem;
    @NotEmpty(message = "The Car Type cannot be empty")
    private String tipo;
    @NotEmpty(message = "The Car Marca cannot be empty")
    private String marca;
    @NotEmpty(message = "The Car Model cannot be empty")
    private String modelo;
    private Long ano;
    private Double price;


}
