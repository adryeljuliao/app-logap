package com.app.logap.repositories;

import com.app.logap.models.ParqueEolico;

public class ParqueEolicoDAO extends GenericHibernateDAO<ParqueEolico, Long>{

	public ParqueEolicoDAO(Class<ParqueEolico> clazz) {
		super(clazz);
	}

}
