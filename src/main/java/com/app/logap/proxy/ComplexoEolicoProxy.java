package com.app.logap.proxy;

import java.util.List;

import com.app.logap.dao.ComplexoEolicoDAO;
import com.app.logap.model.ComplexoEolico;

public class ComplexoEolicoProxy {

	private ComplexoEolicoDAO repository;

	public ComplexoEolicoProxy() {
		repository = new ComplexoEolicoDAO(ComplexoEolico.class);
	}

	public void salvar(ComplexoEolico objeto) {
		repository.save(objeto);
	}

	public void atualizar(ComplexoEolico objeto) {
		repository.update(objeto);
	}

	public void remover(ComplexoEolico objeto) {
		repository.delete(objeto);
	}

	public ComplexoEolico buscar(Long id) {
		return repository.findById(id);
	}

	public List<ComplexoEolico> buscarTodos() {
		return repository.findAll();
	}

}
