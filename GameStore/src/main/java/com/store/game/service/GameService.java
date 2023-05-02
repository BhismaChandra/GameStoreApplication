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
        Game savedGame = gameRepository.findById(game.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Cannot Find Game by ID %s", game.getId())));

        savedGame.setTitle(game.getTitle());
        savedGame.setDeveloper(game.getDeveloper());
        savedGame.setGenre(game.getGenre());
        savedGame.setPrice(game.getPrice());

        gameRepository.save(savedGame);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameByTitle(String name) {
        return gameRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot Find Game by Title %s", name)));
    }

    public void deleteGame(String id) {
        gameRepository.deleteById(id);
    }

	public Game getGameById(String id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot Find Game by ID %s", id)));
	}
}
