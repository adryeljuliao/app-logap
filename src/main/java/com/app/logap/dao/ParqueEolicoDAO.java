package com.app.logap.dao;

import com.app.logap.model.ParqueEolico;

public class ParqueEolicoDAO extends GenericHibernateDAO<ParqueEolico, Long>{

	public ParqueEolicoDAO(Class<ParqueEolico> clazz) {
		super(clazz);
	}

}
