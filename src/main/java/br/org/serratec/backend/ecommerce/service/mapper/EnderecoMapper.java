package br.org.serratec.backend.ecommerce.service.mapper;

import org.mapstruct.Mapper;

import br.org.serratec.backend.ecommerce.model.Endereco;
import br.org.serratec.backend.ecommerce.model.dto.EnderecoDTO;

@Mapper(componentModel="spring")
public interface EnderecoMapper {
    EnderecoDTO toDTO(Endereco endereco);
    Endereco toEntity(EnderecoDTO enderecoDTO);
}
