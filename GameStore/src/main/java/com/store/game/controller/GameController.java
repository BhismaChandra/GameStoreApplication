package com.store.game.controller;

import com.store.game.model.Game;
import com.store.game.service.GameService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String getAllGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "index";
    }

    @GetMapping("/add")
    public String newGame(Model model) {
        Game game = new Game();
        model.addAttribute("game", game);
        return "add-game";
    }

    @PostMapping("/save")
    public String saveGame(@ModelAttribute("game") Game game) {
        gameService.addGame(game);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable String id, Model model) {
        Game game = gameService.getGameById(id);
        model.addAttribute("game", game);
        return "edit-game";
    }

    @PostMapping("/update")
    public String updateGame(@ModelAttribute("game") Game game) {
        gameService.updateGame(game);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
        return "redirect:/";
    }
}
