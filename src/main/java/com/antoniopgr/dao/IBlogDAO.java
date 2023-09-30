package com.antoniopgr.dao;

import com.antoniopgr.models.Post;
import com.antoniopgr.utils.EntityManagerHandler;

public class IBlogDAO {

	EntityManagerHandler em_handler = new EntityManagerHandler("blog_sql");

	public void cadastrar(Object object){
		this.em_handler.persistNCommit(object);
	}

	public void atualizar(Object object) {
		this.em_handler.mergeNCommit(object);
	}

	public void deletar(Object object) {
		this.em_handler.removeNCommit(object);
	}

}
