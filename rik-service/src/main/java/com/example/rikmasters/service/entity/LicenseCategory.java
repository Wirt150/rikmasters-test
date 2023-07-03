package com.example.rikmasters.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(of = {"id", "category"})
@EqualsAndHashCode(of = {"id", "category"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "license_category")
public class LicenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "category", length = 3, unique = true)
    private String category;
}
