package com.metrodata.serverapp.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.metrodata.serverapp.service.NotificationService;

@Primary
@Service("email")
public class EmailNotificationServiceImpl implements NotificationService {

  @Override
  public String send(String message) {
    return "[Email] " + message;
  }

}
