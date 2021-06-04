package com.joaoh.manutencao.manutencao.repositories;

import com.joaoh.manutencao.manutencao.domain.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    
}
