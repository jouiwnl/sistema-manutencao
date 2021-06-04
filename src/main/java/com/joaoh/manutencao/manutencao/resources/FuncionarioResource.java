package com.joaoh.manutencao.manutencao.resources;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Funcionario;
import com.joaoh.manutencao.manutencao.services.FuncionarioService;
import com.joaoh.manutencao.manutencao.services.utils.URICreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/funcionarios")
@PreAuthorize("hasAnyHole('ADMIN')")
public class FuncionarioResource {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> findAll() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    public Funcionario findOne(@PathVariable Integer id) {
        return funcionarioService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<Funcionario> add(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.insert(funcionario);

        return ResponseEntity.created(URICreator.buildLocation(funcionarioSalvo.getId())).body(funcionarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        funcionarioService.update(id, funcionario);
        
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        funcionarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
