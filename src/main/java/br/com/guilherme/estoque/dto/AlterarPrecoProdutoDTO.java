package br.com.guilherme.estoque.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AlterarPrecoProdutoDTO {

    @NotNull
    @DecimalMin(value = "9.90", message = "O preço não pode ser menor que R$ 9,90")
    private BigDecimal preco;

}
