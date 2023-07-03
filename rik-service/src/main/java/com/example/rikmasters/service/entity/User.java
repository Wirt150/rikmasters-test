package com.example.rikmasters.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Size(max = 255, message = "Максимальный размер ограничен, 255 символов.")
    @Column(name = "full_name", length = 255)
    private String fullName;
    @NotNull
    @Pattern(regexp = "^([0-9]{2}\\s[0-9]{2}\\s[0-9]{6})?$", message = "Номер не соответствует паттерну")
    @Size(max = 12, message = "Максимальный размер ограничен, 12 символами.")
    @Column(name = "passport", length = 12, unique = true)
    private String passport;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthday;
    @NotNull
    @Min(value = 3, message = "Минимальный стаж ограничен, 3 годами")
    @Max(value = 100, message = "Максимальный стаж ограничен, 100 годами.")
    @Column(name = "driving_experience")
    private Integer drivingExperience;
    @ManyToMany
    @JoinTable(
            name = "user_license",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<LicenseCategory> userLicense;
}
