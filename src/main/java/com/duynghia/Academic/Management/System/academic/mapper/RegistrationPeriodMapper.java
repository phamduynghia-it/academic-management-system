package com.duynghia.Academic.Management.System.academic.mapper;

import com.duynghia.Academic.Management.System.academic.dto.request.RegistrationPeriodCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.RegistrationPeriodResponse;
import com.duynghia.Academic.Management.System.academic.entities.RegistrationPeriod;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistrationPeriodMapper {
    RegistrationPeriod toRegistrationPeriod(RegistrationPeriodCreationRequest request);

    RegistrationPeriodResponse toRegistrationPeriodResponse(RegistrationPeriod registrationPeriod);
}
