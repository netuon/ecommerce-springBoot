package com.example.ecommerce.User;

import com.example.ecommerce.DTO.User.UserLoginDTO;
import com.example.ecommerce.DTO.User.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private userRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
    public void criarUsuario(UserRegisterDTO userRegisterDTO) {
        if(userRepository.existsByLogin(userRegisterDTO.login())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User already exists"
            );
        }//verifica se o login ja existe

        String senhaHash = encoder.encode(userRegisterDTO.password());//pega a senha e transforma em hash
        UserModel newUser = new UserModel(
                userRegisterDTO.login(),
                senhaHash,
                Role.USER);

        userRepository.save(newUser);
    }

}
