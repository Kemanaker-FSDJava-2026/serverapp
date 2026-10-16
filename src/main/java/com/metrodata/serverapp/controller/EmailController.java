package com.metrodata.serverapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.dto.request.EmailAttachRequest;
import com.metrodata.serverapp.dto.request.EmailSimpleRequest;
import com.metrodata.serverapp.dto.response.EmailAttachResponse;
import com.metrodata.serverapp.dto.response.EmailSimpleResponse;
import com.metrodata.serverapp.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
  private final EmailService emailService;

  @PostMapping("/simple")
  public EmailSimpleResponse sendSimpleMessage(@RequestBody EmailSimpleRequest request) {
    return emailService.sendSimpleMessage(request);
  }

  @PostMapping("/attach")
  public EmailAttachResponse sendMessageWithAttachmen(@RequestBody EmailAttachRequest request) {
    return emailService.sendMessageWithAttachmen(request);
  }
}
