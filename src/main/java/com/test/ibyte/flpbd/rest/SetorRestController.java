package com.test.ibyte.flpbd.rest;

import java.util.List;

import com.test.ibyte.flpbd.model.Setor;
import com.test.ibyte.flpbd.service.SetorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/setor")
public class SetorRestController {

    @Autowired
    private SetorService setorService;

    @GetMapping
    private List<Setor> listarTodos() {
        return setorService.listarTodos();
    }

    @GetMapping("{id}")
    private Setor obterPorId(@PathVariable("id") int id) {
        return setorService.obterPorId(id);
    }

    @PostMapping("cadastrar")
    private Setor cadastrar(@RequestBody Setor setor) {
        return setorService.cadastrar(setor);
    }

    @PostMapping("editar")
    private Setor editar(@RequestBody Setor setor) {
        return setorService.editar(setor);
    }

}
