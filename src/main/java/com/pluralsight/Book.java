package com.pluralsight;

public class Book {
	int id;
	String title;
	String author;
	float price;

	public Book(String title, String author, float price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public Book(int id, String title, String author, float price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	@Override
	public String toString() {
		return "(" + title + ", " + author + ", " + price + ")";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
