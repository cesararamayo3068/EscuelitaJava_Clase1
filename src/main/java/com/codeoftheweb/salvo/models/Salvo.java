package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Entity crea una clase como entidad (Tabla de una BD)
@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") //ANOTACION QUE GENERA VALOR AUTOMATICO AL ID
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @ElementCollection
    @Column(name="locations")
    private List<String> locations= new ArrayList<>();
    private int turn;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;
    public Salvo() {
    }
    public Salvo( int turn,List<String> locations,GamePlayer gamePlayer ) {
        this.gamePlayer = gamePlayer;
        this.locations = locations;
        this.turn = turn;
    }
    public Long getId() {
        return id;
    }
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public List<String> getLocations() {
        return locations;
    }
    public int getTurn() {
        return turn;
    }
    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public Map<String, Object> makeSalvoDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn",this.turn);
        dto.put("player",gamePlayer.getPlayer().getId());
        dto.put("locations", this.getLocations().stream().collect(Collectors.toList()));
        return dto;
    }
}


