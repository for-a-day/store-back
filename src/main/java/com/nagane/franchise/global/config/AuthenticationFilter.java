package com.nagane.franchise.global.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nagane.franchise.store.application.impl.AdminServiceImpl;
import com.nagane.franchise.store.application.impl.StoreServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            System.out.println("Extracted token: " + token); // 토큰 출력

            try {
                Claims claims = jwtUtil.parseToken(token);
                String id = claims.getSubject();

                CustomUserDetails userDetails = new CustomUserDetails(id.toString(), "",
                        AuthorityUtils.NO_AUTHORITIES, id);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Access Token 갱신 로직
//                if (jwtUtil.isTokenExpired(token)) {
//                    String refreshToken = request.getHeader("Refresh-Token");
//                    if (refreshToken != null) {
//                        // Validate refresh token
//                        Claims refreshClaims = jwtUtil.parseToken(refreshToken);
//                        String adminIdFromRefresh = refreshClaims.getSubject();
//
//                        if (adminIdFromRefresh != null && adminIdFromRefresh.equals(adminId)) {
//                            // Generate new access token
//                            String newToken = jwtUtil.generateToken(adminId);
//                            response.setHeader("Authorization", "Bearer " + newToken);
//                        }
//                    }
//                }

            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired: ", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("JWT Token has expired");
                return;
            } catch (Exception e) {
                logger.error("JWT Token validation error: ", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("JWT Token validation error");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
