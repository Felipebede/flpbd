package com.test.ibyte.flpbd.rest;

import com.test.ibyte.flpbd.model.Usuario;
import com.test.ibyte.flpbd.model.dto.JwtRequestDTO;
import com.test.ibyte.flpbd.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Api(tags = "UsuarioRestController")
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

    @PostMapping("login")
    private String login(@RequestBody JwtRequestDTO jwtRequestDTO) {
        return usuarioService.authenticate(jwtRequestDTO);
    }

    //desafio bonus
    @GetMapping("cadastrar-api")
    private void cadastrarUsuariosApi() {
        usuarioService.cadastrarUsuarioApi();
    }

}
