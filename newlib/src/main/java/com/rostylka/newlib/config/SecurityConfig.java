package com.rostylka.newlib.config;

import com.rostylka.newlib.utils.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    /**
     * Allows to config SecurityFilterChain
     *
     * @param httpSecurity HTTP Security
     * @return Filtered HTTP Security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        /*.requestMatchers("/authors").hasAnyRole("Reader", "Administrator")
                        .requestMatchers("/books").hasRole("Administrator")
                        .requestMatchers("/", "/home").permitAll()*/
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.successHandler(customAuthenticationSuccessHandler()))
                .logout(config -> config.logoutSuccessUrl("/"))
                .build();
    }

    /**
     * Encoder Configuration
     *
     * @return encoder ()
     * TODO change for normal encoder
     */
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    /**
     * Method for customization Path after Login
     * @return new CustomAuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}



