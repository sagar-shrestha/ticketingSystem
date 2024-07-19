package com.cellpay.ticketingSystem.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ClientAuthenticationFilter extends OncePerRequestFilter {

    private static final String CLIENT_ID = "sagar";
    private static final String CLIENT_SECRET = "sagar";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String clientId = request.getHeader("Client-Id");
        String clientSecret = request.getHeader("Client-Secret");

        if (clientId != null && clientSecret != null) {
            Authentication authentication = authenticate(clientId, clientSecret);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
                return;
            }
            else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid Client-Id or Client-Secret");
                return;
            }
        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Client-Id and Client-Secret headers are required");
            return;
        }
    }


    private Authentication authenticate(String clientId, String clientSecret) {
        if (CLIENT_ID.equals(clientId) && CLIENT_SECRET.equals(clientSecret)) {
            return new ClientAuthenticationToken(clientId, clientSecret);
        }
        return null;
    }
}
