package br.org.serratec.backend.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
