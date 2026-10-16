package com.metrodata.serverapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.metrodata.serverapp.dto.request.EmailSimpleRequest;
import com.metrodata.serverapp.dto.response.EmailSimpleResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EmailSimpleMapper {

  EmailSimpleResponse toSimpleEmail(EmailSimpleRequest request);
}
