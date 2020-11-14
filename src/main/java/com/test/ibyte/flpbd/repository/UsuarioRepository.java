package com.test.ibyte.flpbd.repository;

import java.util.List;
import java.util.Optional;

import com.test.ibyte.flpbd.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Override
    List<Usuario> findAll();

    void deleteUsuarioById(int id);

    // Optional<Usuario> findUsuarioById(int id);
    Optional<Usuario> findById(int id);
    // List<Usuario> findAllByFirst_name(String first_name);
    // List<Usuario> findAllByLast_name(String last_name);
    // List<Usuario> findAllByFirst_nameAndLast_name(String first_name, String
    // last_name);

    List<Usuario> findAllByDepartmentId(int id);

    List<Usuario> findAllByDepartmentDescricao(String setor);



}
