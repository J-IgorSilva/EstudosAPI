package com.example.mercadinho.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mercadinho.model.Produto;
import com.example.mercadinho.respository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtorepository;

    public List<Produto>obtertodosprodutos(){
        return produtorepository.findAll();
    }

    public Optional<Produto> obteProdutoPorId(int id){
        return produtorepository.findById(id);
    }
    public Produto adicionarProduto(Produto produto){
        produto.setId(produto.getId());
        return produtorepository.save(produto);
    }

    public void deletarPorId(int id){
      produtorepository.deleteById(id);
    }

    public Produto atualizaProduto(Produto produto, int id){
        produto.setId(id);
        return produtorepository.save(produto);
    }
}
