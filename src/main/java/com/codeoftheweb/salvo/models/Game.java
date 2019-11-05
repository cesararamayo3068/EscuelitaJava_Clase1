package com.codeoftheweb.salvo.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long idGame;
    private LocalDateTime gameCreation = LocalDateTime.now();

    //El juego tiene una relación de  uno a muchos  con  GamePlayer
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    Set<GamePlayer> gamePlayers;
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    Set<Score> score;

    public Set<GamePlayer> getGamePlayers() {

        return gamePlayers;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {

        this.gamePlayers = gamePlayers;
    }


    public Game() {
    }

    public Game(LocalDateTime gameCreation) {
        this.gameCreation = gameCreation;

    }

    public Set<Score> getScore() {
        return score;
    }

    public LocalDateTime getGameCreation() {
        return gameCreation;
    }

    public long getIdGame() {
        return idGame;
    }

    public void setIdGame(long idGame) {

        this.idGame = idGame;
    }

    public void setGameCreation(LocalDateTime gameCreation) {
        this.gameCreation = gameCreation;
    }

    public Map<String, Object> makeGameDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getIdGame());
        dto.put("created", this.getGameCreation());
        dto.put("gamePlayers", this.getGamePlayers().stream().map(gamePlayer -> gamePlayer.makeGamePlayerDTO()).collect(Collectors.toList()));
        dto.put("scores", this.getScore().stream().map(score1 -> score1.makeScoreDTO()).collect(Collectors.toList()));

        return dto;

    }

}


