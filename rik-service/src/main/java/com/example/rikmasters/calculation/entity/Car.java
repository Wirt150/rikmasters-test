package com.example.rikmasters.calculation.entity;

import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString(of = {"id", "vin", "stateNumber"})
@EqualsAndHashCode(of = {"id", "vin", "stateNumber"})
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;
    @NotNull
    @Size(min = 17, max = 17, message = "Длинна VIN должна быть 17 симовлов")
    @Column(name = "vin", length = 17, unique = true)
    private String vin;
    @NotNull
    @Pattern(regexp = "([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]\\s*\\d{3}\\s*[АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{2,3})|([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{3}\\s*\\d{2,3})",
            message = "Номер не соответствует паттерну")
    @Size(min = 8, max = 9, message = "Длинна номера должна быть 8-9 симовлов")
    @Column(name = "state_number", length = 9, unique = true)
    private String stateNumber;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Detail> details;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;
}
