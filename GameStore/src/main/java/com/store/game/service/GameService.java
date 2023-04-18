package com.store.game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.game.model.Game;
import com.store.game.repository.GameRepository;

@Service
public class GameService {
	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	public void addGame(Game game) {
		gameRepository.insert(game);
	}
	
	public void updateGame(Game game) {
		Game savedGame = gameRepository.findById(game.getId()).
				orElseThrow(() -> new RuntimeException(String.format(
						"Cannot Find Game by ID %s", game.getId())));

		savedGame.setGameTitle(game.getGameTitle());
		savedGame.setGameDeveloper(game.getGameDeveloper());
		savedGame.setGameGenre(game.getGameGenre());
		savedGame.setGamePrice(game.getGamePrice());
		
		gameRepository.save(savedGame);
	}
	
	public List<Game> getAllGames() {
		return gameRepository.findAll();
	}
	
	public Game getGameByName(String name) {
		return gameRepository.findByName(name).
		orElseThrow(() -> new RuntimeException(String.format(
				"Cannot Find Game by Title %s", name)));
	}
	
	public void deleteGame(String id) {
		gameRepository.deleteById(id);
	}
}

