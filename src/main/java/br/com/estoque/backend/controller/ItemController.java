package br.com.estoque.backend.controller;

import br.com.estoque.backend.dto.request.ItemDTO;
import br.com.estoque.backend.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {

    private ItemService itemService;

    @PostMapping("/{id}")
    public ResponseEntity<Long> criarItem(@PathVariable Long id, @RequestBody @Valid ItemDTO itemDTO) {
        return ResponseEntity.ok().body(itemService.criarItem(id, itemDTO));
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> listarTodos() {
        return ResponseEntity.ok().body(itemService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> encontrarPorId(@PathVariable Long id)  {
        return ResponseEntity.ok().body(itemService.encontrarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizarPorId(@PathVariable Long id, @RequestBody @Valid ItemDTO itemDTO) {
        return ResponseEntity.ok().body(itemService.atualizarPorId(id, itemDTO));
    }

    @PutMapping("/adicionar/{id}")
    public ResponseEntity<Long> adicionarPorId(@PathVariable Long id, @RequestParam float quantidade)  {
        return ResponseEntity.ok().body(itemService.adicionarQuantidade(id, quantidade));
    }

    @PutMapping("/diminuir/{id}")
    public ResponseEntity<Long> diminuirPorId(@PathVariable Long id, @PathVariable float quantidade)  {
        return ResponseEntity.ok().body(itemService.diminuirQuantidade(id, quantidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletarPorID(@PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.deletar(id));
    }
}
