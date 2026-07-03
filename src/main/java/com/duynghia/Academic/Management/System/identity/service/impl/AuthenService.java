package com.duynghia.Academic.Management.System.identity.service.impl;

import com.duynghia.Academic.Management.System.config.jwt.JwtService;
import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.duynghia.Academic.Management.System.identity.dto.request.AuthenticationRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.IntrospectRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.LogoutRequest;
import com.duynghia.Academic.Management.System.identity.dto.request.RefreshRequest;
import com.duynghia.Academic.Management.System.identity.dto.response.AuthenticationResponse;
import com.duynghia.Academic.Management.System.identity.dto.response.IntrospectResponse;
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

import java.text.ParseException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenService implements IAuthenService {
    private static final Logger log = LoggerFactory.getLogger(AuthenService.class);
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    JwtService jwtService;
    RefreshTokenService refreshService;

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
            var refreshToken = refreshService.createRefreshToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken.getToken())
                    .build();
        } catch (JOSEException e) {
            log.info("can't create token");
            throw new RuntimeException(e);
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            jwtService.verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            jwtService.invalidateToken(request.getToken());
        } catch (AppException e) {
            log.info("Token already invalid");
        }

        try {
            var refreshToken = refreshService.findByToken(request.getRefreshToken());
            refreshService.revokeToken(refreshToken);
        } catch (AppException e) {
            log.info("Refresh token not found or already invalid");
        }
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) {
        return java.util.Optional.of(refreshService.findByToken(request.getRefreshToken()))
                .map(token -> {
                    if (token.isRevoked()) {
                        refreshService.revokeAllUserTokens(token.getUser());
                        throw new AppException(ErrorCode.UNAUTHENTICATED);
                    }
                    return refreshService.verifyExpiration(token);
                })
                .map(token -> {
                    refreshService.revokeToken(token);
                    User user = token.getUser();
                    try {
                        String newAccessToken = jwtService.generateToken(user.getUsername());
                        var newRefreshToken = refreshService.createRefreshToken(user);
                        return AuthenticationResponse.builder()
                                .token(newAccessToken)
                                .refreshToken(newRefreshToken.getToken())
                                .build();
                    } catch (JOSEException e) {
                        throw new RuntimeException(e);
                    }
                }).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
    }
}
