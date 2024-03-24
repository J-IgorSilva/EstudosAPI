package com.igorprojetos.gerenciahospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igorprojetos.gerenciahospital.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Hospital findByNome(String nome);
}
