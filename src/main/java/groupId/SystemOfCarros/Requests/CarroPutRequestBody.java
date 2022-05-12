package groupId.SystemOfCarros.Requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarroPutRequestBody {
    private Long id;
    private Long ano;
    private String marca;
    private String modelo;
    private String tipo;
    private Double price;
}
