package com.joaoh.manutencao.manutencao.repositories;

import java.util.Optional;

import com.joaoh.manutencao.manutencao.domain.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByUser(String user);
}
