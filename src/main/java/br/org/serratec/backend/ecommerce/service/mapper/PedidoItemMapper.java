package br.org.serratec.backend.ecommerce.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.org.serratec.backend.ecommerce.model.PedidoItem;
import br.org.serratec.backend.ecommerce.model.dto.PedidoItemDTO;

@Mapper(componentModel="spring",uses= ProdutoMapper.class)
public interface PedidoItemMapper {
   @Mapping(source="id.produto.id", target="idProduto") 
   @Mapping(source="id.produto.nome", target="nomeProduto") 
   PedidoItemDTO toDTO(PedidoItem pedidoItem);
   @Mapping(source="idProduto", target="id.produto.id")
   PedidoItem toEntity(PedidoItemDTO pedidoItemDTO);

    
}
