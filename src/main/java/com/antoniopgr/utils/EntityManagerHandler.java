package com.antoniopgr.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

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

	public void persist(Object o){
		getManager().persist(o);
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

	public EntityManagerFactory getFactory() {
		return entity_manager_factory;
	}

	public EntityManager getManager() {
		return entity_manager;
	}

	public EntityTransaction geTransaction() {
		return entity_transaction;
	}
}
