package br.com.estoque.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArmazemNaoEncontradoException extends RuntimeException {

    public ArmazemNaoEncontradoException(Long id) {
        super("Armazem com o ID " + id + "Nao encontrado");
    }
}
