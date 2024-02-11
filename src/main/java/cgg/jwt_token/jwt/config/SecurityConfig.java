package cgg.jwt_token.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cgg.jwt_token.jwt.Filter.JwtAuthFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationEntryPoint point;

    @Autowired
    private JwtAuthFilter filter;

    @Bean
    SecurityFilterChain getfilterchain(HttpSecurity httpsecurity) throws Exception {

        httpsecurity.csrf(s -> s.disable()).cors(s -> s.disable()).authorizeHttpRequests(
                auth -> auth.requestMatchers("/test").authenticated()
                        .requestMatchers("/auth/login", "/user/create", "/auth/refresh")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(e -> e.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpsecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return httpsecurity.build();

    }

}
