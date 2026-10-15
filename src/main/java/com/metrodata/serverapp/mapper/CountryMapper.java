package com.metrodata.serverapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.metrodata.serverapp.dto.request.CountryRequest;
import com.metrodata.serverapp.model.Country;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CountryMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "region", ignore = true)
  Country toEntity(CountryRequest request);
}
