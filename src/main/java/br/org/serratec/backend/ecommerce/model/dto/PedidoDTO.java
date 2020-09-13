package br.org.serratec.backend.ecommerce.model.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.serratec.backend.ecommerce.enums.PedidoStatus;
import lombok.Data;

@Data
public class PedidoDTO {

	private Integer id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Date dataPedido;

	private PedidoStatus pedidoStatus;

	private Integer idCliente;
	private String nomeCliente;

	@Valid
	private Set<PedidoItemDTO> itens = new HashSet<>();

    private Double total;


}
