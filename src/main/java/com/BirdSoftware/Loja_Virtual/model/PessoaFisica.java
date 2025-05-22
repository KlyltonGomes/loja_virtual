package com.BirdSoftware.Loja_Virtual.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id")  // Herda a chave primária de Pessoa
public class PessoaFisica extends Pessoa{

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String cpf;
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
