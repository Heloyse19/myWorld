package com.procode.myWorld.dtos;

public record LoginResponseDTO(
        Long id,
        String email,
        String nome,
        String token
) {}
