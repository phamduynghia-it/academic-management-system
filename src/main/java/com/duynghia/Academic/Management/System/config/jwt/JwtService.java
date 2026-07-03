package com.duynghia.Academic.Management.System.config.jwt;

import com.duynghia.Academic.Management.System.exception.AppException;
import com.duynghia.Academic.Management.System.exception.ErrorCode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(secretKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey("blacklist:" + signedJWT.getJWTClaimsSet().getJWTID())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    public void invalidateToken(String token) throws JOSEException, ParseException {
        SignedJWT signedJWT = verifyToken(token);
        String jwtID = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        long timeToLive = expiryTime.getTime() - System.currentTimeMillis();
        if (timeToLive > 0) {
            stringRedisTemplate.opsForValue().set("blacklist:" + jwtID, "invalid", timeToLive, java.util.concurrent.TimeUnit.MILLISECONDS);
        }
    }
}
