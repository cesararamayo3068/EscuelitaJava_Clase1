package com.codeoftheweb.salvo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private String email;
    //El jugador tiene una relación de  uno a muchos  con  GamePlayer
    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<Score> score;


    public Player() { }

    public Player(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public long getId() {return id; }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setId(long id) { this.id = id; }

    Map<String, Object> makePlayerDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("email", this.getEmail());
        return dto;
    }

}
