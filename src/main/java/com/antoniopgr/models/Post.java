package com.antoniopgr.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PostId")
	int id;
	@Column(name="Title")
	String title;
	@Column(name = "Content")
	String content;
	@Column(name = "DateTime")
	LocalDateTime datetime = LocalDateTime.now();
	//@ManyToMany
	//@JoinTable(name = "posts_tags", joinColumns = @JoinColumn(name = "PostId"), inverseJoinColumns = @JoinColumn(name = "TagId"))
	//List<Tag> tags;
	@ManyToMany
	@JoinTable(name = "posts_categories", joinColumns = @JoinColumn(name = "PostId"), inverseJoinColumns = @JoinColumn(name = "CategoryId"))
	List<Category> categories;
	@ManyToOne
	@JoinColumn(name = "AuthorID")
	User author;

	public Post(){}

	public Post(String title, String content, /*List<Tag> tags,*/ List<Category> categories, User author) {
		this.title = title;
		this.content = content;
		//this.tags = tags;
		this.categories = categories;
		this.author = author;
	}

	/*
	public List<Tag> getTags() {
		return tags;
	}
	*/

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public User getAuthor() {
		return author;
	}
}

