package com.joaoh.manutencao.manutencao.repositories;

import com.joaoh.manutencao.manutencao.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
}
