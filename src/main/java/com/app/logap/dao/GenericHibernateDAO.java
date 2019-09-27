package com.app.logap.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import com.app.logap.utils.exeptions.ExceptionCustom;
import com.app.logap.utils.hibernate.SessaoHibernateUtils;

public class GenericHibernateDAO<T, ID extends Serializable> implements GenericDaoAPI<T, ID> {

	private SessaoHibernateUtils sessao;

	private Class<T> clazz;

	public GenericHibernateDAO(Class<T> clazz) {
		sessao = new SessaoHibernateUtils();
		this.clazz = clazz;
	}

	@Override
	public void save(T object) {
		try {
			sessao.beginTranslaction();
			sessao.persistObj(object);
			sessao.commitTranslaction();
		} catch (Exception e) {
			sessao.rollbackTranslaction();
			throw new ExceptionCustom("Erro ao salvar entidade no banco", e);
		}
	}

	@Override
	public void update(T object) {
		novaSessao();
		try {
			sessao.beginTranslaction();
			sessao.update(object);
			sessao.commitTranslaction();
		} catch (Exception e) {
			sessao.beginTranslaction();
			sessao.rollbackTranslaction();
			sessao.commitTranslaction();
			throw new PersistenceException("Erro ao persistir entidade", e);
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("from ");
		sql.append(clazz.getSimpleName());
		return sessao.getEntityManager().createQuery(sql.toString()).getResultList();
	}

	private void novaSessao() {
		sessao = new SessaoHibernateUtils();
	}

}
