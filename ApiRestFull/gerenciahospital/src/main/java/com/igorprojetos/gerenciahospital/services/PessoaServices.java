package com.igorprojetos.gerenciahospital.services;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.igorprojetos.gerenciahospital.model.Hospital;
import com.igorprojetos.gerenciahospital.model.Pessoa;
import com.igorprojetos.gerenciahospital.repository.HospitalRepository;
import com.igorprojetos.gerenciahospital.repository.PessoaRepository;

@Service
public class PessoaServices {
    private PessoaRepository pessoaRepository;
    private HospitalRepository hospitalRepository;

    public PessoaServices(PessoaRepository pessoaRepository, HospitalRepository hospitalRepository) {
        this.pessoaRepository = pessoaRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public List<Pessoa> obtertodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> obterPessoaPorId(int id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa adicionarPessoa(Pessoa pessoa) throws Exception {
        String nomeHospital = pessoa.getNomeHospital();

        Hospital hospitalEncontrado = hospitalRepository.findByNome(nomeHospital);

        if (hospitalEncontrado != null) {
            Integer hospitalEncontradoId = hospitalEncontrado.getId(); // Assumindo que você tem uma propriedade
                                                                       // hospitalId

            if (pessoa.getClass().getDeclaredMethods().length > 0) { // Verifique se há métodos
                for (Method method : pessoa.getClass().getDeclaredMethods()) {
                    if (method.getName().equals("setHospital")) {
                        method.invoke(pessoa, hospitalEncontrado); // Use setHospital(Hospital) se disponível
                        break;
                    } else if (method.getName().equals("setHospitalID")) {
                        method.invoke(pessoa, hospitalEncontradoId); // Use setHospitalId(Integer) se necessário
                        break;
                    }
                }
            } else {
                // Lidar com o caso em que Pessoa não possui métodos setter (menos provável)
                throw new Exception("A classe Pessoa não possui métodos setter para hospital ou hospitalId");
            }
        }
        return pessoaRepository.saveAndFlush(pessoa);
    }

    public void deletarPessoaPorId(int id) {
        pessoaRepository.deleteById(id);
    }

    public Pessoa atualizarPessoa(Pessoa pessoa, Integer id) {
        pessoa.setId(id);
        pessoaRepository.save(pessoa);
        return pessoa;
    }

}
