package com.test.ibyte.flpbd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class FlpbdApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Test
//    public void testBuscarUsaurio() throws IOException {
//        URL url = new URL("http://localhost:8080/api/usuario/1");
//
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        int responseCode = con.getResponseCode();
//        System.out.printf("COdigo resposta GET = " + responseCode);
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            System.out.printf("response === " + response);
//            assertThat(response != null);
//
//
//        } else {
//            System.out.printf("REquest nao funcionou.");
//        }
//
//    }

}
