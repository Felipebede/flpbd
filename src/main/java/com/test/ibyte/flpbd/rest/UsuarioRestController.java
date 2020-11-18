package com.test.ibyte.flpbd.rest;

import com.test.ibyte.flpbd.model.Usuario;
import com.test.ibyte.flpbd.model.dto.JwtRequestDTO;
import com.test.ibyte.flpbd.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
@Api(tags = "UsuarioRestController")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {

        List<Usuario> usuarios = usuarioService.listarTodos();

        if (usuarios == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.obterPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("setor-id/{id_setor}")
    public ResponseEntity<List<Usuario>> obterPorSetorId(@PathVariable("id_setor") int id) {

        List<Usuario> usuarios = usuarioService.obterPorSetorId(id);

        if (usuarios == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("setor/{setor}")
    public ResponseEntity<List<Usuario>> obterPorSetorDesc(@PathVariable("setor") String setor) {
        List<Usuario> usuarios = usuarioService.obterPorSetor(setor);

        if (usuarios == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePorId(@PathVariable("id") int id) {

        String mensagem = usuarioService.deletePorId(id);

        if (mensagem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mensagem);
    }

    @PostMapping("cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

        Usuario usuarioCadastrado = usuarioService.cadastrar(usuario);
        if (usuarioCadastrado.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioCadastrado);
    }

    @PostMapping("editar")
    public ResponseEntity<Usuario> editar(@RequestBody Usuario usuario) {
        Usuario usuarioEditado = usuarioService.editar(usuario);
        if (usuarioEditado.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioEditado);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody JwtRequestDTO jwtRequestDTO) {

        String mensagem = usuarioService.authenticate(jwtRequestDTO);

        if (mensagem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mensagem);
    }

    //desafio bonus
    @GetMapping("cadastrar-api")
    @ApiOperation(value = "Cadastrar Usuários e setores do desafio Bônus")
    public  ResponseEntity<List<Usuario>> cadastrarUsuariosApi() {
        List<Usuario> usuariosInicio = usuarioService.listarTodos();

        usuarioService.cadastrarUsuarioApi();

        List<Usuario> usuariosApos = usuarioService.listarTodos();

        if (usuariosApos.size() < usuariosInicio.size() + 100) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(usuariosApos);

    }

}
