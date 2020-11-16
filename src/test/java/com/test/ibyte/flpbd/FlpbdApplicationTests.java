package com.test.ibyte.flpbd;

import com.test.ibyte.flpbd.util.RequestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
class FlpbdApplicationTests {

    @Autowired
    private RequestUtil requestUtil;

    @BeforeEach
    public void login() {
        String token = requestUtil.loginRequest("http://localhost:8080/api/usuario/login");
        System.out.printf("token : " + token);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testBuscarUsaurio() throws IOException {
        System.out.printf("Teste");

    }

}
