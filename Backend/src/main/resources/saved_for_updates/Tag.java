package com.antoniopgr.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TagId")
	int id;
	@Column(name = "Name")
	String name;
	@Column(name = "Description")
	String description;
	@ManyToMany(mappedBy = "tags")
	Set<Post> posts;

	public Tag(String name, String description) {
		this.name = name;
		this.description = description;
	}

	// GETTERS
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}
