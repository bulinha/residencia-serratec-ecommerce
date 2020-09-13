package br.org.serratec.backend.ecommerce.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.org.serratec.backend.ecommerce.model.Pedido;
import br.org.serratec.backend.ecommerce.model.dto.PedidoDTO;
import br.org.serratec.backend.ecommerce.model.dto.PedidoSimplesDTO;

@Mapper(componentModel="spring",uses={PedidoItemMapper.class, ClienteMapper.class})
public interface PedidoMapper {
    @Mapping(source="cliente.id", target="idCliente")
    @Mapping(source="cliente.nome", target="nomeCliente")
    PedidoDTO toDTO(Pedido pedido);

    @Mapping(source="cliente.id", target="idCliente")
    @Mapping(source="cliente.nome", target="nomeCliente")
    PedidoSimplesDTO toSimplesDTO(Pedido pedido);
    
    @Mapping(source="idCliente", target="cliente.id")
    @Mapping(target="total", ignore=true)
    Pedido toEntity(PedidoDTO pedido);
    
}
