package com.igorprojetos.gerenciahospital.model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hospital_id") // FK na tabela Pessoa
    private Hospital hospital; // Referência à entidade Hospital

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "doenca")
    private String doenca;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "salario")
    private Integer salario;

    @Column(name = "licenca")
    private String licenca;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @Column(name = "atendimentoPaciente")
    private Date atendimentoPaciente;

    @Column(name = "altaPaciente")
    private Date altaPaciente;

    @Column(name = "nomeHospital")
    private String nomeHospital;
    
    @Transient
    private Integer hospitalID;

    public void setHospitalID(Integer hospitallID){
        this.hospitalID = hospitallID;
    }
    
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getAtendimentoPaciente() {
        return atendimentoPaciente;
    }

    public void setAtendimentoPaciente(Date atendimentoPaciente) {
        this.atendimentoPaciente = atendimentoPaciente;
    }

    public Date getAltaPaciente() {
        return altaPaciente;
    }

    public void setAltaPaciente(Date altaPaciente) {
        this.altaPaciente = altaPaciente;
    }
    public Hospital getHospital() {
        return hospital;
    }
    
    public String getNomeHospital() {
        return nomeHospital;
    }

    public void setNomeHospital(String nomeHospital) {
        this.nomeHospital = nomeHospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }
  
}
