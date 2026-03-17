package com.duynghia.Academic.Management.System.entities;


import com.duynghia.Academic.Management.System.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", length = 36)
    String userId;

    @NotBlank(message = "Username is required")
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(nullable = false)
    String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false)
    String email;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "role_id", nullable = false)
    Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;


}
