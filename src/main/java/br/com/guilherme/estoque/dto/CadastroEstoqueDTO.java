package br.com.guilherme.estoque.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CadastroEstoqueDTO {
    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin(value = "9.90", message = "O preço não pode ser menor que R$ 9,90")
    private BigDecimal preco;
    @NotNull
    @Min(value = 1l, message = "A quantidade minima deve ser 1")
    private Long quantidade;
}
