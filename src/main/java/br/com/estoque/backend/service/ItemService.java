package br.com.estoque.backend.service;

import br.com.estoque.backend.dto.request.ItemDTO;
import br.com.estoque.backend.exception.*;
import br.com.estoque.backend.mapper.ArmazemMapper;
import br.com.estoque.backend.mapper.ItemMapper;
import br.com.estoque.backend.models.Armazem;
import br.com.estoque.backend.models.Item;
import br.com.estoque.backend.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemService {

    private ItemRepository itemRepository;
    private ArmazemService armazemService;

    private final ItemMapper itemMapper = ItemMapper.INSTANCE;
    private final ArmazemMapper armazemMapper = ArmazemMapper.INSTANCE;

    public Long criarItem(Long id, ItemDTO itemDTO) throws ArmazemNaoEncontradoException {
        Item itemToSave = itemMapper.toModel(itemDTO);
        itemToSave.setArmazem(armazemMapper.toModel(armazemService.encontrarPorID(id)));

        if(!checarQuantidade(itemToSave)) {
            throw new QuantidadeNegativaException();
        }
        if(!checarEspaco(itemToSave.getArmazem())){
            throw new QuantidadeSuperaCapacidadeException(itemToSave.getArmazem().getQuantidadeMax());
        }

        Item savedItem = itemRepository.save(itemToSave);
        return savedItem.getId();
    }

    public Long adicionarQuantidade(Long id, float quantidade) throws ItemNaoEncontradoException, QuantidadeSuperaCapacidadeException {
        Item item = verificarSeExiste(id);
        Armazem armazem = item.getArmazem();
        item.getArmazem().setQuantidadeSobrando(armazem.getQuantidadeSobrando() - quantidade);
        System.out.println(item.getArmazem().getQuantidadeSobrando());
        item.setQuantidade(item.getQuantidade() + quantidade);

        if(!checarQuantidade(item)) {
            throw new QuantidadeNegativaException();
        }
        if(!checarEspaco(armazem)){
            throw new QuantidadeSuperaCapacidadeException(armazem.getQuantidadeMax());
        }
        Item updatedItem = itemRepository.save(item);
        return updatedItem.getId();
    }

    public Long diminuirQuantidade(Long id, float quantidade) throws ItemNaoEncontradoException, QuantidadeSuperaCapacidadeException {
        Item item = verificarSeExiste(id);
        Armazem armazem = item.getArmazem();
        item.getArmazem().setQuantidadeSobrando(armazem.getQuantidadeSobrando() + quantidade);
        item.setQuantidade(item.getQuantidade() - quantidade);

        if(!checarQuantidade(item)) {
            throw new QuantidadeNegativaException();
        }
        Item updatedItem = itemRepository.save(item);
        return updatedItem.getId();
    }


    public List<ItemDTO> listarTodos() {
        List<Item> todosOsItens = itemRepository.findAll();
        if(todosOsItens.isEmpty()){
            throw new NenhumItemEncontradoEncontradoException();
        }
        return todosOsItens.stream()
                .map(itemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO encontrarPorID(Long id) throws ItemNaoEncontradoException {
        Item item = verificarSeExiste(id);
        return itemMapper.toDTO(item);
    }

    public Long deletar(Long id) throws ItemNaoEncontradoException {
        verificarSeExiste(id);
        itemRepository.deleteById(id);
        return id;
    }

    public Long atualizarPorId(Long id, ItemDTO itemDTO) throws ItemNaoEncontradoException {
        Armazem armazem = verificarSeExiste(id).getArmazem();
        Item itemToUpdate = itemMapper.toModel(itemDTO);
        itemToUpdate.setArmazem(armazem);
        itemToUpdate.setId(id);
        Item updatedItem = itemRepository.save(itemToUpdate);
        return updatedItem.getId();
    }

    private boolean checarEspaco(Armazem armazem)  {

        return armazem.getQuantidadeSobrando() <= armazem.getQuantidadeMax() && armazem.getQuantidadeSobrando() >= 0;
    }

    private boolean checarQuantidade(Item item)  {
        return item.getQuantidade() > 0;
    }

    private Item verificarSeExiste(Long id) throws ItemNaoEncontradoException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNaoEncontradoException(id));
    }


}
