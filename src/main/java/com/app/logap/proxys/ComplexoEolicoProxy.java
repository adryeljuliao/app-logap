package com.app.logap.proxys;

import java.util.List;

import com.app.logap.models.ComplexoEolico;
import com.app.logap.repositories.ComplexoEolicoDAO;

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
		repository.remove(objeto);
	}

	public List<ComplexoEolico> buscar(String nome) {
		return repository.findByName(nome);
	}

	public List<ComplexoEolico> buscarTodos() {
		return repository.findAll();
	}

}
