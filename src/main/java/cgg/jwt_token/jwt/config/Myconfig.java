package cgg.jwt_token.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import cgg.jwt_token.jwt.services.CustomUserDetailsService;

@Configuration
public class Myconfig {
    @Autowired
    private CustomUserDetailsService s1;

    UserDetailsService getUserDetailsService() {

        // UserDetails userDetails =
        // User.builder().username("din").password(getencoder().encode("add")).roles("ADMIN")
        // .build();
        // return new InMemoryUserDetailsManager(userDetails);
        return s1;
    }

    @Bean
    PasswordEncoder getencoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthManager(AuthenticationConfiguration builder)
            throws Exception {

        return builder.getAuthenticationManager();

    }

    @Bean
    AuthenticationProvider authenticationprovider() {

        DaoAuthenticationProvider d1 = new DaoAuthenticationProvider();

        d1.setUserDetailsService(s1);
        d1.setPasswordEncoder(getencoder());

        return d1;
    }
}
