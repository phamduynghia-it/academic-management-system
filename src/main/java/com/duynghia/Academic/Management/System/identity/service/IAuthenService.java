package com.duynghia.Academic.Management.System.identity.service;

import com.duynghia.Academic.Management.System.identity.dto.request.AuthenticationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.AuthenticationResponse;

public interface IAuthenService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
