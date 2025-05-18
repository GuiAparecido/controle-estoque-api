package br.com.guilherme.estoque.service;

import br.com.guilherme.estoque.dto.AlterarPrecoProdutoDTO;
import br.com.guilherme.estoque.dto.AlterarQuantidadeProdutoDTO;
import br.com.guilherme.estoque.dto.CadastroEstoqueDTO;
import br.com.guilherme.estoque.dto.RetornoEstoqueDTO;
import br.com.guilherme.estoque.exception.ProdutoNaoEncontradoException;
import br.com.guilherme.estoque.model.Estoque;
import br.com.guilherme.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public void cadastrarProduto(CadastroEstoqueDTO cadastroEstoqueDTO){
        Estoque estoque = new Estoque();
        estoque.setNome(cadastroEstoqueDTO.getNome());
        estoque.setPreco(cadastroEstoqueDTO.getPreco());
        estoque.setQuantidade(cadastroEstoqueDTO.getQuantidade());
        LocalDateTime agora = LocalDateTime.now();
        estoque.setDataCriacao(agora);

        estoqueRepository.save(estoque);
    }

    public List<RetornoEstoqueDTO> listarProduto() {
        return estoqueRepository.findAll().stream().map(estoque -> {
            RetornoEstoqueDTO retorno = new RetornoEstoqueDTO();
            retorno.setNome(estoque.getNome());
            retorno.setPreco(estoque.getPreco());
            retorno.setQuantidade(estoque.getQuantidade());
            retorno.setId(estoque.getId());
            retorno.setDataCriacao(estoque.getDataCriacao());
            retorno.setDataAtualizacao(estoque.getDataAtualizacao());
            return retorno;
        }).toList();
    }

    public void deletarProduto(Long id) {
        estoqueRepository.deleteById(id);
    }

    public void alterarPrecoProduto(Long id, AlterarPrecoProdutoDTO alterarPrecoProdutoDTO) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow(()-> new ProdutoNaoEncontradoException("Produto não encontrado para o id informado"));
        estoque.setPreco(alterarPrecoProdutoDTO.getPreco());
        LocalDateTime agora = LocalDateTime.now();
        estoque.setDataAtualizacao(agora);

        estoqueRepository.save(estoque);
    }

    public void alterarQuantidadeProduto(Long id, AlterarQuantidadeProdutoDTO alterarQuantidadeProdutoDTO) {
        Estoque estoque = estoqueRepository.findById(id).orElseThrow(()-> new ProdutoNaoEncontradoException("Produto não encontrado para o id informado"));
        estoque.setQuantidade(alterarQuantidadeProdutoDTO.getQuantidade());
        LocalDateTime agora = LocalDateTime.now();
        estoque.setDataAtualizacao(agora);

        estoqueRepository.save(estoque);
    }

}
