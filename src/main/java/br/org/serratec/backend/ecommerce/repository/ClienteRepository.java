package br.org.serratec.backend.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.ecommerce.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
