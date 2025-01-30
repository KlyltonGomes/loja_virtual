package com.BirdSoftware.Loja_Virtual.security;


import com.BirdSoftware.Loja_Virtual.ApplicationContextLoad;
import com.BirdSoftware.Loja_Virtual.model.Usuario;
import com.BirdSoftware.Loja_Virtual.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/*criar autenticacao e retorna tambem a autenticacao JWT*/
@Service
@Component
public class JwttokenAutenticacaoService {

    private static final long EXPIRATION_TIME = 2592000000L; // 30 dias
    private static final String SECRET = "vZ8!7@S^3Jd#kLzN$2rFtPmX9*5&QhTp";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    /* Gera o token JWT */
    public void addAuthentication(HttpServletResponse response, String username) throws IOException, IOException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET) // Corrigido: Era ES512, agora HS512
                .compact();

        String token = TOKEN_PREFIX + JWT;

        response.addHeader(HEADER_STRING, token);
        liberacaoCors(response);

        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }

    /* Retorna o usuário validado */
    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(HEADER_STRING); // Pegando do request, não do response!

        if (token != null) {
            String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

            try {
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(tokenLimpo)
                        .getBody()
                        .getSubject();

                if (user != null) {
                    Usuario usuario = ApplicationContextLoad.getApplicationContext()
                            .getBean(UsuarioRepository.class)
                            .findUserByLogin(user);

                    if (usuario != null) {
                        return new UsernamePasswordAuthenticationToken(
                                usuario.getLogin(),
                                usuario.getSenha(),
                                usuario.getAuthorities()
                        );
                    }
                }
            } catch (Exception e) {
                return null;
            }
        }

        liberacaoCors(response);
        return null;
    }

    /* Liberação CORS */
    private void liberacaoCors(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
    }
}
