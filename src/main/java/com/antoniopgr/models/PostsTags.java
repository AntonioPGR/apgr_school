package com.antoniopgr.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts_tags")
public class PostsTags {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PostsTagsId")
	int id;
	@Column(name = "PostId")
	int post_id;
	@Column(name = "TagId")
	int tag_id;

	public PostsTags(int post_id, int tag_id) {
		this.post_id = post_id;
		this.tag_id = tag_id;
	}

	public int getId() {
		return id;
	}

	public int getPostId() {
		return post_id;
	}

	public int getTagId() {
		return tag_id;
	}
}
