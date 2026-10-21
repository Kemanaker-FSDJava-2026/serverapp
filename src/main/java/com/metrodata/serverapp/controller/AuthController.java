package com.metrodata.serverapp.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.dto.request.LoginRequest;
import com.metrodata.serverapp.dto.request.RegistrationRequest;
import com.metrodata.serverapp.dto.response.LoginResponse;
import com.metrodata.serverapp.dto.response.RegistrationResponse;
import com.metrodata.serverapp.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  // ? registration
  @PostMapping("/registration")
  public RegistrationResponse registration(@RequestBody RegistrationRequest request) {
    return authService.registration(request);
  }

  // ? login
  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
    LoginResponse loginResponse = authService.login(loginRequest);

    // memastikan session setelah diset
    HttpSession session = request.getSession(true); // membuat session kalau belum ada
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
        SecurityContextHolder.getContext());

    return loginResponse;
  }
}
