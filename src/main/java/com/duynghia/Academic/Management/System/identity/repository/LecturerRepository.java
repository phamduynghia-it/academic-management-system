package com.duynghia.Academic.Management.System.identity.repository;

import com.duynghia.Academic.Management.System.identity.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, String> {
}
