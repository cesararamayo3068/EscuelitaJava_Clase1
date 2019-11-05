package com.codeoftheweb.salvo.controllers;

import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.models.Salvo;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import com.codeoftheweb.salvo.repositories.SalvoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SalvosController {

    @Autowired
    private GamePlayerRepository gameplayerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private SalvoRepository salvoRepository;

    Authentication authentication;

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }
    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    @RequestMapping(value="/games/players/{gamePlayerId}/salvoes", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addSalvos(Authentication authentication, @PathVariable long gamePlayerId, @RequestBody Salvo salvo) {

        GamePlayer gamePlayer = gameplayerRepository.findById(gamePlayerId).get();
        Player player = playerRepository.findByuserName(authentication.getName());

        if (isGuest(authentication)||gamePlayer == null||!(gamePlayer.getPlayer().equals(player))) {
            return new ResponseEntity<>(makeMap("Error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
        }

        salvo.setTurn(gamePlayer.getSalvos().size()+1);
        salvo.setGamePlayer(gamePlayer);
        salvoRepository.save(salvo);
        return new ResponseEntity<>(makeMap("OK", "salvos creados"), HttpStatus.CREATED);
    }
}

