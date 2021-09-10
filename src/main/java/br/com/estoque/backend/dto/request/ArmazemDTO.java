package br.com.estoque.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArmazemDTO {

    private Long id;

    @NotNull
    private float quantidadeMax;

    @NotNull
    private float quantidadeSobrando;

    @Valid
    private List<ItemDTO> items;

}
