package com.igorprojetos.gerenciahospital.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.igorprojetos.gerenciahospital.model.Hospital;
import com.igorprojetos.gerenciahospital.repository.HospitalRepository;

@Service
public class HospitalServices {
    private HospitalRepository hospitalRepository;

    public HospitalServices(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> obtertodosHospitais() {
        return hospitalRepository.findAll();
    }

    public Optional<Hospital> obteHospitalPorId(int id) {
        return hospitalRepository.findById(id);
    }

    public Hospital adicionarHospital(Hospital hospital) {
        hospital.setId(hospital.getId());
        return hospitalRepository.save(hospital);
    }

    public void deletarHospitalPorId(int id) {
        hospitalRepository.deleteById(id);
    }

    public Hospital atualizaHospital(Hospital produto, int id) {
        produto.setId(id);
        return hospitalRepository.save(produto);
    }

    public List<String> buscarEspecialidadesPorHospitalId(Integer id) {
        Optional<Hospital> hospitalEncontrado = hospitalRepository.findById(id);
        
        if (hospitalEncontrado.isPresent()) {
            return hospitalEncontrado.get().getEspecialidades(); // Retorna  especialidades do hospital encontrado
        } else {
            return Collections.emptyList(); // Retorna vazio se o hospital não encontrado
        }
    }

}
