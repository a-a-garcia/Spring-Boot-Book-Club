package com.anthonygarcia.bookclub.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="books")
public class Book {
	    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	    
	 @NotBlank(message="**Title is required!")
	 @Size(min=3, max=30, message="**Title must be between 3 and 30 characters")
	 private String title;
	    
	 @NotBlank(message="**Author is required!")
	 @Size(min=3, max=30, message="**Author must be between 3 and 30 characters")
	 private String author;
	    
	 @NotBlank(message="**Please enter some thoughts.")
	 private String thoughts;
	 
	 @Column(updatable=false)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	  private Date createdAt;
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	  private Date updatedAt;
	 
	 @ManyToOne(fetch= FetchType.LAZY)
	 @JoinColumn(name = "user_id")
	 private User user;
	    
	 public Book() {}
	 
	 @PrePersist
		protected void onCreate(){
		    this.createdAt = new Date();
		  }
		@PreUpdate
		protected void onUpdate(){
		      this.updatedAt = new Date();
		  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	 
	    
 }
