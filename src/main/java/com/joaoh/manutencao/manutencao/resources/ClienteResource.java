package com.joaoh.manutencao.manutencao.resources;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Cliente;
import com.joaoh.manutencao.manutencao.domain.OrdemDeServico;
import com.joaoh.manutencao.manutencao.domain.dto.ClienteDTO;
import com.joaoh.manutencao.manutencao.domain.dto.ClienteNewDTO;
import com.joaoh.manutencao.manutencao.repositories.OrdemDeServicoRepository;
import com.joaoh.manutencao.manutencao.services.ClienteService;
import com.joaoh.manutencao.manutencao.services.utils.URICreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    
    @Autowired
    private ClienteService service;

    @Autowired
    private OrdemDeServicoRepository ordemRepository;

    
    @GetMapping
    public List<Cliente> FindAll() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public Cliente findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Void> inserCliente(@RequestBody ClienteNewDTO objClienteDto) {
        Cliente cliente = service.insert(objClienteDto);
        return ResponseEntity.created(URICreator.buildLocation(cliente.getId())).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable(name = "id") Integer clienteId, @RequestBody ClienteDTO objClienteDto) {
        service.update(clienteId, objClienteDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable(name = "id") Integer clienteId) {
        service.delete(clienteId);

        return ResponseEntity.ok().build();
    }

    //ORDENS DE SERVICO

    @GetMapping(value="/{id}/ordens")
    public ResponseEntity<List<OrdemDeServico>> findAllOrdensCliente(@PathVariable(name = "id") Integer clienteId) {
        List<OrdemDeServico> ordens = ordemRepository.findAllByClienteId(clienteId);
        return ResponseEntity.ok().body(ordens);
    }
    
}
