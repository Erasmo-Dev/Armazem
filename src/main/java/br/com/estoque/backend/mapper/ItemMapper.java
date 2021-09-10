package br.com.estoque.backend.mapper;

import br.com.estoque.backend.dto.request.ItemDTO;
import br.com.estoque.backend.models.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item toModel(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);

}
