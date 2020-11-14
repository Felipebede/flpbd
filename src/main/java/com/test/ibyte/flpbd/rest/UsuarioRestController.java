package com.test.ibyte.flpbd.rest;

import java.util.List;

import com.test.ibyte.flpbd.model.Usuario;
import com.test.ibyte.flpbd.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    private List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("{id}")
    private Usuario obterPorId(@PathVariable("id") int id) {
        return usuarioService.obterPorId(id);
    }

    @GetMapping("setor-id/{id_setor}")
    private List<Usuario> obterPorSetorId(@PathVariable("id_setor") int id) {
        return usuarioService.obterPorSetorId(id);
    }

    @GetMapping("setor/{setor}")
    private List<Usuario> obterPorSetorDesc(@PathVariable("setor") String setor) {
        return usuarioService.obterPorSetor(setor);
    }

    @GetMapping("delete/{id}")
    private String deletePorId(@PathVariable("id") int id) {
        return usuarioService.deletePorId(id);
    }

    @PostMapping("cadastrar")
    private Usuario cadastrar(@RequestBody Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }

    @PostMapping("editar")
    private Usuario editar(@RequestBody Usuario usuario) {
        return usuarioService.editar(usuario);
    }

}
