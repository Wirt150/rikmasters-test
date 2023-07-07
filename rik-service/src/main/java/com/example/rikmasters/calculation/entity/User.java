package com.example.rikmasters.calculation.entity;

import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(of = {"id", "fullName", "passport"})
@EqualsAndHashCode(of = {"id", "fullName", "passport"})
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "full_name", length = 255)
    private String fullName;
    @NotNull
    @Column(name = "passport", length = 12, unique = true)
    private String passport;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthday;
    @NotNull
    @Column(name = "driving_experience")
    private Integer drivingExperience;
    @ManyToMany
    @JoinTable(
            name = "user_license",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<LicenseCategory> userLicense;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;
}
