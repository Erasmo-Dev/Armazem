package br.com.estoque.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemJaCadastradoException extends RuntimeException {

    public ItemJaCadastradoException(String nome) {
        super("Item com o nome " + nome + "já cadastrado");
    }
}
