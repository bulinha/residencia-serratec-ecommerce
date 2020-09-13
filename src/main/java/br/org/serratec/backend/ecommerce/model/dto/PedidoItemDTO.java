package br.org.serratec.backend.ecommerce.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PedidoItemDTO {

	private Integer idProduto;

	private String nomeProduto;

	@NotNull
	@Min(1)
	private Integer qtdItens;

	@NotNull
	@Min(0)
	private Double valor;

	private Double subTotal;


}
