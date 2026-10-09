package com.metrodata.serverapp.controller;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.config.AppPropertiesConfig;
import com.metrodata.serverapp.service.NotificationService;
import com.metrodata.serverapp.service.impl.EmailNotificationServiceImpl;

@RestController
public class HomeController {

  private final EmailNotificationServiceImpl emailService;
  private final NotificationService notificationService;
  private final NotificationService defaultNotificationService;
  private final AppPropertiesConfig appPropertiesConfig;
  private final Clock clock;

  public HomeController(EmailNotificationServiceImpl emailService, NotificationService notificationService,
      @Qualifier("email") NotificationService defaultNotificationService, AppPropertiesConfig appPropertiesConfig,
      Clock clock) {
    this.emailService = emailService;
    this.notificationService = notificationService;
    this.defaultNotificationService = defaultNotificationService;
    this.appPropertiesConfig = appPropertiesConfig;
    this.clock = clock;
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

  @GetMapping("/info")
  public Map<String, Object> info() {
    return Map.of(
        "name", appPropertiesConfig.name(),
        "version", appPropertiesConfig.version(),
        "description", appPropertiesConfig.description(),
        "clock", LocalDateTime.now(clock).toString());
  }
}
