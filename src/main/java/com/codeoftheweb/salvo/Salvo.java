package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Entity crea una clase como entidad (Tabla de una BD)
@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    //Id generada por la BD
    private long idsalvo;

    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn dice qué columna tiene el ID del propietario
    @JoinColumn(name = "gamePlayer_id")
    // Atributo que almacena el Un  juego de jugador
    //declara un juego de un jugador
    private GamePlayer gamePlayer;

    //numero de turno
    private long turno;

    //Creo el constructor vacio
    public Salvo() {
    }

    public Salvo(long turno,List<String> salvolocation,GamePlayer gamePlayer) {
        this.turno = turno;
        this.salvolocation = salvolocation;

        this.gamePlayer=gamePlayer;

    }


    @ElementCollection
    //@Column le asigna un nombre a la columna de la tabla
    @Column(name = "salvolocation")
    // Crea la lista de ubicaciones de los salvos en un String y los almacena en un array vacio
    private List<String> salvolocation = new ArrayList<>();
 //constructor vacio
    public long getTurno() {
        return turno;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public List<String> getSalvolocation() {
        return salvolocation;
    }

    Map<String, Object> makeSalvoDTO() {

        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", this.getTurno());
        dto.put("player", gamePlayer.getPlayer().getId());
        dto.put("locations", this.getSalvolocation());

        return dto;


    }


}

