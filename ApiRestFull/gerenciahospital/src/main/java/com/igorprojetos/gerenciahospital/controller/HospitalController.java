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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.igorprojetos.gerenciahospital.model.Hospital;
import com.igorprojetos.gerenciahospital.services.HospitalServices;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
    private final HospitalServices hospitalService;

    public HospitalController(HospitalServices hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Hospital>> obterHopital() {
        List<Hospital> hospitais = hospitalService.obtertodosHospitais();
        return ResponseEntity.ok(hospitais);

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Optional<Hospital>> buscarId(@PathVariable Integer id) {
        Optional<Hospital> hospitalEncontrado = hospitalService.obteHospitalPorId(id);
        if (hospitalEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hospitalEncontrado);
    }

    @PostMapping
    public ResponseEntity<Hospital> adicionar(@RequestBody Hospital hospital) {
        Hospital hospitalCriado = hospitalService.adicionarHospital(hospital);
        return ResponseEntity.created(URI.create("/criado" + hospitalCriado.getId())).body(hospitalCriado);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Integer> deletarHospitalId(@PathVariable Integer id) {
        Optional<Hospital> hospitalEncontrado = hospitalService.obteHospitalPorId(id);
        if (hospitalEncontrado.isPresent()) {
            Hospital hospital = hospitalEncontrado.get(); // Obtém o objeto Hospital do Optional

            // Compara o ID do Hospital com o ID da URL
            if (hospital.getId().equals(id)) {
                hospitalService.deletarHospitalPorId(id);
                return ResponseEntity.ok(id);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/especialidades")
    public ResponseEntity<List<String>> buscarEspecialidadesHospitalId(@PathVariable Integer id) {
        List<String> especialidades = hospitalService.buscarEspecialidadesPorHospitalId(id);
        if (especialidades.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se não encontrar especialidades
        }

        return ResponseEntity.ok(especialidades);

    }

}
