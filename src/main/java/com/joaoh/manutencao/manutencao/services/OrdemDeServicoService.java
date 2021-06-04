package com.joaoh.manutencao.manutencao.services;

import java.time.LocalDate;
import java.util.List;

import com.joaoh.manutencao.manutencao.domain.OrdemDeServico;
import com.joaoh.manutencao.manutencao.domain.dto.OrdemDeServicoDTO;
import com.joaoh.manutencao.manutencao.domain.dto.OrdemDeServicoNewDTO;
import com.joaoh.manutencao.manutencao.domain.enums.EstadoOrdemServico;
import com.joaoh.manutencao.manutencao.repositories.OrdemDeServicoRepository;
import com.joaoh.manutencao.manutencao.services.exceptions.DataIntegrityException;
import com.joaoh.manutencao.manutencao.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdemDeServicoService {

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    private ClienteService clienteService;

    public List<OrdemDeServico> findAll() {
        return ordemDeServicoRepository.findAll();
    }
    
    public OrdemDeServico findById(Integer ordemId) {
        return ordemDeServicoRepository.findById(ordemId)
                .orElseThrow(() -> new ObjectNotFoundException("Ordem de serviço id " + ordemId + " não encontrado"));
    }

    public OrdemDeServico insert(OrdemDeServicoNewDTO objOrdemDto) {
        OrdemDeServico ordem = this.fromDTO(objOrdemDto);
        
        ordem = ordemDeServicoRepository.save(ordem);

        return ordem;
    }

    public OrdemDeServico update(Integer ordemId, OrdemDeServicoDTO objDto) {
        OrdemDeServico ordem = this.findById(ordemId);
        this.updateDatabase(ordem, objDto);

        return ordemDeServicoRepository.save(ordem);
    }

    public void delete(Integer ordemId) {
        OrdemDeServico ordem = this.findById(ordemId);

        if(ordem.getStatus() == EstadoOrdemServico.ABERTA || ordem.getStatus() == EstadoOrdemServico.ATENDIMENTO) {
            throw new DataIntegrityException("Não é possível excluir um ordem de serviço não finalizada");
        } else {
            ordemDeServicoRepository.delete(ordem);
        }
    }

    public OrdemDeServico fromDTO(OrdemDeServicoNewDTO objDto) {
        OrdemDeServico ordem = new OrdemDeServico();

        ordem.setId(null);
        ordem.setCliente(clienteService.findById(objDto.getClienteId()));
        ordem.setDetalhe(objDto.getDetalhe());
        ordem.setNomeEquipamento(objDto.getNomeEquipamento());
        ordem.setStatus(EstadoOrdemServico.ABERTA);
        ordem.setTipoEquipamento(objDto.getTipoEquipamento());
        ordem.setData(LocalDate.now());

        return ordem;
    }

    public void updateDatabase(OrdemDeServico ordem, OrdemDeServicoDTO objDto) {
        ordem.setStatus(objDto.getStatus());
    }

    
}
