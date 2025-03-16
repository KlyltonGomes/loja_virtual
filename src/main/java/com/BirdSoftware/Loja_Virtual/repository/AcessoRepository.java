package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.enums.Role;
import com.BirdSoftware.Loja_Virtual.model.Acesso;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso,Long> {

    Optional<Acesso> findByDescricao(Role descricao);
}
