package com.metrodata.serverapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.service.NotificationService;
import com.metrodata.serverapp.service.impl.EmailNotificationServiceImpl;

@RestController
public class HomeController {

  private final EmailNotificationServiceImpl emailService;
  private final NotificationService notificationService;
  private final NotificationService defaultNotificationService;

  public HomeController(EmailNotificationServiceImpl emailService, NotificationService notificationService,
      @Qualifier("sms") NotificationService defaultNotificationService) {
    this.emailService = emailService;
    this.notificationService = notificationService;
    this.defaultNotificationService = defaultNotificationService;
  }

  @GetMapping
  public String home() {
    return "Welcome to Server App...😀😀😀";
  }

  @GetMapping("/notify")
  public Map<String, String> notify(@RequestParam String message,
      @RequestParam(defaultValue = "Ini adalah sms") String defaultMessage) {
    return Map.of(
        "default", defaultNotificationService.send(defaultMessage),
        "email", notificationService.send(message));
  }
}
