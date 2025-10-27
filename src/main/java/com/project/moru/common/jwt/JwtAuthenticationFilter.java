package com.project.moru.common.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final CustomUserDetailsService customUserDetailsService;
  
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws IOException {
    
    try {
      String header = request.getHeader("Authorization");
      
      if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);
        
        if (jwtTokenProvider.validateToken(token)) {
          String username = jwtTokenProvider.getUsername(token);
          UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      
      filterChain.doFilter(request, response);
    } catch (Exception e) { // 예외 처리 로직 구현해야함.
      SecurityContextHolder.clearContext();
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write("{\"success\":false, \"message\":\"" + e.getMessage() + "\"}");
    }
    
  }
}
