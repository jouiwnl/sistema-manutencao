package com.joaoh.manutencao.manutencao.services;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Cidade;
import com.joaoh.manutencao.manutencao.domain.Estado;
import com.joaoh.manutencao.manutencao.repositories.CidadeRepository;
import com.joaoh.manutencao.manutencao.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;


    public List<Cidade> findCidades(Integer estado_id) {
        return cidadeRepository.findCidades(estado_id);
    }

    public Cidade findById(Integer id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cidade id " + id + " não encontrado"));
    }

    public Cidade insert(Integer estadoId, Cidade objCidade) {
        Estado objEstado = estadoService.findById(estadoId);
        objCidade.setId(null);
        objCidade.setEstado(objEstado);

        objEstado.getCidades().add(objCidade);
        estadoService.udpate(estadoId, objEstado); 

        objCidade = cidadeRepository.save(objCidade);
        return objCidade;
    }

}
