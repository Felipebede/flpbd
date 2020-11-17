package com.test.ibyte.flpbd.setor.controller;

import com.test.ibyte.flpbd.rest.UsuarioRestController;
import com.test.ibyte.flpbd.util.RequestUtil;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class SetorControllerTests {

    @Autowired
    private RequestUtil requestUtil;

    @Autowired
    private UsuarioRestController usuarioRestController;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(this.usuarioRestController);
    }

    @Test
    public void testCadastrarUsuario() throws IOException {

    }

}
