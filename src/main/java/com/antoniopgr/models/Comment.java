package com.antoniopgr.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CommentID")
	int id;
	@ManyToOne
	@JoinColumn(name = "UserId")
	User author;
	@Column(name = "Text")
	String text;
	@Column(name = "DateTime")
	LocalDateTime datetime;

	public Comment(User author, String text, LocalDateTime datetime) {
		this.author = author;
		this.text = text;
		this.datetime = datetime;
	}

	public int getId() {
		return id;
	}

	public User getAuthor() {
		return author;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}
}
