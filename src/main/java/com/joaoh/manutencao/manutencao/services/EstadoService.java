package com.joaoh.manutencao.manutencao.services;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Estado;
import com.joaoh.manutencao.manutencao.repositories.EstadoRepository;
import com.joaoh.manutencao.manutencao.services.exceptions.DataIntegrityException;
import com.joaoh.manutencao.manutencao.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado findById(Integer estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new ObjectNotFoundException("Estado id " + estadoId + " não encontrado"));
    }

    public Estado insert(Estado objEstado) {
        objEstado.setId(null);
        objEstado.setSigla(objEstado.getSigla().toUpperCase());
        return estadoRepository.save(objEstado);
    }

    public Estado udpate(Integer estadoId, Estado objEstado) {
        this.findById(estadoId);

        objEstado.setId(estadoId);
        objEstado.setSigla(objEstado.getSigla().toUpperCase());

        return estadoRepository.save(objEstado);
    }

    public void delete(Integer estadoId) {
        Estado obj = this.findById(estadoId);

        if(!obj.getCidades().isEmpty()) {
            throw new DataIntegrityException("Não é possível deletar um estado que possui cidades");
        } else{
           estadoRepository.delete(obj);
        }
    }
}
