package com.antoniopgr.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	int id;
	@Column(name = "FirstName")
	String first_name;
	@Column(name = "LastName")
	String last_name;
	@Column(name = "Email")
	String email;
	@Column(name = "Password")
	String password;

	public User(){}

	public User(int id) {
		this.id = id;
	}

	public User(String first_name, String last_name, String email, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return first_name;
	}
}
