package com.BlackFoxT.smartbook_backend.dto.user;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {

    private String name;
    private String surname;

    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;
}