package com.store.game.controller;

import com.store.game.model.Game;
import com.store.game.service.GameService;
import java.util.List;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/Game")
public class GameController {
	
	private final GameService gameService;

	@GetMapping("index")
	public String index() {
	      return "index";
	}
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping
	public ResponseEntity<Game> addGame(@RequestBody Game game) {
		gameService.addGame(game);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Game> updateGame(@RequestBody Game game) {
		gameService.updateGame(game);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Game>> getAllGames() {
		return ResponseEntity.ok(gameService.getAllGames());
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Game> getGameByName(@PathVariable String name) {
		return ResponseEntity.ok(gameService.getGameByName(name));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Game> deleteGame(@PathVariable String id) {
		gameService.deleteGame(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}


