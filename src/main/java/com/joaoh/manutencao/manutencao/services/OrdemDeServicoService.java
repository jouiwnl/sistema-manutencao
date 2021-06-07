package com.joaoh.manutencao.manutencao.services;

import java.time.LocalDate;
import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Cliente;
import com.joaoh.manutencao.manutencao.domain.OrdemDeServico;
import com.joaoh.manutencao.manutencao.domain.dto.OrdemApproveDTO;
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

    public OrdemDeServico insert(OrdemDeServicoNewDTO ordemDto) {
        OrdemDeServico ordem = this.fromDTO(ordemDto);
        
        ordem = ordemDeServicoRepository.save(ordem);

        return ordem;
    }

    public OrdemDeServico update(Integer ordemId, OrdemDeServicoDTO ordemDto) {
        OrdemDeServico ordem = this.findById(ordemId);
        this.updateDatabase(ordem, ordemDto);

        return ordemDeServicoRepository.save(ordem);
    }

    public OrdemDeServico updateApprove(Integer ordemId, OrdemApproveDTO ordemDto) {
        OrdemDeServico ordem = this.fromDTOapprove(ordemDto, ordemId);

        ordem = ordemDeServicoRepository.save(ordem);
        return ordem;
    }

    public void delete(Integer ordemId) {
        OrdemDeServico ordem = this.findById(ordemId);

        if(ordem.getStatus() == EstadoOrdemServico.ABERTA || ordem.getStatus() == EstadoOrdemServico.ATENDIMENTO) {
            throw new DataIntegrityException("Não é possível excluir um ordem de serviço não finalizada");
        } else {
            ordemDeServicoRepository.delete(ordem);
        }
    }

    public OrdemDeServico fromDTOapprove(OrdemApproveDTO objDto, Integer ordemId){
        OrdemDeServico ordem = this.findById(ordemId);
        Cliente cliente = ordem.getCliente();

        ordem.setStatus(EstadoOrdemServico.APROVADA);
        ordem.setCliente(cliente);

        return ordem;
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
        ordem.setNomeEquipamento(objDto.getNomeEquipamento());
        ordem.setDetalhe(objDto.getDetalhe());
        ordem.setTipoEquipamento(objDto.getTipoEquipamento());
        ordem.setStatus(objDto.getStatus());
    }

    
}
