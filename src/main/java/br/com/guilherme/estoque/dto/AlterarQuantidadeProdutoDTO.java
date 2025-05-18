package br.com.guilherme.estoque.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlterarQuantidadeProdutoDTO {

    @NotNull
    @Min(value = 1l, message = "A quantidade minima deve ser 1")
    private Long quantidade;
}
