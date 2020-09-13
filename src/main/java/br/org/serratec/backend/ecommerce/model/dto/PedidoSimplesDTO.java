package br.org.serratec.backend.ecommerce.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.serratec.backend.ecommerce.enums.PedidoStatus;
import lombok.Data;

@Data
public class PedidoSimplesDTO {

	private Integer id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Date dataPedido;

	private PedidoStatus pedidoStatus;

	private Integer idCliente;
	private String nomeCliente;


}
