package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Entity crea una clase como entidad (Tabla de una BD)
@Entity
    public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    //Id generada por la BD
    private long idbarco;
    //Variable que almacenta el tipo de Barco (Ej:"crucero", "destructor" o "acorazado")
    private String tipo;

    //Creo el constructor vacio
    public Ship() {
    }

    public Ship(String tipo, List<String> shiplocation,GamePlayer gamePlayer) {
        this.tipo = tipo;
        this.shiplocation = shiplocation;

        this.gamePlayer=gamePlayer;

    }

    @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn dice qué columna tiene el ID del propietario
    @JoinColumn(name = "gamePlayer_id")
    // Atributo que almacena el Un  juego de jugador
    private GamePlayer gamePlayer;

    @ElementCollection
    //@Column le asigna un nombre a la columna de la tabla
    @Column(name = "ubicacionbarco")

    // Crea la lista de ubicaciones de los barcos en un String y los almacena en un array vacio
    private List<String> shiplocation = new ArrayList<>();


    Map<String, Object> makeShipDTO() {

        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", this.getTipo());
        dto.put("locations", this.getShiplocation());

        return dto;


    }


    public long getIdbarco() {
        return idbarco;
    }

    public String getTipo() {
        return tipo;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public List<String> getShiplocation() {
        return shiplocation;
    }
}