package com.duynghia.Academic.Management.System.identity.service;

import com.duynghia.Academic.Management.System.identity.dto.request.AuthenticationRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.IntrospectRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.AuthenticationResponse;
import com.duynghia.Academic.Management.System.identity.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException;

}
