package com.antoniopgr.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts_categories")
public class PostsCategories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PostsCategoriesId")
	int id;
	@Column(name = "PostId")
	int post_id;
	@Column(name = "CategoryId")
	int category_id;

	public PostsCategories(int post_id, int category_id) {
		this.post_id = post_id;
		this.category_id = category_id;
	}
}
