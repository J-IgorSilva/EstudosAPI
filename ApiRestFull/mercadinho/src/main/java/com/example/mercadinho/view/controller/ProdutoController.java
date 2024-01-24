package com.example.mercadinho.view.controller;

import com.example.mercadinho.services.ProdutoService;
import com.example.mercadinho.shared.ProdutoDTO;
import com.example.mercadinho.view.model.ProdutoRequest;
import com.example.mercadinho.view.model.ProdutoResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterProdutos() {
        List<ProdutoDTO> produtos = produtoService.obtertodosprodutos();
        ModelMapper mapper = new ModelMapper();
        List<ProdutoResponse>resposta=produtos.stream()
        .map(ProdutoDTO ->mapper.map(ProdutoDTO, ProdutoResponse.class))
        .collect(Collectors.toList());
        return new ResponseEntity<>(resposta,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> buscarPorId(@PathVariable int id) {
        Optional<ProdutoDTO> dto = produtoService.obteProdutoPorId(id);
        ProdutoResponse produtoresponse = new ModelMapper().map(dto.get(),ProdutoResponse.class);
        return new ResponseEntity<>(Optional.of(produtoresponse),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoRequest) {
        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);
        produtoDTO = produtoService.adicionarProduto(produtoDTO);
        return new ResponseEntity<>(mapper.map(produtoDTO, ProdutoResponse.class),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Integer id) {
        produtoService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtorRequest,@PathVariable Integer id) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDTO = mapper.map(produtorRequest, ProdutoDTO.class);
        produtoDTO = produtoService.atualizaProduto(id,produtoDTO);
        return new ResponseEntity<>(
            mapper.map(produtoDTO, ProdutoResponse.class),
            HttpStatus.OK);
    }

}