package com.joaoh.manutencao.manutencao.repositories;

import com.joaoh.manutencao.manutencao.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
