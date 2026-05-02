package com.duynghia.Academic.Management.System.academic.service.impl;

import com.duynghia.Academic.Management.System.academic.dto.request.RegistrationPeriodCreationRequest;
import com.duynghia.Academic.Management.System.academic.dto.response.RegistrationPeriodResponse;
import com.duynghia.Academic.Management.System.academic.entities.RegistrationPeriod;
import com.duynghia.Academic.Management.System.academic.mapper.RegistrationPeriodMapper;
import com.duynghia.Academic.Management.System.academic.repository.RegistrationPeriodRepository;
import com.duynghia.Academic.Management.System.academic.service.IRegistrationPeriodService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegistrationPeriodService implements IRegistrationPeriodService {
    RegistrationPeriodRepository registrationPeriodRepositoryrepository;
    RegistrationPeriodMapper registrationPeriodMapper;

    @Override
    public RegistrationPeriodResponse createRegistrationPeriod(RegistrationPeriodCreationRequest request) {
        RegistrationPeriod registrationPeriod = registrationPeriodMapper.toRegistrationPeriod(request);
        return registrationPeriodMapper
                .toRegistrationPeriodResponse(registrationPeriodRepositoryrepository.save(registrationPeriod));
    }

}
