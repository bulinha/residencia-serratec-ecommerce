package br.org.serratec.backend.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.org.serratec.backend.ecommerce.model.pk.PedidoItemPk;
import lombok.Data;

@Entity
@Table(name = "pedido_item")
@Data
public class PedidoItem {

	@EmbeddedId
	private PedidoItemPk id = new PedidoItemPk();

	@NotNull
	@Min(1)
	@Column(name = "quantidade", nullable = false)
	private Integer qtdItens;

	@NotNull
	@Column(name="valor")
	@Min(0)
	private Double valor;

	@Transient
	public Double getSubTotal() {
		return valor * qtdItens;
	}

}
