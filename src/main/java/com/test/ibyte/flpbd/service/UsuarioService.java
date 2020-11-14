package com.test.ibyte.flpbd.service;

import java.util.List;

import com.test.ibyte.flpbd.exception.NotFoundException;
import com.test.ibyte.flpbd.model.Usuario;
import com.test.ibyte.flpbd.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obterPorId(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

    }

    public List<Usuario> obterPorSetorId(int id) {
        return usuarioRepository.findAllByDepartmentId(id);
    }

    public List<Usuario> obterPorSetor(String setor) {
        return usuarioRepository.findAllByDepartmentDescricao(setor);
    }

    @Transactional
    public String deletePorId(int id) {
        usuarioRepository.deleteUsuarioById(id);
        logger.info("[INFO] - Usuario id: " + id + " deletado");
        return "Usuario id: " + id + " deletado com sucesso!";
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario editar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

}
