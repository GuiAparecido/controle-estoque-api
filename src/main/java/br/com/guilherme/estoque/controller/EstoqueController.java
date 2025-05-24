package br.com.guilherme.estoque.controller;

import br.com.guilherme.estoque.dto.AlterarPrecoProdutoDTO;
import br.com.guilherme.estoque.dto.AlterarQuantidadeProdutoDTO;
import br.com.guilherme.estoque.dto.CadastroEstoqueDTO;
import br.com.guilherme.estoque.dto.RetornoEstoqueDTO;
import br.com.guilherme.estoque.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estoque")

public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity cadastrarProduto(@Valid @RequestBody CadastroEstoqueDTO cadastroEstoqueDTO){
        estoqueService.cadastrarProduto(cadastroEstoqueDTO);
        return ResponseEntity.created(URI.create("/estoque")).build();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public ResponseEntity<List <RetornoEstoqueDTO>> listarProduto(){
        return ResponseEntity.ok( estoqueService.listarProduto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable Long id){
        estoqueService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/preco")
    public ResponseEntity alterarPrecoProdudo(@PathVariable Long id, @Valid @RequestBody AlterarPrecoProdutoDTO alterarPrecoProdutoDTO){
        estoqueService.alterarPrecoProduto(id, alterarPrecoProdutoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/quantidade")
    public ResponseEntity alterarQuantidadeProdudo(@PathVariable Long id, @Valid @RequestBody AlterarQuantidadeProdutoDTO alterarQuantidadeProdutoDTO){
        estoqueService.alterarQuantidadeProduto(id, alterarQuantidadeProdutoDTO);
        return ResponseEntity.ok().build();
    }



}
