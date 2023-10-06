package com.antoniopgr.utils;

import jakarta.persistence.*;

public class EntityManagerHandler {

	private final EntityManagerFactory entity_manager_factory;
	private EntityManager entity_manager;
	private EntityTransaction entity_transaction;

	public EntityManagerHandler(String persistenceUnitName) {
		this.entity_manager_factory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	public void persistNCommit(Object o){
		beginTransaction();
		persist(o);
		commitTransaction();
		closeManager();
	}

	public void mergeNCommit(Object o){
		beginTransaction();
		merge(o);
		commitTransaction();
		closeManager();
	}

	public void removeNCommit(Object o){
		beginTransaction();
		remove(o);
		commitTransaction();
		closeManager();
	}

	public void persist(Object o){
		getManager().persist(o);
	}

	public void merge(Object o){
		getManager().merge(o);
	}

	public void remove(Object o){
		getManager().remove(o);
	}

	public void beginTransaction(){
		if(entity_manager == null){
			this.entity_manager = getFactory().createEntityManager();
		}
		if(entity_transaction == null){
			this.entity_transaction = getManager().getTransaction();
		}
		geTransaction().begin();
	}

	public void commitTransaction(){
		geTransaction().commit();
	}

	public void closeManager(){
		getManager().close();
		this.entity_transaction = null;
		this.entity_manager = null;
	}

	public <T> TypedQuery<T> createQuery(String jpql, Class<T> resultClass){
		beginTransaction();
		return entity_manager.createQuery(jpql, resultClass);
	}

	private EntityManagerFactory getFactory() {
		return entity_manager_factory;
	}

	private EntityManager getManager() {
		return entity_manager;
	}

	private EntityTransaction geTransaction() {
		return entity_transaction;
	}
}
