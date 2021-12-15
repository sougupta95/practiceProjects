package com.springBoot.basics.sprintBootBasics;

public class Book {
	long id;
	String name;
	String author;
	public Book(long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + "]";
	}
	
	
}
