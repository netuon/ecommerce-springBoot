package com.example.ecommerce.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ecommerce.User.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(UserModel userModel) {
        Algorithm algorithm = Algorithm.HMAC256(secret); //assinatura do token

        try{
            return JWT.create()
                    .withIssuer("ecommerce-api")
                    .withClaim("UserId", userModel.getId())
                    .withSubject(userModel.getLogin())
                    .withExpiresAt(Instant.now().plusSeconds(3600000))//tempo de vida do token
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw  new RuntimeException("erro ao gerar token", exception);
        }
    }
    public String getRole(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("ecommerce-api")
                    .build()
                    .verify(token)
                    .getClaim("role")
                    .asString();
        } catch (JWTCreationException exception) {
            throw  new RuntimeException("erro ao gerar token", exception);
        }

    }


    public String verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try{
            return JWT.require(algorithm)
                    .withIssuer("ecommerce-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException  exception) {
            return null;
        }
    }//fazer o get depois
}
