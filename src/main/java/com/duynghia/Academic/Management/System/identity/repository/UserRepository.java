package com.duynghia.Academic.Management.System.identity.repository;

import com.duynghia.Academic.Management.System.identity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
