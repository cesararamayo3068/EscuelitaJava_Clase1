package com.codeoftheweb.salvo.controllers;

import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.models.Ship;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import com.codeoftheweb.salvo.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShipController {

    @Autowired
    private GamePlayerRepository gameplayerRepository;
    @Autowired
    private PlayerRepository playerRepository;

    Authentication authentication;

    @Autowired
    private ShipRepository shipRepository;


    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    @RequestMapping(value="/games/players/{gamePlayerId}/ships", method = RequestMethod.POST)
    public ResponseEntity <Map<String,Object>> addShip(Authentication authentication,@PathVariable long gamePlayerId, @RequestBody List<Ship>ships) {
        GamePlayer gamePlayer = gameplayerRepository.findById(gamePlayerId).get();
        Player player = playerRepository.findByuserName(authentication.getName());

        if (isGuest(authentication)||gamePlayer == null||!(gamePlayer.getPlayer().equals(player))) {
            return new ResponseEntity<>(makeMap("Error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
        }
        if (gamePlayer.getShips().size()!=0){
            return new ResponseEntity<>(makeMap("Error", "ya tiene naves"), HttpStatus.FORBIDDEN);
        }
        ships.forEach(ship -> ship.setGamePlayer(gamePlayer));
        ships.forEach(ship -> shipRepository.save(ship));
        return new ResponseEntity<>(makeMap("OK", "naves agregadas"), HttpStatus.CREATED);
    }
    }
