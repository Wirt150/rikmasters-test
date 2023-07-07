package com.example.rikmasters.calculation.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(of = {"id", "category"})
@EqualsAndHashCode(of = {"id", "category"})
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "license_category")
public class LicenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category", length = 3, unique = true)
    private String category;
}
