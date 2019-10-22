package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
   @RequestMapping("/api")
   public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    public Map<String,Object> getid() {
        Map<String , Object> Dto=new LinkedHashMap<>();
        Dto.put("player","Guest");
//        Dto.put("games",gameRepository.findAll().stream().map(game -> game.makeGameDTO()).collect(Collectors.toList()));
        Dto.put("games",gameRepository.findAll().stream().map(game -> game.makeGameDTO()).collect(Collectors.toList()));
        return Dto;



    }
    @Autowired
    private GamePlayerRepository gameplayerRepository;
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


}

