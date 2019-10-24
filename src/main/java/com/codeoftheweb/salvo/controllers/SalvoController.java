package com.codeoftheweb.salvo.controllers;
import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.repositories.GamePlayerRepository;
import com.codeoftheweb.salvo.repositories.GameRepository;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
   @RequestMapping("/api")
   public class SalvoController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamePlayerRepository gameplayerRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }
    @RequestMapping("/games")
    public Map<String, Object> getIdGames(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        if (isGuest(authentication)) {
            dto.put("player", "Guest");
        } else {

            dto.put("player", playerRepository.findByuserName(authentication.getName()).makePlayerDTO());
        }
        dto.put("games", gameRepository.findAll().stream()
                .map(game -> game.makeGameDTO())
                .collect(Collectors.toList()));
        return dto;


    }

    @RequestMapping("/game_view/{nn}")
    public Map<String,Object> getAll(@PathVariable long nn) {
        GamePlayer gamePlayer=gameplayerRepository.findById(nn).get();
        Map<String,Object> Dto =new LinkedHashMap<>();
        Dto.put("id",gamePlayer.getGame().getIdGame());
        Dto.put("created",gamePlayer.getGame().getGameCreation());
        Dto.put("gamePlayers",gamePlayer.getGame().getGamePlayers().stream().map(game-> game.makeGamePlayerDTO()).collect(Collectors.toList()));
        Dto.put ("ships",gamePlayer.getShips().stream().map(game-> game.makeShipDTO()).collect(Collectors.toList()));
        Dto.put("salvoes",gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1->gamePlayer1.getSalvos()).flatMap(salvo->salvo.stream().map(s->s.makeSalvoDTO())));
        return Dto;


}
    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createplayer(@RequestParam String email,@RequestParam String password ) {
        if (email.isEmpty()) {
            return new ResponseEntity<>(makeMap("error", "Sin nombre"), HttpStatus.FORBIDDEN);
        }
        Player player=playerRepository.findByuserName(email);
        if (player != null) {
            return new ResponseEntity<>(makeMap("error", "nombre de usuario ya existe"), HttpStatus.CONFLICT);
        }
        playerRepository.save(new Player(email,passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }



}

