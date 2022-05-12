package groupId.SystemOfCarros.Util;

import groupId.SystemOfCarros.Carro.Carro;

public class CarCreator {
    public static Carro createCarroToBeSaved(){
        return Carro.builder().tipo("Veiculo").marca("Honda")
                .modelo("Sedan").ano(1998L).price(500.000).build();
    }
    public static Carro createCarroToBeUpdate(){
        return Carro.builder().Id(2L).tipo("Veiculo").marca("Honda")
                .modelo("Sedan").ano(1998L).price(500.000).build();
    }
    public static Carro createCarroToBeUpdateValid(){
        return Carro.builder().Id(2L).tipo("Truck").marca("Ferrari")
                .modelo("Sedan").ano(1998L).price(500.000).build();
    }

}
