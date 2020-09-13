package br.org.serratec.backend.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.backend.ecommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
