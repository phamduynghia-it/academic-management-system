package com.duynghia.Academic.Management.System.identity.repository;

import com.duynghia.Academic.Management.System.identity.entities.RefreshToken;
import com.duynghia.Academic.Management.System.identity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    
    void deleteByUser(User user);
}
