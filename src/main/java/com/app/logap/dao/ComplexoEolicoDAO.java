package com.app.logap.dao;

import java.util.List;

import com.app.logap.model.ComplexoEolico;

public class ComplexoEolicoDAO extends GenericHibernateDAO<ComplexoEolico, Long>{

	public ComplexoEolicoDAO(Class<ComplexoEolico> clazz) {
		super(clazz);
	}

//	@SuppressWarnings("unchecked")
//	public List<ComplexoEolico> buscarPorNome(ComplexoEolico complexoEolico) {
//		StringBuilder sql = new StringBuilder("from ");
//		sql.append(ComplexoEolico.class.getSimpleName());
//		sql.append(" c ");
//		sql.append("where ");
//		sql.append("c.nome =:nome");
//		
//		return getSessao().getEntityManager().createQuery(sql.toString()).setParameter("nome", complexoEolico.getNome()).getResultList();
//	}
}
