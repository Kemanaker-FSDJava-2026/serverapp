package com.metrodata.serverapp.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.metrodata.serverapp.dto.request.EmailAttachRequest;
import com.metrodata.serverapp.dto.request.EmailSimpleRequest;
import com.metrodata.serverapp.dto.response.EmailAttachResponse;
import com.metrodata.serverapp.dto.response.EmailSimpleResponse;
import com.metrodata.serverapp.mapper.EmailAttachMapper;
import com.metrodata.serverapp.mapper.EmailSimpleMapper;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {

  private final EmailSimpleMapper emailSimpleMapper;
  private final EmailAttachMapper emailAttachMapper;
  private JavaMailSender javaMailSender;

  public EmailSimpleResponse sendSimpleMessage(EmailSimpleRequest request) {

    EmailSimpleResponse response = emailSimpleMapper.toSimpleEmail(request);

    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(request.getTo());
    message.setSubject(request.getSubject());
    message.setText(request.getText());

    javaMailSender.send(message);
    return response;
  }

  public EmailAttachResponse sendMessageWithAttachmen(EmailAttachRequest request) {
    EmailAttachResponse response = emailAttachMapper.toAttachEmail(request);

    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(request.getTo());
      helper.setSubject(request.getSubject());
      helper.setText(request.getText());

      FileSystemResource file = new FileSystemResource(request.getAttach());
      helper.addAttachment("Invoice", file);

      javaMailSender.send(message);

    } catch (Exception e) {
      log.error("Log Email : {}", e.getMessage());
      System.out.println("Sout Email error : " + e.getMessage());
    }

    return response;
  }
}
