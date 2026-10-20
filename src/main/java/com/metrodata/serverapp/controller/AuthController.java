package com.metrodata.serverapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.dto.request.RegistrationRequest;
import com.metrodata.serverapp.dto.response.RegistrationResponse;
import com.metrodata.serverapp.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/registration")
  public RegistrationResponse registration(@RequestBody RegistrationRequest request) {
    return authService.registration(request);
  }
}
