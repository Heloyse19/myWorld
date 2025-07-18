package com.procode.myWorld.dtos;
import jakarta.validation.constraints.*;

public record UsuarioCadastroDTO(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String senha,
        @NotBlank String nome
) {}
