package br.org.serratec.backend.ecommerce.service.mapper;

import org.mapstruct.Mapper;

import br.org.serratec.backend.ecommerce.model.Cliente;
import br.org.serratec.backend.ecommerce.model.dto.ClienteDTO;

@Mapper(componentModel="spring",uses = EnderecoMapper.class)
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    
}
