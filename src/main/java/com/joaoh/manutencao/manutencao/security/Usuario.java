package com.joaoh.manutencao.manutencao.security;

import com.joaoh.manutencao.manutencao.domain.enums.TipoFuncionario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class Usuario implements UserDetails {
    private Integer id;

    private String user;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public Usuario() {
    }

    public Usuario(Integer id, String user, String password, TipoFuncionario tipoFuncionario) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(tipoFuncionario.getDesc()));
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(TipoFuncionario tipoFuncionario) {
        return this.getAuthorities().contains(new SimpleGrantedAuthority(tipoFuncionario.getDesc()));
    }
}
