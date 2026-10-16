package com.metrodata.serverapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAttachResponse {
  private String to;
  private String subject;
  private String text;
  private String attach;
}
