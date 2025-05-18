package br.com.guilherme.estoque.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RetornoEstoqueDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private BigDecimal preco;
    private long quantidade;

}
