package br.com.guilherme.estoque.exception;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
