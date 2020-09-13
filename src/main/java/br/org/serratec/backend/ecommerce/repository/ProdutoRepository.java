package br.org.serratec.backend.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
