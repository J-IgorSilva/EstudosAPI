package com.teste.primeiroexemplo.repository;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.model.Exception.ResourceNotFoundException;

@Repository
public class ProdutoRepositoryold {
    private List<Produto> produtos = new ArrayList<Produto>();
    private int ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * 
     * @return
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    public Optional<Produto> obteProdutoPorId(int id) {
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    public Produto adicionarProduto(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    public void deletarPorId(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public Produto atualizaProduto(Produto produto) {
        Optional<Produto> produtoEncontrado = obteProdutoPorId(produto.getId());
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        deletarPorId(produto.getId());
        produtos.add(produto);

        return produto;
    }
}
