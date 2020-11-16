package com.test.ibyte.flpbd.rest;

import com.test.ibyte.flpbd.model.Setor;
import com.test.ibyte.flpbd.service.SetorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/setor")
@Api(tags = "SetorRestController")
public class SetorRestController {

    @Autowired
    private SetorService setorService;

    @GetMapping
//    @ApiOperation(value = "Listar Setores")
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

    @PostMapping("salvar")
    private Setor salvar(@RequestBody Setor setor) {
        return setorService.salvar(setor);
    }

}
