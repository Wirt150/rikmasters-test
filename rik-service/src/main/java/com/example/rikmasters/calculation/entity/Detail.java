package com.example.rikmasters.calculation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString(of = {"id", "name", "serialNumber"})
@EqualsAndHashCode(of = {"id", "name", "serialNumber"})
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name", length = 255)
    private String name;
    @NotNull
    @Column(name = "serial_number", length = 6, unique = true)
    private String serialNumber;
    @ManyToOne
    @JsonIgnore
    private Car car;
}
