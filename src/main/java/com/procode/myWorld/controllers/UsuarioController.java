package com.procode.myWorld.controllers;

import com.procode.myWorld.dtos.LoginResponseDTO;
import com.procode.myWorld.dtos.UsuarioCadastroDTO;
import com.procode.myWorld.dtos.UsuarioLoginDTO;
import com.procode.myWorld.models.Usuario;
import com.procode.myWorld.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO usuarioDTO){
        try{
            Usuario usuarioSalvo = usuarioService.cadastrar(usuarioDTO);
            return ResponseEntity.ok(usuarioSalvo);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UsuarioLoginDTO loginDTO){
        Usuario usuario = usuarioService.autenticar(loginDTO.email(), loginDTO.senha());

        LoginResponseDTO response = new LoginResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNome(),
                "token"

        );
        return ResponseEntity.ok(response);
    }
}
