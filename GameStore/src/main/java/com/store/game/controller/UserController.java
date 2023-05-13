package com.store.game.controller;

import com.store.game.model.Game;
import com.store.game.model.User;
import com.store.game.model.GameGenre;
import com.store.game.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Login");
        return "user-form";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (optionalUser.isPresent()) {
            User loggedInUser = optionalUser.get();
            redirectAttributes.addAttribute("userId", loggedInUser.getId());
            return "redirect:/dashboard/{userId}";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("title", "Register");
        return "user-form";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user.getUsername(), user.getPassword());
        return "redirect:/";
    }
    
    @PostMapping("/addGame/{userId}")
    public String addGameToLibrary(
            @PathVariable String userId,
            @ModelAttribute Game game,
            @RequestParam("genre") String genre) {
        if (StringUtils.hasText(genre)) {
            GameGenre gameGenre = GameGenre.valueOf(genre);
            game.setGenre(gameGenre);
        }
        userService.addGameToLibrary(userId, game);
        return "redirect:/dashboard/" + userId;
    }

    @PostMapping("/updateUser/{userId}")
    public String updateUser(@PathVariable String userId, @RequestParam String username, @RequestParam String password) {
        userService.updateUser(userId, username, password);
        return "redirect:/dashboard";
    }

    @PostMapping("/addBalance/{userId}")
    public String addBalance(
            @PathVariable String userId,
            @RequestParam BigDecimal balance) {
        userService.addBalance(userId, balance);
        return "redirect:/dashboard/{userId}";
    }
    
    @GetMapping("/game-form/{userId}/add")
    public String showAddGameForm(@PathVariable String userId, Model model) {
        User user = userService.getUserById(userId).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("game", new Game());
            return "game-form";
        } else {
            return "redirect:/dashboard/" + userId;
        }
    }

    @GetMapping("/game-form/{userId}/{gameId}")
    public String showEditGameForm(@PathVariable String userId, @PathVariable String gameId, Model model) {
        User user = userService.getUserById(userId).orElse(null);
        if (user != null) {
            List<Game> games = user.getGames();
            if (StringUtils.hasText(gameId)) {
                Game game = games.stream()
                        .filter(g -> g.getId().equals(gameId))
                        .findFirst()
                        .orElse(null);
                if (game != null) {
                    model.addAttribute("user", user);
                    model.addAttribute("game", game);
                    return "game-form";
                }
            }
        }
        return "redirect:/dashboard/" + userId;
    }

    @PostMapping("/updateGame/{userId}/{gameId}")
    public String updateGame(
            @PathVariable String userId,
            @PathVariable String gameId,
            @ModelAttribute Game updatedGame) {
        userService.updateGame(userId, gameId, updatedGame);
        return "redirect:/dashboard/" + userId;
    }

    @GetMapping("/deleteGame/{userId}/{gameId}")
    public String deleteGame(
            @PathVariable String userId,
            @PathVariable String gameId) {
        userService.deleteGame(userId, gameId);
        return "redirect:/dashboard/" + userId;
    }

    @GetMapping("/dashboard/{userId}")
    public String showDashboard(@PathVariable String userId, Model model) {
        Optional<User> optionalUser = userService.getUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            model.addAttribute("genres", GameGenre.values());
            return "dashboard";
        } else {
           return "redirect:/";
        }
    }
}