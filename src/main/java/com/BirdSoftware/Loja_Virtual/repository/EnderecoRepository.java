package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    @Query("SELECT e FROM Endereco e WHERE LOWER(TRIM(e.cep)) = LOWER(TRIM(:cep)) AND LOWER(TRIM(e.numero)) = LOWER(TRIM(:numero))")
    Optional<Endereco> findByCepAndNumero(@Param("cep") String cep, @Param("numero") String numero);
}
