package com.project.app.conf;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.dto.AuthResponse;
import com.project.app.dto.AuthenticationDto;
import com.project.app.dto.RegisterDto;
import com.project.app.exption.types.EntityNotFoundException;
import com.project.app.model.User;
import com.project.app.repository.UserRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private TokenServices tokenServices;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthenticationDto data) {
 
            var authToken = new UsernamePasswordAuthenticationToken( data.login(),data.password());
            var auth = authenticationManager.authenticate(authToken);

            var tokken = tokenServices.generetdTokken( (User) auth.getPrincipal());
            return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(tokken));
       
    }

    @PostMapping("/register")
    public ResponseEntity resgiter(@RequestBody @Valid RegisterDto data) {

        UserDetails userDetails = userRepository.findByLogin(data.login());

        if (userDetails != null) {
            throw new EntityNotFoundException("Usuario já cadastrado!");
        }

        String encry = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encry, data.role());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();

    }

}