package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Entity crea una clase como entidad (Tabla de una BD)
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    //Id generada por la BD
    private long idscore;

    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn dice qué columna tiene el ID del propietario
    @JoinColumn(name = "game_id")
    // Atributo que almacena el Un id  del juego
    private Game game;

    public Game getGame() {
        return game;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn dice qué columna tiene el ID del propietario
    @JoinColumn(name = "player_id")
    // Atributo que almacena el Un id  del jugador
    private Player player;

   private Double score;

    public Player getPlayer() {
        return player;
    }

    private Date finish_date= new Date();

    public Score() {
    }

    public Score(Game game, Player player, Double score, Date finish_date) {
        this.game = game;
        this.player = player;
        this.score = score;
        this.finish_date = finish_date;
    }

    public Double getScore() {
        return score;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    Map<String, Object> makeScoreDTO() {

        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("player", this.getPlayer().getId());
        dto.put("score", this.getScore());
        dto.put("finishDate", this.getFinish_date());

        return dto;


    }
}

