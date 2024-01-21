package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.repository.ProdutoRepositoryold;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepositoryold produtorepository;

    public List<Produto>obtertodosprodutos(){
        return produtorepository.obterTodos();
    }

    public Optional<Produto> obteProdutoPorId(int id){
        return produtorepository.obteProdutoPorId(id);
    }
    public Produto adicionarProduto(Produto produto){
        return produtorepository.adicionarProduto(produto);
    }

    public void deletarPorId(int id){
      produtorepository.deletarPorId(id);
    }

    public Produto atualizaProduto(Produto produto, int id){
        produto.setId(id);
        return produtorepository.atualizaProduto(produto);
    }
}
