package com.test.ibyte.flpbd.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class RequestUtil {

    public StringBuffer getRequest(String urlParam) {

        try {
            URL url = new URL(urlParam);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.printf("COdigo resposta GET = " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public String loginRequest(String urlParam) {

//        String loginInputString = "{"id": "1", "senha": "123456"}";

//        String loginInputString = Json.create
//                .put("id", "1")
//                .put("senha", "123456")
//                .toString();



//        try {
//            URL url = new URL(urlParam);
//
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//
//            // For POST only - START
//            con.setDoOutput(true);
//            OutputStream os = con.getOutputStream();
//
//            byte[] input = loginInputString.getBytes("utf-8");
//
//            os.write(input);
//            os.flush();
//            os.close();
//            // For POST only - END
//
//            int responseCode = con.getResponseCode();
//            System.out.printf("COdigo resposta GET = " + responseCode);
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                StringBuffer token = new StringBuffer();
//                while ((inputLine = in.readLine()) != null) {
//                    token.append(inputLine);
//                }
//                in.close();
//
//                return token.toString();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
    }


}
