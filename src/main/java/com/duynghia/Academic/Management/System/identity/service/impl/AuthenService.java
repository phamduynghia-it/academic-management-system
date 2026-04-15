package com.duynghia.Academic.Management.System.identity.service.impl;

import com.duynghia.Academic.Management.System.config.jwt.JwtService;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.AuthenticationRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.AuthenticationResponse;
import com.duynghia.Academic.Management.System.identity.entities.User;
import com.duynghia.Academic.Management.System.identity.repository.UserRepository;
import com.duynghia.Academic.Management.System.identity.service.IAuthenService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenService implements IAuthenService {
    private static final Logger log = LoggerFactory.getLogger(AuthenService.class);
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    JwtService jwtService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        boolean isValid = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isValid) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        try {
            String token = jwtService.generateToken(request.getUsername());
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (JOSEException e) {
            log.info("can't create token");
            throw new RuntimeException(e);
        }
    }
}
