package br.org.serratec.backend.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.ecommerce.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
