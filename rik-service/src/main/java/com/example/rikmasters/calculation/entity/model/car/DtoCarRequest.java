package com.example.rikmasters.calculation.entity.model.car;

import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoCarRequest {
    @NotBlank(message = "Поле не может быть пустым.")
    @Size(min = 17, max = 17, message = "Длинна VIN должна быть 17 симовлов")
    private String vin;
    @NotBlank(message = "Поле не может быть пустым.")
    @Pattern(regexp = "([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]\\s*\\d{3}\\s*[АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{2,3})|([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{3}\\s*\\d{2,3})",
            message = "Номер не соответствует паттерну")
    @Size(min = 8, max = 9, message = "Длинна номера должна быть 8-9 симовлов")
    private String stateNumber;
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;
}
