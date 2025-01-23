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

    /**
     * Method for creating URL for each Role
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String redirectUrl = "/";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            switch (authority.getAuthority()) {
                case ("ROLE_Reader") -> redirectUrl = "/readers/" + userDetails.getId();
                case ("ROLE_Administrator") -> redirectUrl = "/admin/" + userDetails.getId();
                case ("ROLE_Librarian") -> redirectUrl = "/librarians/" + userDetails.getId();
            }
        }
        response.sendRedirect(redirectUrl);
    }

}



