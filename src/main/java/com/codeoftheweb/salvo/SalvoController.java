package com.codeoftheweb.salvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
   @RequestMapping("/api")
   public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    public List<Map<String,Object>> getAll() {
        return gameRepository.findAll().stream().map(game -> game.makeGameDTO()).collect(Collectors.toList());


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

        return Dto;


}


}

