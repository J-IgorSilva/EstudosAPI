package com.teste.primeiroexemplo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.services.ProdutoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> obterProdutos() {
        
        return produtoService.obtertodosprodutos();
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto) {
        return produtoService.adicionarProduto(produto);
    }
    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@PathVariable int id) {
        return produtoService.obteProdutoPorId(id);
    }
    @DeleteMapping("/{id}")
    public String deletar (@PathVariable int id) {
        produtoService.deletarPorId(id);
        return "Produto com id" +id +"foi deletado!";
    }
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable int id,@RequestBody Produto produto) {
        return produtoService.atualizaProduto(produto, id);
    }

}