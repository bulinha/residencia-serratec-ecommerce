package br.org.serratec.backend.ecommerce.service.mapper;

import br.org.serratec.backend.ecommerce.model.Produto;
import br.org.serratec.backend.ecommerce.model.dto.ProdutoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring",uses={CategoriaMapper.class})
public interface ProdutoMapper{

    @Mapping(source="categoria.id", target = "idCategoria")
    @Mapping(source="categoria.nome", target = "nomeCategoria")
    @Mapping(source="funcionario.id", target ="idFuncionario")
    @Mapping(source="funcionario.nome", target="nomeFuncionario")
    ProdutoDTO toDTO(Produto produto);

    @Mapping(source="idCategoria", target="categoria.id")
    @Mapping(source="idFuncionario", target="funcionario.id")
    @Mapping(target="itens", ignore=true)
    Produto toEntity(ProdutoDTO produto);

}