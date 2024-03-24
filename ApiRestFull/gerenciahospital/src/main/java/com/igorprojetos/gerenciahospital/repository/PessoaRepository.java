package com.igorprojetos.gerenciahospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.igorprojetos.gerenciahospital.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
