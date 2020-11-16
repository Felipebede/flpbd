package com.test.ibyte.flpbd.repository;

import com.test.ibyte.flpbd.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

    @Override
    List<Setor> findAll();


    Setor findSetorByDescricao(String descricao);
}
