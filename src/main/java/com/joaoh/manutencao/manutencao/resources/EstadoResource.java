package com.joaoh.manutencao.manutencao.resources;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Cidade;
import com.joaoh.manutencao.manutencao.domain.Estado;
import com.joaoh.manutencao.manutencao.services.CidadeService;
import com.joaoh.manutencao.manutencao.services.EstadoService;
import com.joaoh.manutencao.manutencao.services.utils.URICreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/estados")
public class EstadoResource {
    
    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    //Estados
    @GetMapping
    public List<Estado> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Estado findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Void> insertEstado(@RequestBody Estado objEstado) {
        Estado obj = service.insert(objEstado);

        return ResponseEntity.created(URICreator.buildLocation(obj.getId())).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> updateEstado(@PathVariable(name = "id") Integer estadoId, @RequestBody Estado objEstado) {
        service.udpate(estadoId, objEstado);
        
        return ResponseEntity.ok().build(); 
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable(name = "id") Integer estadoId) {
        service.delete(estadoId);

        return ResponseEntity.ok().build();
    }

    //Cidades
    @GetMapping(value = "/{id}/cidades")
    public List<Cidade> findAllCitys(@PathVariable (name = "id") Integer estado_id) {
        return cidadeService.findCidades(estado_id);
    }    
    //
}
