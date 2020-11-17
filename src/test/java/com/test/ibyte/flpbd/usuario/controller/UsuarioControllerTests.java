package com.test.ibyte.flpbd.usuario.controller;

import com.test.ibyte.flpbd.model.Setor;
import com.test.ibyte.flpbd.model.Usuario;
import com.test.ibyte.flpbd.rest.UsuarioRestController;
import com.test.ibyte.flpbd.seguranca.JwtAuthenticationEntryPoint;
import com.test.ibyte.flpbd.seguranca.JwtRequestFilter;
import com.test.ibyte.flpbd.seguranca.WebSecurity;
import com.test.ibyte.flpbd.service.SetorService;
import com.test.ibyte.flpbd.service.UsuarioService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;


@WebMvcTest
class UsuarioControllerTests {

    @Autowired
    private UsuarioRestController usuarioRestController;

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private SetorService setorService;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private WebSecurity webSecurity;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private UserDetailsService jwtUserDetailsService;


    @BeforeEach
    public void setup() {
        standaloneSetup(this.usuarioRestController, this.usuarioService);
    }

    @Test
    public void retornarSucesso_BuscarUsuario() {

        when(this.usuarioService.obterPorId(1)).thenReturn(new Usuario(1, "Usuario", "Teste", "Testes dev", new Setor(1, "Testes Ibyte")));


        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/usuario/{id}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());

    }

}
