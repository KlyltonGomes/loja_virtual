package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByLogin(String login);
}
