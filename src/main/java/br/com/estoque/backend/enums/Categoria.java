package br.com.estoque.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Categoria {

    PERECIVEIS("Alimento com baixo prazo de validade"),
    NAOPERECIVEIS("Alimento com alto prazo de validade"),
    MEDICAMENTOS("Items para cuidados com a saude"),
    MUNICAO("Cartuchos de arma de fogo ou caca(Arco e flexa)"),
    HIGIENEINTIMA("Items para cuidar da higiene pessoal (Papel higienico, pasta de dente ETC) "),
    AGUA("Agua propria ou impropria para o consumo");

    private final String descricao;

}
