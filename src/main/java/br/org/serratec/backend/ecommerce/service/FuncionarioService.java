package br.org.serratec.backend.ecommerce.service;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.model.Funcionario;
import br.org.serratec.backend.ecommerce.model.dto.FuncionarioDTO;
import br.org.serratec.backend.ecommerce.repository.FuncionarioRepository;
import br.org.serratec.backend.ecommerce.service.mapper.FuncionarioMapper;

@Service

public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	
	@Autowired
	FuncionarioMapper funcionarioMapper;
	
	private Funcionario findBy(Integer id) throws DataNotFoundException {
		Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

		if (optionalFuncionario.isPresent()) {
			return optionalFuncionario.get();
		}
		throw new DataNotFoundException(String.format("Funcionario com id %d não encontrado", id)) ;		
	}

	@Transactional
	public FuncionarioDTO inserir(@Valid FuncionarioDTO funcionarioDTO) {
		Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
		Funcionario funcionarioSalvoNoBd = funcionarioRepository.save(funcionario);
		return funcionarioMapper.toDTO(funcionarioSalvoNoBd);
	}

	public List<FuncionarioDTO> listar() {
		return funcionarioRepository.findAll()
			.stream()
			.map(funcionarioMapper::toDTO)
			.collect(toList());
	}

	public FuncionarioDTO listarPorId(Integer id) throws DataNotFoundException {
		return funcionarioMapper.toDTO(findBy(id));
	}

	@Transactional
	public FuncionarioDTO substituir(Integer id, @Valid FuncionarioDTO funcionarioDTO)throws  DataNotFoundException {
		Funcionario funcionarioNoBanco = findBy(id);

		if (funcionarioDTO.getNome() != null) {
			funcionarioNoBanco.setNome(funcionarioDTO.getNome());
		}

		if (funcionarioDTO.getCpf() != null) {
			funcionarioNoBanco.setCpf(funcionarioDTO.getCpf());
		}

		return funcionarioMapper.toDTO(funcionarioRepository.save(funcionarioNoBanco));
	}

	@Transactional
	public void deletar(Integer id) throws DataNotFoundException {
		Funcionario funcionarioNoBanco = findBy(id);
		funcionarioRepository.delete(funcionarioNoBanco);
	}

}