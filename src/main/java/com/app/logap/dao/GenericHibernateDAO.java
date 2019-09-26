package com.app.logap.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import com.app.logap.hibernate.utils.SessaoHibernateUtils;

public class GenericHibernateDAO<T, ID extends Serializable> implements GenericDaoAPI<T, ID> {

	private SessaoHibernateUtils sessao;

	public GenericHibernateDAO() {
		sessao = new SessaoHibernateUtils("app-logap");
	}

	@Override
	public void save(T object) {
		try {
			sessao.beginTranslaction();
			sessao.persistObj(object);
			sessao.commitTranslaction();
		} catch (Exception e) {
			sessao.rollbackTranslaction();
			throw new PersistenceException("Erro ao persistir entidade", e);
		} finally {
			System.out.println("finalizou");
			sessao.close();
		}
	}

	@Override
	public void update(T object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T object) {
		// TODO Auto-generated method stub

	}

	@Override
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
