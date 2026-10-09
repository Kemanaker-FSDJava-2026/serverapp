package com.metrodata.serverapp.service.impl;

import org.springframework.stereotype.Service;

import com.metrodata.serverapp.service.NotificationService;

@Service("sms")
public class SMSNotificationServiceImpl implements NotificationService {

  @Override
  public String send(String message) {
    return "[SMS] " + message;
  }

}
