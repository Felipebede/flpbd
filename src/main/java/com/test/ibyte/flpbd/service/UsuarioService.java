package com.test.ibyte.flpbd.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.ibyte.flpbd.exception.NotFoundException;
import com.test.ibyte.flpbd.model.Setor;
import com.test.ibyte.flpbd.model.Usuario;
import com.test.ibyte.flpbd.model.dto.JwtRequestDTO;
import com.test.ibyte.flpbd.model.dto.UsuarioDTO;
import com.test.ibyte.flpbd.repository.SetorRepository;
import com.test.ibyte.flpbd.repository.UsuarioRepository;
import com.test.ibyte.flpbd.util.JwtTokenUtil;
import com.test.ibyte.flpbd.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class UsuarioService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);


    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private RequestUtil requestUtil;

    @Override
    public UserDetails loadUserByUsername(String id) {
        Usuario user = this.obterPorId(Integer.parseInt(id));

        // pega a senha do user, mas pelo tempo curto está fixo a senha

        return new org.springframework.security.core.userdetails.User(id, passwordEncoder.encode("123456"),
                Collections.emptyList());
    }

    public String authenticate(JwtRequestDTO jwtRequest) {
        try {
            String id = jwtRequest.getId();
            String password = jwtRequest.getSenha();

            final Usuario usuario = this.obterPorId(Integer.parseInt(id));
            final UserDetails userDetails = this.loadUserByUsername(id);
            final String token = this.jwtTokenUtil.generateToken(userDetails, "user");

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, password));

            return token;
        } catch (Exception e) {
            logger.error("ERROR - authenticate(): ", e.getMessage());
            throw e;
        }
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obterPorId(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> obterPorSetorId(int id) {
        return usuarioRepository.findAllByDepartmentId(id);
    }

    public List<Usuario> obterPorSetor(String setor) {
        return usuarioRepository.findAllByDepartmentDescricao(setor);
    }

    @Transactional
    public String deletePorId(int id) {

        usuarioRepository.deleteUsuarioById(id);

        return "Usuario id: " + id + " deletado com sucesso!";

    }

    public Usuario cadastrar(Usuario usuario) {

        return usuarioRepository.save(usuario);

    }


//
//    public void cadastrarUsuarioApi() {
//
//
////        "https://5e61af346f5c7900149bc5b3.mockapi.io/desafio03/employer"
//        try {
//            URL url = new URL("https://5e61af346f5c7900149bc5b3.mockapi.io/desafio03/employer");
//
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", "Mozilla/5.0");
//            int responseCode = con.getResponseCode();
//            System.out.printf("COdigo resposta GET = " + responseCode);
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                List<UsuarioDTO> usuariosDtos = Arrays.asList(mapper.readValue(response.toString(), UsuarioDTO[].class));
//
//                //cadastrar setores
//
//                for (UsuarioDTO usuario : usuariosDtos) {
//
//                    Setor setor = setorRepository.findSetorByDescricao(usuario.getDepartment());
//
//                    if (setor != null) {
//                        logger.info("setor ja cadastrado : " + usuario.getDepartment());
//                    } else {
//                        setorRepository.save(new Setor(usuario.getDepartment()));
//                        logger.info("cadastrando setor : " + usuario.getDepartment());
//                    }
//                }
//
//                for (UsuarioDTO usuarioDTO : usuariosDtos) {
//                    Setor setor = setorRepository.findSetorByDescricao(usuarioDTO.getDepartment());
//
//                    Usuario usuario = new Usuario();
//                    usuario.setFirst_name(usuarioDTO.getFirst_name());
//                    usuario.setLast_name(usuarioDTO.getLast_name());
//                    usuario.setCareer(usuarioDTO.getCareer());
//
//                    usuario.setDepartment(setor);
//                    usuarioRepository.save(usuario);
//
//                }
//
//
//            } else {
//                System.out.printf("REquest nao funcionou.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


    public void cadastrarUsuarioApi() {


        try {

            StringBuffer response = requestUtil.getRequest("https://5e61af346f5c7900149bc5b3.mockapi.io/desafio03/employer");

            if (response != null) {


                List<UsuarioDTO> usuariosDtos = Arrays.asList(mapper.readValue(response.toString(), UsuarioDTO[].class));

                //cadastrar setores

                for (UsuarioDTO usuario : usuariosDtos) {

                    Setor setor = setorRepository.findSetorByDescricao(usuario.getDepartment());

                    if (setor != null) {
                        logger.info("setor ja cadastrado : " + usuario.getDepartment());
                    } else {
                        setorRepository.save(new Setor(usuario.getDepartment()));
                        logger.info("cadastrando setor : " + usuario.getDepartment());
                    }
                }

                for (UsuarioDTO usuarioDTO : usuariosDtos) {
                    Setor setor = setorRepository.findSetorByDescricao(usuarioDTO.getDepartment());

                    Usuario usuario = new Usuario();
                    usuario.setFirst_name(usuarioDTO.getFirst_name());
                    usuario.setLast_name(usuarioDTO.getLast_name());
                    usuario.setCareer(usuarioDTO.getCareer());

                    usuario.setDepartment(setor);
                    usuarioRepository.save(usuario);

                }


            } else {
                System.out.printf("REquest nao funcionou.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
