package com.BirdSoftware.Loja_Virtual.repository;

import com.BirdSoftware.Loja_Virtual.model.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto,Long> {
}
