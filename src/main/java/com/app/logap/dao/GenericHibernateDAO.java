package com.app.logap.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import com.app.logap.utils.exeptions.ExceptionCustom;
import com.app.logap.utils.sessao.SessaoHibernateUtils;

public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDaoAPI<T, ID> {

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
		try {
			sessao.beginTranslaction();
			sessao.update(object);
			sessao.commitTranslaction();
		} catch (Exception e) {
			sessao.rollbackTranslaction();
			throw new ExceptionCustom("Erro ao atualizar entidade do banco", e);
		}
	}

	@Override
	public void remove(T object) {
		try {
			sessao.beginTranslaction();
			sessao.remove(object);
			sessao.commitTranslaction();
		} catch (Exception e) {
			sessao.rollbackTranslaction();
			throw new ExceptionCustom("Erro ao remover entidade do banco", e);
		}
	}

	@Override
	public T findById(ID id) {
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

	public SessaoHibernateUtils getSessao() {
		return sessao;
	}

}
