package com.example.rikmasters.service.entity.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserRequest {
    @NotBlank(message = "Поле не может быть пустым.")
    @Size(max = 255, message = "Максимальный размер ограничен, 255 символов.")
    private String fullName;
    @NotBlank(message = "Поле не может быть пустым.")
    @Pattern(regexp = "^([0-9]{2}\\s[0-9]{2}\\s[0-9]{6})?$", message = "Номер не соответствует паттерну")
    @Size(max = 12, message = "Максимальный размер ограничен, 12 символами.")
    private String passport;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotNull
    @Min(value = 3, message = "Минимальный стаж ограничен, 3 годами")
    @Max(value = 100, message = "Максимальный стаж ограничен, 100 годами.")
    private Integer drivingExperience;
    @Builder.Default
    private Set<Integer> userLicense = new HashSet<>();
}
