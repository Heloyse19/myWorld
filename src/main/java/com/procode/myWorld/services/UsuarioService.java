package com.procode.myWorld.services;

import com.procode.myWorld.dtos.UsuarioCadastroDTO;
import com.procode.myWorld.models.Usuario;
import com.procode.myWorld.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(UsuarioCadastroDTO usuarioCadastroDTO){
        if(usuarioRepository.findByEmail(usuarioCadastroDTO.email()) != null){
            throw new RuntimeException("Usuario já cadastrado");
        }

        Usuario user = new Usuario();
        user.setEmail(usuarioCadastroDTO.email());
        user.setNome(usuarioCadastroDTO.nome());
        user.setSenha(passwordEncoder.encode(usuarioCadastroDTO.senha()));
        user.setAtivo(true);

        return usuarioRepository.save(user);
    }

    public Usuario autenticar(String email, String senha){
        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario == null){
            throw new RuntimeException("Usuario não cadastrado!");
        }

        if(!passwordEncoder.matches(senha, usuario.getSenha())){
            throw new RuntimeException("Senha incorreta!");
        }

        if(!usuario.isAtivo()){
            throw new RuntimeException("Usuario inativo!");
        }

        return usuario;
    }

}
