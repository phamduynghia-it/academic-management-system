package com.duynghia.Academic.Management.System.identity.repository;

import com.duynghia.Academic.Management.System.identity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByUsername(String username);
}
