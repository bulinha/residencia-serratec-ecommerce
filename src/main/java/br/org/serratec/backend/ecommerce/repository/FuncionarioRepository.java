package br.org.serratec.backend.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.ecommerce.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
