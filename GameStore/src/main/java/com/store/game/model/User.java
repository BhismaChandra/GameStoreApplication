package com.store.game.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Document("account")
public class User {
    @Id
    private String id;

    @Field(name = "username")
    @Indexed(unique = true)
    private String username;

    @Field(name = "password")
    private String password;

    @Field(name = "games")
    private List<Game> games;
    
    @Field(name = "balances")
    private BigDecimal balances;

    public User() {
        this.username = "";
        this.password = "";
        this.games = new ArrayList<>();
        this.balances = BigDecimal.ZERO;
    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.games = new ArrayList<>();
        this.balances = BigDecimal.ZERO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getGames() {
        return games;
    }

    public BigDecimal getBalances() {
		return balances;
	}

	public void setBalances(BigDecimal balances) {
		this.balances = balances;
	}

	public void setGames(List<Game> games) {
        this.games = games;
    }
}
