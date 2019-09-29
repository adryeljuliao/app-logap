package com.app.logap.proxys;

import java.util.List;

import com.app.logap.models.ParqueEolico;
import com.app.logap.repositories.ParqueEolicoDAO;


public class ParqueEolicoProxy {

	private ParqueEolicoDAO repository;
	
	public ParqueEolicoProxy() {
		repository = new ParqueEolicoDAO(ParqueEolico.class);
	}
	
	public void salvar(ParqueEolico objeto) {
		repository.save(objeto);
	}
	
	public void atualizar(ParqueEolico objeto) {
		repository.update(objeto);
	}
	
	public void remover(ParqueEolico objeto) {
		repository.remove(objeto);
	}
	
	public ParqueEolico buscar(Long id) {
		return repository.findById(id);
	}
	
	public List<ParqueEolico> buscar(ParqueEolico parqueEolico) {
		return repository.findByName(parqueEolico.getNome());
	}
	
	public List<ParqueEolico> buscarTodos(){
		return repository.findAll();
	}
}
