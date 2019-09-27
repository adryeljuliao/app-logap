package com.app.logap.utils.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SessaoHibernateUtils {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public SessaoHibernateUtils() {
		entityManagerFactory = Persistence.createEntityManagerFactory("app-logap");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void beginTranslaction() {
		entityTransaction.begin();
	}

	public void commitTranslaction() {
		entityTransaction.commit();
	}

	public void persistObj(Object obj) {
		entityManager.persist(obj);
	}
	
	public void update(Object obj) {
		entityManager.merge(obj);
	}
	
	public void remove(Object obj) {
		entityManager.remove(obj);
	}
	
	public List<?> findAll(String sql) {
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

	public void close() {
		entityManager.close();
	}

	public void rollbackTranslaction() {
		entityManager.getTransaction().rollback();
	}
}
