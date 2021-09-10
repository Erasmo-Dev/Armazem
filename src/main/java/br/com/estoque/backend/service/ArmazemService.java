package br.com.estoque.backend.service;


import br.com.estoque.backend.dto.request.ArmazemDTO;
import br.com.estoque.backend.exception.ArmazemNaoEncontradoException;
import br.com.estoque.backend.exception.NenhumArmazemEncontradoException;
import br.com.estoque.backend.exception.QuantidadeNegativaException;
import br.com.estoque.backend.mapper.ArmazemMapper;
import br.com.estoque.backend.models.Armazem;
import br.com.estoque.backend.models.Item;
import br.com.estoque.backend.repository.ArmazemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ArmazemService {

    private ArmazemRepository armazemRepository;
    private final ArmazemMapper armazemMapper = ArmazemMapper.INSTANCE;

    public Long criarArmazem(ArmazemDTO armazemDTO) {
        armazemDTO.setQuantidadeSobrando(armazemDTO.getQuantidadeMax());
        Armazem armazemToSave = armazemMapper.toModel(armazemDTO);
        List<Item> itens = armazemToSave.getItems();
        itens.forEach(item -> item.setArmazem(armazemToSave));
        armazemToSave.setItems(itens);
        Armazem savedArmazem = armazemRepository.save(armazemToSave);
        return savedArmazem.getId();
    }

    public List<ArmazemDTO> listarTodos() {
        List<Armazem> todosOsArmazens = armazemRepository.findAll();
        if(todosOsArmazens.isEmpty()){
            throw new NenhumArmazemEncontradoException();
        }
        return todosOsArmazens.stream()
                .map(armazemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ArmazemDTO encontrarPorID(Long id) throws ArmazemNaoEncontradoException {
        Armazem armazem = verificarSeExiste(id);

        return armazemMapper.toDTO(armazem);
    }

    public Long deletar(Long id) throws ArmazemNaoEncontradoException {
        verificarSeExiste(id);
        armazemRepository.deleteById(id);
        return id;
    }

    public Long atualizarPorId(Long id, ArmazemDTO armazemDTO) throws ArmazemNaoEncontradoException, QuantidadeNegativaException {
        verificarSeExiste(id);
        Armazem armazemAtualizar = armazemMapper.toModel(armazemDTO);

        if(!checarEspaco(armazemAtualizar)){
            throw new QuantidadeNegativaException();
        }

        Armazem updatedPerson = armazemRepository.save(armazemAtualizar);
        return updatedPerson.getId();
    }

    private boolean checarEspaco(Armazem armazem)  {
        return armazem.getQuantidadeSobrando() <= armazem.getQuantidadeMax();
    }

    private Armazem verificarSeExiste(Long id) throws ArmazemNaoEncontradoException {
        return armazemRepository.findById(id)
                .orElseThrow(() -> new ArmazemNaoEncontradoException(id));
    }


}
