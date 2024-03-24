package com.igorprojetos.gerenciahospital.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
import com.igorprojetos.gerenciahospital.model.Pessoa;
import com.igorprojetos.gerenciahospital.services.PessoaServices;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    private final PessoaServices pessoaServices;

    public PessoaController(PessoaServices pessoaServices) {
        this.pessoaServices = pessoaServices;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Pessoa>> buscar() {
        List<Pessoa> pessoas = pessoaServices.obtertodasPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @PostMapping
    public ResponseEntity<Pessoa> adicionar(@RequestBody Pessoa pessoa) throws Exception {
        Pessoa pessoaCriada = pessoaServices.adicionarPessoa(pessoa);
        return ResponseEntity
                .created(URI.create("/criado" + pessoaCriada.getId()))
                .body(pessoaCriada);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Pessoa>> buscarId(@PathVariable int id) {
        Optional<Pessoa> pessoaEncontrada = pessoaServices.obterPessoaPorId(id);
        if (pessoaEncontrada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoaEncontrada);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Integer> deletarPessoaId(@PathVariable int id) {
        pessoaServices.deletarPessoaPorId(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/atualiza/{id}")
    public ResponseEntity<Pessoa> atualizarId(@RequestBody Pessoa pessoa, @PathVariable Integer id) {

        pessoaServices.atualizarPessoa(pessoa, id);

        return new ResponseEntity<>(pessoa, HttpStatus.OK);

    }
}
