package com.app.logap.dao;

import com.app.logap.model.ComplexoEolico;

public class ComplexoEolicoDAO extends GenericHibernateDAO<ComplexoEolico, Long>{

	public ComplexoEolicoDAO(Class<ComplexoEolico> clazz) {
		super(clazz);
	}

}
