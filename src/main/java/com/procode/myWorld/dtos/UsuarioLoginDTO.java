package com.procode.myWorld.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioLoginDTO(
        @NotBlank @Email String email,
        @NotBlank String senha
) {}
