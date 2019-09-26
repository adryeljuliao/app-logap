package com.app.logap.hibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessaoHibernateUtils {

	private String persistenceXML;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public SessaoHibernateUtils(String persistenceXML) {
		this.persistenceXML = persistenceXML;
		entityManagerFactory = Persistence.createEntityManagerFactory(this.persistenceXML);
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void beginTranslaction() {
		entityManager.getTransaction().begin();
	}
	
	public void commitTranslaction() {
		entityManager.getTransaction().commit();
	}
	
	public void persistObj(Object obj) {
		entityManager.persist(obj);
	}
	
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public void rollbackTranslaction() {
		entityManager.getTransaction().rollback();
	}
}
