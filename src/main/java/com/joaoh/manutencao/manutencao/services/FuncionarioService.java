package com.joaoh.manutencao.manutencao.services;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Funcionario;
import com.joaoh.manutencao.manutencao.domain.enums.TipoFuncionario;
import com.joaoh.manutencao.manutencao.repositories.FuncionarioRepository;
import com.joaoh.manutencao.manutencao.services.exceptions.DataIntegrityException;
import com.joaoh.manutencao.manutencao.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Funcionario findOne(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário " + id + " não encontrado"));
    }

    public Funcionario findByUsername(String user) {
        return funcionarioRepository.findByUsuario(user)
                .orElseThrow(() -> new ObjectNotFoundException("Funcionário " + user + " não encontrado"));
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    } 

    public Funcionario insert(Funcionario funcionario) {
        funcionario.setId(null);
        funcionario.setSenha(bCryptPasswordEncoder.encode(funcionario.getSenha()));
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Integer id, Funcionario funcionario) {
        this.findOne(id);
        funcionario.setId(id);

        return funcionarioRepository.save(funcionario);
    }

    public void delete(Integer id) {
        Funcionario funcionario = this.findOne(id);

        if (funcionario.getTipo() == TipoFuncionario.ADM) {
            throw new DataIntegrityException("Não é possível excluir o usuário administrador");
        }

        funcionarioRepository.delete(funcionario);
    }



}

