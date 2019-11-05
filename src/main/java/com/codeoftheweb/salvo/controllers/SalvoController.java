package com.codeoftheweb.salvo.controllers;

import com.codeoftheweb.salvo.models.Game;
import com.codeoftheweb.salvo.models.GamePlayer;
import com.codeoftheweb.salvo.models.Player;
import com.codeoftheweb.salvo.models.Salvo;
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
    Date fecha = new Date();

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    @RequestMapping("/games")
    public Map<String, Object> getIdGames(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        if (isGuest(authentication)) {
            dto.put("player", "Guest");
            System.out.println("hitLocations: "+hitLocations(getSelf(1),getOpponent(getSelf(1)), (long) 1));
        } else {

            dto.put("player", playerRepository.findByuserName(authentication.getName()).makePlayerDTO());
        }
        dto.put("games", gameRepository.findAll().stream()
                .map(game -> game.makeGameDTO())
                .collect(Collectors.toList()));
        return dto;
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)

    public ResponseEntity<Object> newGame(
            Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepository.findByuserName(authentication.getName());
        if (player == null) {
            return new ResponseEntity<>(makeMap("error", "Datos perdidos"), HttpStatus.FORBIDDEN);
        }
        Game game = gameRepository.save(new Game());
        GamePlayer gamePlayer = gameplayerRepository.save(new GamePlayer(game, player));
        return new ResponseEntity<>(makeMap("gpid", gamePlayer.getIdGamePlayer()), HttpStatus.CREATED);
    }


    @RequestMapping(path = "/game_view/{idn}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> createUser(Authentication authentication, @PathVariable long idn) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
        }
        GamePlayer gamePlayer = gameplayerRepository.findById(idn).get();
        Player player = playerRepository.findByuserName(authentication.getName());
        if (player == null) {
            return new ResponseEntity<>(makeMap("Error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
        }
        if (gamePlayer == null) {
            return new ResponseEntity<>(makeMap("Error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
        }
        if (gamePlayer.getPlayer().equals(player)) {
            return new ResponseEntity<>(makeMapDto(idn), HttpStatus.OK);
        }
        return new ResponseEntity<>(makeMap("Error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(path = "/game/{id}/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> joinGame(Authentication authentication, @PathVariable long id) {
        Player player = playerRepository.findByuserName(authentication.getName());
        Game game = gameRepository.findById(id).get();

        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("Error", "No estas autorizado"), HttpStatus.UNAUTHORIZED);
        }

        if (game == null) {
            return new ResponseEntity<>(makeMap("error", "No existe el juego"), HttpStatus.FORBIDDEN);
        }
        if (game.getGamePlayers().size() > 1) {
            return new ResponseEntity<>(makeMap("error", "El juego esta lleno"), HttpStatus.FORBIDDEN);
        }
        GamePlayer gamePlayer1 = gameplayerRepository.save(new GamePlayer(game, player));
        return new ResponseEntity<>(makeMap("gpid", gamePlayer1.getIdGamePlayer()), HttpStatus.CREATED);
    }


    private Map<String, Object> makeMapDto(long idPlayerRepository) {
        GamePlayer gamePlayer = gameplayerRepository.findById(idPlayerRepository).get();
        //este seria yo
        GamePlayer self = gamePlayer;
        GamePlayer opponent = self.getGame().getGamePlayers().stream().filter(gamePlayer1 -> gamePlayer1.getIdGamePlayer() != idPlayerRepository).findFirst().orElse(null);

        Map<String, Object> dto = new LinkedHashMap<>();

        Map<String, Object> obj = new HashMap<>();


        obj.put("self",opponent.getSalvos().stream().map(salvo -> salvo.makedamagesDto(self ,opponent)));
        obj.put("opponent",self.getSalvos().stream().map(salvo -> salvo.makedamagesDto(opponent,self)));



        dto.put("id", gamePlayer.getGame().getIdGame());
        dto.put("created", gamePlayer.getGame().getGameCreation());
        dto.put("gameState", gameState(self,opponent));
        dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.makeGamePlayerDTO()).collect(Collectors.toList()));
        dto.put("ships", gamePlayer.getShips().stream().map(gamePlayer1 -> gamePlayer1.makeShipDTO()).collect(Collectors.toList()));
        dto.put("salvoes", gamePlayer.getGame().getGamePlayers().stream().map(gamePlayer1 -> gamePlayer1.getSalvos()).flatMap(salvos -> salvos.stream().map(salvo -> salvo.makeSalvoDTO())));
        dto.put("hits", obj);
        return dto;
    }


    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createplayer(@RequestParam String email, @RequestParam String password) {
        if (email.isEmpty()) {
            return new ResponseEntity<>(makeMap("error", "Sin nombre"), HttpStatus.FORBIDDEN);
        }
        Player player = playerRepository.findByuserName(email);
        if (player != null) {
            return new ResponseEntity<>(makeMap("error", "nombre de usuario ya existe"), HttpStatus.CONFLICT);
        }
        playerRepository.save(new Player(email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    public GamePlayer getSelf(long gpid){
        GamePlayer self = gameplayerRepository.findById(gpid).orElse(null);
        return self;
    }

 public GamePlayer getOpponent (GamePlayer self){
        GamePlayer opponent = self
                .getGame()
                .getGamePlayers()
                .stream().filter(gamePlayer -> gamePlayer.getIdGamePlayer() != self.getIdGamePlayer())
                .findAny()
                .orElse(null);
        return opponent;

 }

 public List<String> getShipLocation (GamePlayer gamePlayer) {
     List<String> shipLocations = new ArrayList<>();
     shipLocations = gamePlayer.getShips()
             .stream()
             .map(ship -> ship.getLocations())
             .flatMap(Collection::stream).collect(Collectors.toList());

   return shipLocations;

 }

 public String gameState(GamePlayer self,GamePlayer opponent){
        String gameState = "PLACESHIPS";
        if(opponent == null){
            gameState = "WAITINGFOROPP";
        }

        return gameState;
 }



 public List<String> hitLocations(GamePlayer self, GamePlayer opponent, Long turn){
        List<String> hitLocation = new ArrayList<>();
        List<String> shipSelfLocations = getShipLocation(self);
        Salvo salvo = opponent
                .getSalvos()
                .stream()
                .filter(salvo1 -> salvo1.getTurn() == turn).findAny().orElse(null);

        for(String positionSalvo : salvo.getLocations()){
            for(String positionShip : shipSelfLocations){
                if(positionSalvo.contains(positionShip)){
                    hitLocation.add(positionSalvo);
                }
            }
        }

        return hitLocation;
 }

}

