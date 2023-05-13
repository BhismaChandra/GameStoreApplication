package com.store.game.service;

import com.store.game.model.Game;
import com.store.game.model.User;
import com.store.game.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }
    
    public void createUser(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setBalances(BigDecimal.ZERO);
        userRepository.save(newUser);
    }

    public void addGameToLibrary(String userId, Game game) {
        userRepository.findById(userId).ifPresent(user -> {
            BigDecimal gamePrice = game.getPrice();
            BigDecimal currentBalance = user.getBalances();
            if (currentBalance.compareTo(gamePrice) >= 0) {
                game.setId(UUID.randomUUID().toString());
                user.getGames().add(game);
                BigDecimal newBalance = currentBalance.subtract(gamePrice);
                user.setBalances(newBalance);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Insufficient balance to purchase the game.");
            }
        });
    }
    public void updateUser(String userId, String username, String password) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);
        }
    }

    public void addBalance(String userId, BigDecimal balance) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal currentBalance = user.getBalances();
            BigDecimal newBalance = currentBalance.add(balance);
            user.setBalances(newBalance);
            userRepository.save(user);
        }
    }

    public void updateGame(String userId, String gameId, Game updatedGame) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Game> games = user.getGames();
            for (int i = 0; i < games.size(); i++) {
                Game game = games.get(i);
                if (game.getId().equals(gameId)) {
                    games.set(i, updatedGame);
                    break;
                }
            }
            user.setGames(games);
            userRepository.save(user);
        }
    }

    public void deleteGame(String userId, String gameId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Game> games = user.getGames();
            games.removeIf(game -> game.getId().equals(gameId));
            user.setGames(games);
            userRepository.save(user);
        }
    }
}
