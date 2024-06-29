package albert.dev.ForoHub.controller;

import albert.dev.ForoHub.domain.users.AuthenticationData;

import albert.dev.ForoHub.domain.users.User;
import albert.dev.ForoHub.infra.security.JWTokenData;
import albert.dev.ForoHub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity authenticationUser(@RequestBody @Valid AuthenticationData authenticationData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authenticationData.login(), authenticationData.password());
        var authUser = authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generateToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JWTokenData(JWToken));
    }

}