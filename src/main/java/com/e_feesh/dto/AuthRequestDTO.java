package com.e_feesh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {

    @NotBlank
    @Size(min=6,max = 20)
    private String username;

    @NotBlank
    @Size(min=8,max = 16)
    private String password;

    public AuthRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
