package com.antoniopgr.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categories")
	int id;
	@Column(name = "Name")
	String name;
	@Column(name = "Description")
	String description;

	public Category(){}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
