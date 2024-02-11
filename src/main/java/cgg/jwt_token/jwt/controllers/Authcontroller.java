package cgg.jwt_token.jwt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.jwt_token.jwt.entities.JwtRequest;
import cgg.jwt_token.jwt.entities.JwtResponse;
import cgg.jwt_token.jwt.entities.RefreshToken;
import cgg.jwt_token.jwt.helper.JwtHelper;
import cgg.jwt_token.jwt.helper.RefresTokenRequest;
import cgg.jwt_token.jwt.services.RefreshTokenService;

@RestController
@RequestMapping("/auth")
public class Authcontroller {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private RefreshTokenService refreshTokenService;

    private Logger Logger = LoggerFactory.getLogger(Authcontroller.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        System.out.println(jwtRequest.getUser());
        this.doAuthenticate(jwtRequest.getUser(), jwtRequest.getPassword());
        UserDetails userByUsername = userDetailsService.loadUserByUsername(jwtRequest.getUser());

        String token = jwtHelper.generateToken(userByUsername);

        // genreate refresh token
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userByUsername.getUsername());
        // genreate refresh token

        JwtResponse jwtResponse = JwtResponse.builder().token(token).user(userByUsername.getUsername()).refreshtoken(
                refreshToken)
                .build();
        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String string, String string2) {

        System.out.println(string + "------------");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                string, string2);

        try {

            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {

            throw new BadCredentialsException("invalid username or passowrd");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(BadCredentialsException b) {

        return b.getMessage();

    }

    @PostMapping("/create-refresh-token")
    public ResponseEntity<RefreshToken> createRefreshToken(@RequestBody String username) {
        refreshTokenService.createRefreshToken(username);
        return null;
    }

    // @PostMapping("/create-refresh-token")
    // public JwtResponse refreshJwtToken(@RequestBody RefresTokenRequest req) {
    // RefreshToken rf = refreshTokenService.verifyRefreshToken(req.refreshtoken);

    // }
}
