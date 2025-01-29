package com.BirdSoftware.Loja_Virtual.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/*criar autenticacao e retorna tambem a autenticacao JWT*/
@Service
@Component
public class JwttokenAutenticacaoService {

    private static final long EXPIRATION_TIME = 2592000000L; // 30 dias cravado

    /*CHAVE SECRETA PRA JUNTAR COM O JWT*/
    private static final String SECRET = "vZ8!7@S^3Jd#kLzN$2rFtPmX9*5&QhTp";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    /*Gera o token e da a resposta para o cliente*/
    public void addAuthentication(HttpServletResponse response, String username) throws Exception{
        /*montando o token*/
        String JWT = Jwts.builder() /*chama gerador de token*/
                .setSubject(username) /*add o user*/
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.ES512,SECRET).compact();/*TEMPO DE EXPIRACAO*/

        String token = TOKEN_PREFIX + " " + JWT;


        /*da a resposta para tela e para o cliente,outra API, navegador, aplicativo, javascript,outra chamada java*/
        response.addHeader(HEADER_STRING,token);

        /*usado para ver no postman para teste*/
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }
}
