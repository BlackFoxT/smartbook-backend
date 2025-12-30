package com.BlackFoxT.smartbook_backend.dto.user;

import com.BlackFoxT.smartbook_backend.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponse {

    private String username;
    private String email;
    private Role role;

    private String name;
    private String surname;
    private Integer age;
}