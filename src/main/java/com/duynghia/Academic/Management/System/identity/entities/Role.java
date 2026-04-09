package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.identity.enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @NotBlank
    @Column(unique = true, nullable = false)
    private RoleName roleName;
}