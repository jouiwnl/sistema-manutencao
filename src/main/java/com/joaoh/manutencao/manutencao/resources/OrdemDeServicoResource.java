package com.joaoh.manutencao.manutencao.resources;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.OrdemDeServico;
import com.joaoh.manutencao.manutencao.domain.dto.OrdemApproveDTO;
import com.joaoh.manutencao.manutencao.domain.dto.OrdemDeServicoDTO;
import com.joaoh.manutencao.manutencao.domain.dto.OrdemDeServicoNewDTO;
import com.joaoh.manutencao.manutencao.services.OrdemDeServicoService;
import com.joaoh.manutencao.manutencao.services.utils.URICreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordens")
public class OrdemDeServicoResource {
    
    @Autowired
    private OrdemDeServicoService service;

    @GetMapping
    public List<OrdemDeServico> findAll() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public OrdemDeServico findById(@PathVariable Integer id) {
        return service.findById(id);
    } 

    @PostMapping
    public ResponseEntity<Void> insertOrdemDeServico(@RequestBody OrdemDeServicoNewDTO objOrdem) {   
        OrdemDeServico obj = service.insert(objOrdem);

        return ResponseEntity.created(URICreator.buildLocation(obj.getId())).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrdemDeServico(@PathVariable(name = "id") Integer ordemId) {
        service.delete(ordemId);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> updateOrdemDeServico(@PathVariable(name = "id") Integer ordemId, @RequestBody OrdemDeServicoDTO objDto) {
        service.update(ordemId, objDto);
        
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}/ordemAprovada")
    public ResponseEntity<Void> updateOrdem(@PathVariable(name = "id") Integer ordemID, @RequestBody OrdemApproveDTO objDto) {
        service.updateApprove(ordemID, objDto);
        return ResponseEntity.ok().build();
    } 
    
}
