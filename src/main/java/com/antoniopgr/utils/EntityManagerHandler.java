package com.antoniopgr.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityManagerHandler {

	private final EntityManagerFactory entity_manager_factory;
	private final EntityManager entity_manager;
	private final EntityTransaction entity_transaction;

	public EntityManagerHandler(String persistenceUnitName) {
		this.entity_manager_factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		this.entity_manager = entity_manager_factory.createEntityManager();
		this.entity_transaction = entity_manager.getTransaction();
	}

	public void persistNCommit(Object o){
		persist(o);
		commitTransaction();
	}

	public void persist(Object o){
		getManager().persist(o);
	}

	public void beginTransaction(){
		geTransaction().begin();
	}

	public void commitTransaction(){
		geTransaction().commit();
	}

	public void closeManager(){
		getManager().close();
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
