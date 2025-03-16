package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuário com Acessos
    @Query("SELECT u FROM Usuario u JOIN FETCH u.acessos WHERE LOWER(u.login) = LOWER(:login)")
    Optional<Usuario> findUserByLogin(@Param("login") String login);

    // Buscar usuário sem Acessos (mais eficiente)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.login) = LOWER(:login)")
    Optional<Usuario> findByLogin(@Param("login") String login);

    // Melhor forma de buscar sem JOIN, pode ser mais eficiente:
    Optional<Usuario> findByLoginIgnoreCase(String login);

}


