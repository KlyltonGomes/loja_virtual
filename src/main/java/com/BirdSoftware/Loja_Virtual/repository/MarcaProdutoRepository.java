package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.model.MarcaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaProdutoRepository extends JpaRepository<MarcaProduto,Long> {
}
