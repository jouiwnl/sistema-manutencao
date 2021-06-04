package com.joaoh.manutencao.manutencao;

import java.time.LocalDate;
import java.util.Arrays;

import com.joaoh.manutencao.manutencao.domain.Cidade;
import com.joaoh.manutencao.manutencao.domain.Cliente;
import com.joaoh.manutencao.manutencao.domain.Endereco;
import com.joaoh.manutencao.manutencao.domain.Estado;
import com.joaoh.manutencao.manutencao.domain.OrdemDeServico;
import com.joaoh.manutencao.manutencao.domain.enums.EstadoOrdemServico;
import com.joaoh.manutencao.manutencao.domain.enums.TipoEquipamento;
import com.joaoh.manutencao.manutencao.repositories.CidadeRepository;
import com.joaoh.manutencao.manutencao.repositories.ClienteRepository;
import com.joaoh.manutencao.manutencao.repositories.EnderecoRepository;
import com.joaoh.manutencao.manutencao.repositories.EstadoRepository;
import com.joaoh.manutencao.manutencao.repositories.OrdemDeServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManutencaoApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private OrdemDeServicoRepository ordemDeServicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManutencaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Estado e1 = new Estado(null, "Santa Catarina", "SC");
		Estado e2 = new Estado(null, "São Paulo", "SP");
		estadoRepository.saveAll(Arrays.asList(e1, e2));

		//
		//

		Cidade c1 = new Cidade(null, "Criciuma", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		e1.getCidades().add(c1);
		e2.getCidades().add(c2);

		//
		//

		Cliente cli1 = new Cliente(null, "João Henrique", "991758280", "jou.098olo@gmail.com");
		clienteRepository.save(cli1);

		//
		//

		Endereco end1 = new Endereco(null, "Valdemira Izabel de souza", "12", c1, cli1);
		enderecoRepository.save(end1);
		c1.getEnderecos().add(end1);
		cli1.getEndereco().add(end1);

		//
		//

		OrdemDeServico ordem1 = new OrdemDeServico(null, EstadoOrdemServico.ABERTA , cli1, "Celular Samsung", TipoEquipamento.CELULAR, "Celular molhou", LocalDate.now());
		ordemDeServicoRepository.save(ordem1);
		cli1.getOrdens().add(ordem1);
		

	}	

}
