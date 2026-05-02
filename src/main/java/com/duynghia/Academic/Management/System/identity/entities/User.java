package com.duynghia.Academic.Management.System.identity.entities;

import com.duynghia.Academic.Management.System.identity.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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

    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min = 2, max = 50, message = "INVALID_USERNAME")
    @Column(nullable = false, unique = true)
    String username;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 6, max = 255, message = "INVALID_PASSWORD")
    @Column(nullable = false)
    String password;

    @NotBlank(message = "EMAIL_REQUIRED")
    @Email(message = "INVALID_EMAIL")
    @Column(nullable = false)
    String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    Role role;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "address", columnDefinition = "nvarchar(250)")
    @Size(max = 250, message = "ADDRESS_INVALID_LENGTH")
    String address;

    @Column(name = "phone", length = 15)
    @Pattern(regexp = "^(0|\\+84)[0-9]{9,10}$", message = "PHONE_INVALID_FORMAT")
    String phone;

    @Column(name = "full_name", columnDefinition = "nvarchar(35)")
    @Size(max = 50, message = "FULL_NAME_INVALID_LENGTH")
    String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdAt;

}