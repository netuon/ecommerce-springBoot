package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.User.UserLoginDTO;
import com.example.ecommerce.DTO.User.UserRegisterDTO;
import com.example.ecommerce.DTO.User.UserTokenDTO;
import com.example.ecommerce.User.UserModel;
import com.example.ecommerce.User.UserService;
import com.example.ecommerce.infra.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class autheticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<Void> RegisterDTO(@RequestBody @Validated UserRegisterDTO userRegisterDTO){
        userService.criarUsuario(userRegisterDTO);//chama o metodo criar usuario do service e faz as validações

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> LoginDTO(@RequestBody UserLoginDTO userLoginDTO){
        try{
            var auth = authenticationManager.authenticate( //linha que dispara a validação do user
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.login(),
                            userLoginDTO.password()
                    )
            );

            String token = jwtService.generateToken((UserModel) auth.getPrincipal());//gera um token de acesso para o user logado
            return ResponseEntity.ok(new UserTokenDTO(token));

        } catch (AuthenticationException e){
            throw  new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "User or password is incorrect."
            );
        }

    }

}


