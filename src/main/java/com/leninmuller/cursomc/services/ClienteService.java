package com.leninmuller.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.leninmuller.cursomc.domain.Cliente;
import com.leninmuller.cursomc.domain.Cliente;
import com.leninmuller.cursomc.dto.ClienteDTO;
import com.leninmuller.cursomc.repositories.ClienteRepository;
import com.leninmuller.cursomc.services.exceptions.DataIntegrityException;
import com.leninmuller.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {  
		Optional<Cliente> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}	


	public void delete(Integer id) {
		find(id);	
		try {
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque a entidades relacionadas");
		} 
	}	
	
	public List<Cliente> findAll(){
		return repo.findAll();		
	}
	
	public Page<Cliente> findPage(Integer page, Integer line, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, line);
		return repo.findAll(pageRequest);
	}
	
	public Cliente findDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}	
}
