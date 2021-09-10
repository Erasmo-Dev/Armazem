package br.com.estoque.backend.controller;

import br.com.estoque.backend.dto.request.ArmazemDTO;
import br.com.estoque.backend.service.ArmazemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/armazem")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ArmazemController {

    private ArmazemService armazemService;

    @PostMapping
    public ResponseEntity<Long> criarArmazem(@RequestBody @Valid ArmazemDTO armazemDTO) {
        return ResponseEntity.ok().body(armazemService.criarArmazem(armazemDTO));
    }

    @GetMapping
    public ResponseEntity<List<ArmazemDTO>> listarTodos() {
        return ResponseEntity.ok().body(armazemService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArmazemDTO> encontrarPorId(@PathVariable Long id)  {
        return ResponseEntity.ok().body(armazemService.encontrarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizarPorId(@PathVariable Long id, @RequestBody @Valid ArmazemDTO armazemDTO) {
        return ResponseEntity.ok().body(armazemService.atualizarPorId(id, armazemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletarPorID(@PathVariable Long id) {
        return ResponseEntity.ok().body(armazemService.deletar(id));
    }
}
