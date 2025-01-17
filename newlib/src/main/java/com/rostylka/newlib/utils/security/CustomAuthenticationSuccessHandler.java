package com.rostylka.newlib.utils.security;

import com.rostylka.newlib.services.implementations.UserServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * Class for customization redirect URL after login
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private UserServiceImplementation userServiceImplementation;

    @Override
    /**
     * Method for creating URL for each Role
     */
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        int userId = userServiceImplementation.findByLogin(user.getUsername()).getId();

        String redirectUrl = "/";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            switch (authority.getAuthority()) {
                case ("ROLE_Reader") -> redirectUrl = "/readers/" + userId;
                case ("ROLE_Administrator") -> redirectUrl = "/admin/" + userId;
                case ("ROLE_Librarian") -> redirectUrl = "/librarians/" + userId;
            }
        }
        response.sendRedirect(redirectUrl);
    }

    @Autowired
    public void setUserServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }
}
