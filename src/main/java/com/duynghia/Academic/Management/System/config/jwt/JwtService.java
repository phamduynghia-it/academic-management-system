package com.duynghia.Academic.Management.System.config.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@ConfigurationProperties(prefix = "app.jwt")
@Getter
@Setter
public class JwtService {
    private String secretKey;
    private long expirationMs;
    private long refreshExpirationMs;

    public String generateToken(String username) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .jwtID(UUID.randomUUID().toString())
                .issuer("phamduynghia@lms.com")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + expirationMs))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(secretKey.getBytes()));
        return jwsObject.serialize();
    }
}
