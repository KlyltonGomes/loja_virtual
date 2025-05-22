package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.model.PessoaFisica;
import com.BirdSoftware.Loja_Virtual.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public  interface PessoaFisicaRepository extends JpaRepository<PessoaFisica,Long> {
    Optional<PessoaFisica> findBycpf(String cpf);

}


