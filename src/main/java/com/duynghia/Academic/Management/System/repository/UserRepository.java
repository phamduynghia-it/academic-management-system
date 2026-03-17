package com.duynghia.Academic.Management.System.repository;

import com.duynghia.Academic.Management.System.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
