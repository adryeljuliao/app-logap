package com.app.logap.proxy;

import com.app.logap.dao.ComplexoEolicoDAO;
import com.app.logap.model.ComplexoEolico;


public class ComplexoEolicoProxy {

	private ComplexoEolicoDAO repository;
	
	public ComplexoEolicoProxy() {
		repository = new ComplexoEolicoDAO();
	}
	
	public void salvar(ComplexoEolico objeto) {
		repository.save(objeto);
	}
}
