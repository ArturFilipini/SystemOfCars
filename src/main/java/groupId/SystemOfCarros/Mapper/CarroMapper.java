package groupId.SystemOfCarros.Mapper;

import groupId.SystemOfCarros.Carro.Carro;
import groupId.SystemOfCarros.Requests.CarroPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CarroMapper {
    public static final CarroMapper INSTANCE = Mappers.getMapper(CarroMapper.class);
    public abstract Carro toCarro(CarroPutRequestBody carroPutRequestBody);


}
