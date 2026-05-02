package com.duynghia.Academic.Management.System.academic.service;

import com.duynghia.Academic.Management.System.academic.dto.request.RegistrationPeriodCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.RegistrationPeriodResponse;

public interface IRegistrationPeriodService {
    RegistrationPeriodResponse createRegistrationPeriod(RegistrationPeriodCreationRequest request);
}
