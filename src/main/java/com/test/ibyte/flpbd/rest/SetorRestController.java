package com.test.ibyte.flpbd.rest;

import com.test.ibyte.flpbd.model.Setor;
import com.test.ibyte.flpbd.service.SetorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Setor>> listarTodos() {

        List<Setor> setores = setorService.listarTodos();

        if (setores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(setores);
    }

    @GetMapping("{id}")
    public ResponseEntity<Setor> obterPorId(@PathVariable("id") int id) {

        Setor setor = setorService.obterPorId(id);

        if (setor == null) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(setor);
    }

    @PostMapping("cadastrar")
    public ResponseEntity<Setor> cadastrar(@RequestBody Setor setor) {
        Setor setorCadastrado = setorService.cadastrar(setor);

        if (setorCadastrado.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(setor);
    }

    @PutMapping("salvar")
    public ResponseEntity<Setor> salvar(@RequestBody Setor setor) {

        Setor setorCadastrado = setorService.salvar(setor);

        if (setorCadastrado.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(setor);

    }

}
