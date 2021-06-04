package com.joaoh.manutencao.manutencao.services;

import java.util.List;

import com.joaoh.manutencao.manutencao.domain.Cliente;
import com.joaoh.manutencao.manutencao.domain.Endereco;
import com.joaoh.manutencao.manutencao.domain.dto.ClienteDTO;
import com.joaoh.manutencao.manutencao.domain.dto.ClienteNewDTO;
import com.joaoh.manutencao.manutencao.repositories.ClienteRepository;
import com.joaoh.manutencao.manutencao.repositories.EnderecoRepository;
import com.joaoh.manutencao.manutencao.services.exceptions.DataIntegrityException;
import com.joaoh.manutencao.manutencao.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    public List<Cliente> findAll (){
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer clienteId) {
        return clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ObjectNotFoundException("Cliente id " + clienteId + " não encontrado"));
    }

    public Cliente insert(Cliente obj) {
        return clienteRepository.save(obj);
    }

    public Cliente insert(ClienteNewDTO objClienteDto) {
        Cliente cliente = this.fromDTO(objClienteDto);

        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEndereco());

        return cliente;
    }

    public Cliente update(Cliente obj) {
        return clienteRepository.save(obj);
    }

    public Cliente update(Integer clienteId, ClienteDTO objClienteDto) {
        Cliente cli = this.findById(clienteId);
        this.updateDatabase(cli, objClienteDto);

        return clienteRepository.save(cli);
    }

    public void delete(Integer clienteId) {
        Cliente obj = this.findById(clienteId);

        if (!obj.getOrdens().isEmpty()){
            throw new DataIntegrityException("Não é possível excluir um cliente que possui ordens de serviço");
        } else {
            clienteRepository.delete(obj);
        }
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Endereco end = new Endereco();
        
        end.setRua(objDto.getRua());
        end.setNumero(objDto.getNumero());
        end.setCidade(cidadeService.findById(objDto.getCidadeId()));
        
        Cliente cliente = new Cliente();
        end.setCliente(cliente);

        cliente.setNome(objDto.getNome());
        cliente.setEmail(objDto.getEmail());
        cliente.setTelefone(objDto.getTelefone());
        cliente.getEndereco().add(end);

        return cliente;
    }

    public void updateDatabase(Cliente cli, ClienteDTO objDto) {
        cli.setNome(objDto.getNome());
        cli.setEmail(objDto.getEmail());
        cli.setTelefone(objDto.getTelefone());
    }

    

    

}
