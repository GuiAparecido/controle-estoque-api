package br.com.guilherme.estoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="estoque")

public class Estoque {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    private BigDecimal preco;
    private long quantidade;

}
