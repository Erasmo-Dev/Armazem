package br.com.estoque.backend.mapper;

import br.com.estoque.backend.dto.request.ArmazemDTO;
import br.com.estoque.backend.models.Armazem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArmazemMapper {

    ArmazemMapper INSTANCE = Mappers.getMapper(ArmazemMapper.class);

    Armazem toModel(ArmazemDTO armazemDTO);

    ArmazemDTO toDTO(Armazem armazem);

}
