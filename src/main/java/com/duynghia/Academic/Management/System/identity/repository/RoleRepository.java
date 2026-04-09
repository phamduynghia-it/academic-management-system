package com.duynghia.Academic.Management.System.identity.repository;

import com.duynghia.Academic.Management.System.identity.entities.Role;
import com.duynghia.Academic.Management.System.identity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
