package com.BlackFoxT.smartbook_backend.dto.admin;

import com.BlackFoxT.smartbook_backend.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminUserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private int totalBooks;
}
