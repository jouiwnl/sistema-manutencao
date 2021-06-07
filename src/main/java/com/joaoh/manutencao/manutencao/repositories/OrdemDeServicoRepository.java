package com.joaoh.manutencao.manutencao.repositories;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.OrdemDeServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Integer> {
    
    List<OrdemDeServico> findAllByClienteId(Integer clienteId);

	
}
