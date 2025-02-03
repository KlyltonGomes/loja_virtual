package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.model.Usuario;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where u.login= ?1")
    Usuario findUserByLogin(String login);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.senha = ?1 WHERE u.login = ?2")
    void atualizarSenha(String novaSenha, String login);  // Atualiza diretamente no banco

    // Método para buscar usuário por login
    //Optional<Usuario> findByLogin(String login);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.login) = LOWER(:login)")
    Optional<Usuario> findByLogin(@Param("login") String login);


}

