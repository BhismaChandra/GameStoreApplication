package com.store.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class Game {
    @Id
    private String id;
    private String title;
    private String developer;
    private GameGenre genre;
    private BigDecimal price;

    public Game() {
        this.title = "";
        this.developer = "";
        this.price = BigDecimal.ZERO;
    }

    public Game(String title, String developer, GameGenre genre, BigDecimal price) {
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