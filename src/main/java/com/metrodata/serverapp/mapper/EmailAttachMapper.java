package com.metrodata.serverapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.metrodata.serverapp.dto.request.EmailAttachRequest;
import com.metrodata.serverapp.dto.response.EmailAttachResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EmailAttachMapper {

  EmailAttachResponse toAttachEmail(EmailAttachRequest request);
}
