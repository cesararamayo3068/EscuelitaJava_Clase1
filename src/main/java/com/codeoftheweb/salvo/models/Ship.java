package com.codeoftheweb.salvo.models;
import com.codeoftheweb.salvo.models.GamePlayer;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") //ANOTACION QUE GENERA VALOR AUTOMATICO AL ID
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String type;
    @ElementCollection
    @Column(name = "locations")
    private List<String> locations = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;
    public Ship() {
    }
    public Ship(String type, List<String> locations, GamePlayer gamePlayer) {
        this.type = type;
        this.locations = locations;
        this.gamePlayer = gamePlayer;
    }
    public Long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public List<String> getLocations() {
        return locations;
    }
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public Map<String, Object> makeShipDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", this.getType());
        dto.put("locations", this.getLocations().stream().collect(Collectors.toList()));
        return dto;
    }
}


