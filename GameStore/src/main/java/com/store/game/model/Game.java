package com.store.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document("game")
public class Game {
	@Id
	private String id;
	
	@Field(name = "title")
	@Indexed(unique = true)
	private String title;
	
	@Field(name = "developer")
	private String developer;
	
	@Field(name = "genre")
	private GameGenre genre;
	
	@Field(name = "price")
	private BigDecimal price;
	
	public Game() {
		super();
	}

	public Game(String id, String title, String developer, GameGenre genre, BigDecimal price) {
		super();
		this.id = id;
		this.title = title;
		this.developer = developer;
		this.genre = genre;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public GameGenre getGenre() {
		return genre;
	}

	public void setGenre(GameGenre genre) {
		this.genre = genre;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}

