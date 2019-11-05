package ru.tsystems.reha.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        // Get the role of logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();

        String targetUrl = "";
        if (role.contains("ADMIN")) {
            targetUrl = "/user/list";
        } else if (role.contains("DOCTOR")) {
            targetUrl = "/patient/list";
        } else if (role.contains("NURSE")) {
            targetUrl = "/patient/list";
        } else targetUrl = "/error-page";
        return targetUrl;
    }
}
