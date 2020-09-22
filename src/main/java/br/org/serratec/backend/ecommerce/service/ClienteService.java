package br.org.serratec.backend.ecommerce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.ecommerce.exception.DataNotFoundException;
import br.org.serratec.backend.ecommerce.model.Cliente;
import br.org.serratec.backend.ecommerce.model.dto.ClienteDTO;
import br.org.serratec.backend.ecommerce.repository.ClienteRepository;
import br.org.serratec.backend.ecommerce.service.mapper.ClienteMapper;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteMapper clienteMapper;

	private Cliente findBy(Integer id) throws DataNotFoundException {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (optionalCliente.isPresent()) {
			return optionalCliente.get();
		}
		throw new DataNotFoundException(String.format("Cliente com id %d não encontrado", id)) ;
	}
	

	@Transactional
	public ClienteDTO inserir(ClienteDTO clienteDTO) {
		Cliente cliente = clienteMapper.toEntity(clienteDTO);
		cliente.getEndereco().setCliente(cliente);
		Cliente clienteSalvoNoBd = clienteRepository.save(cliente);
		return clienteMapper.toDTO(clienteSalvoNoBd);
	
	}
	
	public List<ClienteDTO> listar() {
		return clienteRepository.findAll()
			.stream()
			.map(clienteMapper::toDTO)
			.collect(toList());
	}
	
	public ClienteDTO listarPorId(Integer id) throws DataNotFoundException {
		return clienteMapper.toDTO(findBy(id));
	}
	
	@Transactional
	public ClienteDTO substituir(Integer id, @Valid ClienteDTO clienteDTO) throws DataNotFoundException {
		Cliente clienteNoBanco = findBy(id);
		
		if(clienteDTO.getNome() != null) {
			clienteNoBanco.setNome(clienteDTO.getNome());
		}
		if(clienteDTO.getUsuario() != null) {
			clienteNoBanco.setUsuario(clienteDTO.getUsuario());
		}
		if(clienteDTO.getCpf() != null) {
			clienteNoBanco.setCpf(clienteDTO.getCpf());
		}
		if(clienteDTO.getEmail() != null) {
			clienteNoBanco.setEmail(clienteDTO.getEmail());
		}
		if(clienteDTO.getDataNascimento() != null) {
			clienteNoBanco.setDataNascimento(clienteDTO.getDataNascimento());
		}
		if (clienteDTO.getEndereco()!=null){
			if (clienteDTO.getEndereco().getRua()!=null) {
				clienteNoBanco.getEndereco().setRua(clienteDTO.getEndereco().getRua());
			}
			if (clienteDTO.getEndereco().getNumero()!=null) {
				clienteNoBanco.getEndereco().setNumero(clienteDTO.getEndereco().getNumero());
			}
			if (clienteDTO.getEndereco().getComplemento()!=null) {
				clienteNoBanco.getEndereco().setComplemento(clienteDTO.getEndereco().getComplemento());
			}
			if (clienteDTO.getEndereco().getBairro()!=null) {
				clienteNoBanco.getEndereco().setBairro(clienteDTO.getEndereco().getBairro());
			}
			if (clienteDTO.getEndereco().getCidade()!=null) {
				clienteNoBanco.getEndereco().setCidade(clienteDTO.getEndereco().getCidade());
			}
			if (clienteDTO.getEndereco().getCep()!=null) {
				clienteNoBanco.getEndereco().setCep(clienteDTO.getEndereco().getCep());
			}
			if (clienteDTO.getEndereco().getEstado()!=null) {
				clienteNoBanco.getEndereco().setEstado(clienteDTO.getEndereco().getEstado());
			}
		}
		return clienteMapper.toDTO(clienteRepository.save(clienteNoBanco));
	}
	
	@Transactional
	public void deletar(Integer id) throws DataNotFoundException {
		Cliente clienteNoBanco = findBy(id);
		clienteRepository.delete(clienteNoBanco);
	}
}

