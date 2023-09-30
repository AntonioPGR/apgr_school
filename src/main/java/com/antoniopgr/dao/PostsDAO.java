package com.antoniopgr.dao;

import com.antoniopgr.models.Post;
import com.antoniopgr.utils.EntityManagerHandler;

import java.util.List;

public class PostsDAO extends IBlogDAO{

	EntityManagerHandler em_handler = new EntityManagerHandler("blog_sql");

	public boolean create(Post post){
		try{
			em_handler.persistNCommit(post);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public List<Post> getLastPosts(int n_of_posts){
		String jpql = "SELECT p FROM Post p ORDER BY p.datetime DESC LIMIT :limit";
		List<Post> posts_list = em_handler.createQuery(jpql, Post.class)
				.setParameter("limit", n_of_posts)
				.getResultList();
		em_handler.closeManager();
		return posts_list;
	}

}
