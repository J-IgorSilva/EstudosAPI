package com.igorprojetos.gerenciahospital.model;

import java.sql.Date;
import java.util.List;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numeroLeitos")
    private Integer numeroLeitos;

    @ElementCollection
    @CollectionTable(name = "especialidades", joinColumns = @JoinColumn(name = "hospital_id"))
    @Column(name = "especialidades")
    private List<String> especialidades;

    @Column(name = "numeroFuncionarios")
    private Integer numeroFuncionarios;

    @Column(name = "capacidadeAtendimento")
    private Integer capacidadeAtendimento;

    @Column(name = "dataDeInauguracao")
    private Date dataDeInauguracao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumeroLeitos() {
        return numeroLeitos;
    }

    public void setNumeroLeitos(Integer numeroLeitos) {
        this.numeroLeitos = numeroLeitos;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    public Integer getNumeroFuncionarios() {
        return numeroFuncionarios;
    }

    public void setNumeroFuncionarios(Integer numeroFuncionarios) {
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public Integer getCapacidadeAtendimento() {
        return capacidadeAtendimento;
    }

    public void setCapacidadeAtendimento(Integer capacidadeAtendimento) {
        this.capacidadeAtendimento = capacidadeAtendimento;
    }

    public Date getDataDeInauguracao() {
        return dataDeInauguracao;
    }

    public void setDataDeInauguracao(Date dataDeInauguracao) {
        this.dataDeInauguracao = dataDeInauguracao;
    } 
    public Hospital(String nome) {
        this.nome = nome;
    }
    public Hospital() {}
    
}

