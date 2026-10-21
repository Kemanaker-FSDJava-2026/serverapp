package com.metrodata.serverapp.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.metrodata.serverapp.service.AppUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilterConfig extends OncePerRequestFilter {

  private final JWTConfig jwtConfig;
  private final AppUserDetailService appUserDetailService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // 1. ambil header => Authorization: Bearer <token>
    String header = request.getHeader("Authorization"); // Authorization

    // 2. check token !isValid => anonymous 401
    if (header == null || !header.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = header.substring(7);
    if (!jwtConfig.isTokenValid(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    // 3. check token valid => ambil username, load user (role + privilege) ->
    String username = jwtConfig.extractUsername(token);
    UserDetails userDetails;

    try {
      userDetails = appUserDetailService.loadUserByUsername(username);
    } catch (UsernameNotFoundException e) {
      filterChain.doFilter(request, response);
      return;
    }

    // 4. menandai request -> sudah berhasil login -> authorities
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authToken);

    // 5. lanjut ke filter selanjutnya -> controller
    filterChain.doFilter(request, response);
  }

}
