package com.example.mercadinho.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mercadinho.model.Produto;
import com.example.mercadinho.model.Exception.ResourceNotFoundException;
import com.example.mercadinho.respository.ProdutoRepository;
import com.example.mercadinho.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtorepository;

    public List<ProdutoDTO>obtertodosprodutos(){
        List<Produto> produtos = produtorepository.findAll();
        return produtos.stream().map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obteProdutoPorId(int id){
        Optional<Produto> produto = produtorepository.findById(id);
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id" + id + "não encontrado");
        }
        //
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);
    }

    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDto){
        produtoDto.setId(null);
        ModelMapper  mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDto,Produto.class);
        produto = produtorepository.save(produto);
        produtoDto.setId(produto.getId());
        return produtoDto;
    }

    public void deletarPorId(int id){
        Optional<Produto>produto = produtorepository.findById(id);
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel encontrar o produto");
        }
      produtorepository.deleteById(id);
    }

    public ProdutoDTO atualizaProduto(Integer id, ProdutoDTO produtoDto){
        produtoDto.setId(id);
        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDto, Produto.class);
        produtorepository.save(produto);
        return produtoDto;
       
    }
}
