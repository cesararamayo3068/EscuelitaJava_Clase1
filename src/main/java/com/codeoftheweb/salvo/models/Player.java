package com.codeoftheweb.salvo.models;


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
    private String userName;
    private String password;

    //El jugador tiene una relación de  uno a muchos  con  GamePlayer
    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<Score> score;


    public Player() { }



    public Player(String userName) { this.userName = userName; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public long getId() {return id; }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) { this.id = id; }

    public Map<String, Object> makePlayerDTO() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("email", this.getUserName());
        return dto;
    }

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
