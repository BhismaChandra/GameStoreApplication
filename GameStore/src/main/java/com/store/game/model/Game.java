package com.store.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document("obat")
public class Game {
	@Id
	private String id;
	
	@Field(name = "title")
	@Indexed(unique = true)
	private String gameTitle;
	
	@Field(name = "developer")
	private String gameDeveloper;
	
	@Field(name = "genre")
	private GameGenre gameGenre;
	
	@Field(name = "price")
	private BigDecimal gamePrice;
	
	public Game(String id, String gameTitle, String gameDeveloper, GameGenre gameGenre, BigDecimal gamePrice) {
		super();
		this.id = id;
		this.gameTitle = gameTitle;
		this.gameDeveloper = gameDeveloper;
		this.gameGenre = gameGenre;
		this.gamePrice = gamePrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getGameDeveloper() {
		return gameDeveloper;
	}

	public void setGameDeveloper(String gameDeveloper) {
		this.gameDeveloper = gameDeveloper;
	}

	public GameGenre getGameGenre() {
		return gameGenre;
	}

	public void setGameGenre(GameGenre gameGenre) {
		this.gameGenre = gameGenre;
	}

	public BigDecimal getGamePrice() {
		return gamePrice;
	}

	public void setGamePrice(BigDecimal gamePrice) {
		this.gamePrice = gamePrice;
	}
}

