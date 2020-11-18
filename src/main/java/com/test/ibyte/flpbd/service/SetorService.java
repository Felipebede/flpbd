package com.test.ibyte.flpbd.service;

import com.test.ibyte.flpbd.exception.NotFoundException;
import com.test.ibyte.flpbd.model.Setor;
import com.test.ibyte.flpbd.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public List<Setor> listarTodos() {
        return setorRepository.findAll();
    }

    public Setor obterPorId(int id) {
        return setorRepository.findById(id).orElseThrow(() -> new NotFoundException("Setor não encontrado"));
    }

    public Setor cadastrar(Setor setor) {
        return setorRepository.save(setor);
    }

    public Setor salvar(Setor setor) {
        return setorRepository.save(setor);
    }

    
    @Transactional
    public String deletePorId(int id) {
        setorRepository.deleteSetorById(id);
        return "Setor id: " + id + " deletado com sucesso!";
    }

}
