package com.joaoh.manutencao.manutencao.config;

import com.joaoh.manutencao.manutencao.domain.Funcionario;
import com.joaoh.manutencao.manutencao.domain.enums.TipoFuncionario;
import com.joaoh.manutencao.manutencao.repositories.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminConfig {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private BCryptPasswordEncoder crypt;
    
    @Bean
    public void verifyAdmin(){
        if (!funcionarioRepository.findByUsuario("admin").isPresent()) {
            Funcionario admin = new Funcionario("admin", "admin", 
            crypt.encode("admin"), TipoFuncionario.ADM);

            funcionarioRepository.save(admin); 
        } else {
           System.out.println("Funcionario não encontrado"); 
        }
        
    }

}
