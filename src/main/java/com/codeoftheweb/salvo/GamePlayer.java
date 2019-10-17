package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long idGamePlayer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;
    private LocalDateTime gameCreation;


    public GamePlayer(){}
    public GamePlayer(Game game , Player player){
        this.game = game;
        this.player = player;
        gameCreation= LocalDateTime.now();

    }

    public long getIdGamePlayer() {
        return idGamePlayer;
    }

    public void setIdGamePlayer(long idGamePlayer) {
        this.idGamePlayer = idGamePlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LocalDateTime getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(LocalDateTime gameCreation) {
        this.gameCreation = gameCreation;
    }

    Map<String, Object> makeGamePlayerDTO() {

            Map<String, Object> dto = new LinkedHashMap<>();
            dto.put("id", this.getIdGamePlayer());
            dto.put("player", this.player.makePlayerDTO());


        return dto;
        }

}