package com.test.ibyte.flpbd.usuario.controller;

import com.test.ibyte.flpbd.model.Setor;
import com.test.ibyte.flpbd.model.Usuario;
import com.test.ibyte.flpbd.rest.SetorRestController;
import com.test.ibyte.flpbd.rest.UsuarioRestController;
import com.test.ibyte.flpbd.service.SetorService;
import com.test.ibyte.flpbd.service.UsuarioService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
@SpringBootTest
class UsuarioControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    private UsuarioRestController usuarioRestController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private SetorService setorService;

    @Mock
    private SetorRestController setorRestController;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioRestController).build();
    }


    @Test
    public void retornarSucesso_BuscarUsuario() {

        when(usuarioService.obterPorId(1))
                .thenReturn(new Usuario(1, "Usuario", "Teste", "Testes dev", new Setor(1, "Testes Ibyte")));

        try {
            given().mockMvc(mockMvc)
                    .accept(ContentType.JSON)
                    .when()
                    .get("/api/usuario/{id}", 1L)
                    .then()
                    .statusCode(HttpStatus.OK.value());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void retornarErro_BuscarUsuario() {

        when(usuarioService.obterPorId(999))
                .thenReturn(null);

        try {
            given().mockMvc(mockMvc)
                    .accept(ContentType.JSON)
                    .when()
                    .get("/api/usuario/{id}", 999)
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    
    @Test
    public void retornarErro_ListarUsuarios() {

        when(usuarioService.listarTodos())
                .thenReturn(null);

        try {
            given().mockMvc(mockMvc)
                    .accept(ContentType.JSON)
                    .when()
                    .get("/api/usuario")
                    .then()
                    .statusCode(HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
