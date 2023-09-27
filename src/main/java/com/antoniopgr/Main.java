package com.antoniopgr;

import com.antoniopgr.models.Category;
import com.antoniopgr.models.Post;
import com.antoniopgr.models.Tag;
import com.antoniopgr.models.User;
import com.antoniopgr.utils.EntityManagerHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		User user = new User("Luciana", "Pacheco", "lupacheco24@gmail.com", "050406@Ant+");
		Category category1 = new Category("front-end", "o client side");
		Category category2 = new Category("back-end", "o server side");
		List<Category> category_set = new ArrayList<>();
		category_set.add(category1);
		category_set.add(category2);


		Tag tag1 = new Tag("programação", "um pouco sobre código");
		List<Tag> tags_set = new ArrayList<>();
		tags_set.add(tag1);

		Post post1 = new Post("Teste1", "ola mundo", tags_set, category_set, user);

		EntityManagerHandler user_em = new EntityManagerHandler("blog_sql");
		user_em.beginTransaction();
		user_em.persistNCommit(user);
		user_em.closeManager();

		EntityManagerHandler category1_em = new EntityManagerHandler("blog_sql");
		category1_em.beginTransaction();
		category1_em.persistNCommit(category1);
		category1_em.closeManager();

		EntityManagerHandler category2_em = new EntityManagerHandler("blog_sql");
		category2_em.beginTransaction();
		category2_em.persistNCommit(category2);
		category2_em.closeManager();

		EntityManagerHandler tag1_em = new EntityManagerHandler("blog_sql");
		tag1_em.beginTransaction();
		tag1_em.persistNCommit(tag1);
		tag1_em.closeManager();

		EntityManagerHandler post1_em = new EntityManagerHandler("blog_sql");
		post1_em.beginTransaction();
		post1_em.persistNCommit(post1);
		post1_em.closeManager();

	}
}