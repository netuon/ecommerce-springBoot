package com.example.ecommerce.infra;

import com.example.ecommerce.User.UserService;
import com.example.ecommerce.User.userRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private userRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        } else {
            var login = jwtService.verifyToken(token);//chama o service do token e verifica se tem login desse token
            var role = jwtService.getRole(token);//verifica a role desse token

            UserDetails user = userRepository.findByLogin(login);//pega o user com base no token validado

            var authorities = List.of(new SimpleGrantedAuthority(role));//"esse usuario tem essa role"

            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    authorities);//esse comando cria o user autenticado no bd

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);//diz que a request já esta autenticado
            filterChain.doFilter(request, response);//permite a requisição ir ate o controller
        }

    }
    private String recoverToken(HttpServletRequest request){//metodo que recebe a requisição HTTP
        var authHeader = request.getHeader("Authorization");//essa linha pega o header authorization

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {//verifica se o header existe
            return null;
        }
        return authHeader.substring(7);
    }
}
