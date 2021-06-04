package com.joaoh.manutencao.manutencao.services;

import com.joaoh.manutencao.manutencao.domain.Funcionario;
import com.joaoh.manutencao.manutencao.security.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public UserDetails loadUserByUsername(String obj) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioService.findByUsername(obj);

        return new Usuario(funcionario.getId(), funcionario.getUser(), funcionario.getSenha(), funcionario.getTipo());
    }
}

