package br.org.serratec.backend.ecommerce.service.mapper;

import org.mapstruct.Mapper;

import br.org.serratec.backend.ecommerce.model.Funcionario;
import br.org.serratec.backend.ecommerce.model.dto.FuncionarioDTO;

@Mapper(componentModel="spring")
public interface FuncionarioMapper {
    FuncionarioDTO toDTO(Funcionario funcionario);
    Funcionario toEntity(FuncionarioDTO funcionarioDTO);
}
