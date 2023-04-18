package com.store.game.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.store.game.model.Game;

public interface GameRepository extends MongoRepository<Game, String>{
	@Query("{'name': ?0")
	Optional<Game> findByName(String name);
}
