package com.app.logap.proxy;

import com.app.logap.dao.ParqueEolicoDAO;
import com.app.logap.model.ParqueEolico;


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
		repository.delete(objeto);
	}
	
	public ParqueEolico buscar(Long id) {
		return repository.findById(id);
	}
	
//	public List<ParqueEolico> buscar() {
//		return repository.findAll();
//	}
}
