package com.codeoftheweb.salvo.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

//@Entity crea una clase como entidad (Tabla de una BD)
@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") //ANOTACION QUE GENERA VALOR AUTOMATICO AL ID
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @ElementCollection
    @Column(name = "locations")
    private List<String> locations = new ArrayList<>();
    private int turn;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    public Salvo() {
    }

    public Salvo(int turn, List<String> locations, GamePlayer gamePlayer) {
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
        dto.put("turn", this.turn);
        dto.put("player", gamePlayer.getPlayer().getId());
        dto.put("locations", this.getLocations().stream().collect(Collectors.toList()));
        return dto;


    }


    public Map<String, Object> makedamagesDto(GamePlayer self, GamePlayer opponent) {
        Map<String, Object> damages = new LinkedHashMap<>();
        Integer carrierhits = 0;
        Integer battleshiphits = 0;
        Integer destroyerhits = 0;
        Integer submarinehits = 0;
        Integer patrolboathits = 0;
        Integer missed = 0;
        int carrier=0;
        int battleship=0;
        int destroyer=0;
        int submarine=0;
        int patrolboat=0;


        List<String> hitLocations = new ArrayList<>();

        Salvo salvo = opponent
                .getSalvos()
                .stream()
                .filter(salvo1 -> salvo1.getTurn() == this.getTurn()).findAny().orElse(null);

        missed = salvo.getLocations().size();

        for(String positionSalvo : salvo.getLocations()) {
            for (Ship ship : self.getShips()) {
                for (String positionShip : ship.getLocations()) {
                    if (positionSalvo.contains(positionShip)) {

                        hitLocations.add(positionSalvo);

                        switch (ship.getType().toLowerCase()){
                            case "carrier":
                                carrierhits++;
                                missed--;
                                break;
                            case "destroyer":
                                destroyerhits++;
                                missed--;
                                break;
                            case "submarine":
                                submarinehits++;
                                missed--;
                                break;
                            case "patrolboat":
                                patrolboathits++;
                                missed--;
                                break;
                            case "battleship":
                                battleshiphits++;
                                missed--;
                                break;
                        }

                    }
                }
            }
        }

        List<Salvo> salvos = opponent
                .getSalvos()
                .stream()
                .filter(salvo1 -> salvo1.getTurn() <= this.getTurn()).collect(Collectors.toList());

        for(Salvo salvo1 : salvos) {
            for (String positionSalvo : salvo1.getLocations()) {
                for (Ship ship : self.getShips()) {
                    for (String positionShip : ship.getLocations()) {
                        if (positionSalvo.contains(positionShip)) {


                            switch (ship.getType().toLowerCase()) {
                                case "carrier":
                                    carrier++;
                                    break;
                                case "destroyer":
                                    destroyer++;
                                    break;
                                case "submarine":
                                    submarine++;
                                    break;
                                case "patrolboat":
                                    patrolboat++;
                                    break;
                                case "battleship":
                                    battleship++;
                                    break;
                            }

                        }
                    }
                }
            }
        }


        damages.put("carrierHits", carrierhits);
        damages.put("battleshipHits", battleshiphits);
        damages.put("submarineHits", submarinehits);
        damages.put("destroyerHits", destroyerhits);
        damages.put("patrolboatHits", patrolboathits);
        damages.put("carrier", carrier);
        damages.put("battleship", battleship);
        damages.put("submarine", submarine);
        damages.put("destroyer", destroyer);
        damages.put("patrolboat", patrolboat);

        Map<String, Object> selfs = new LinkedHashMap<>();
        selfs.put("turn", this.getTurn());
        selfs.put("hitLocations",hitLocations);
        selfs.put("damages", damages);
        selfs.put("missed",missed);
        return selfs;
    }

    public List<String> getShipLocation (GamePlayer gamePlayer) {
        List<String> shipLocations = new ArrayList<>();
        shipLocations = gamePlayer.getShips()
                .stream()
                .map(ship -> ship.getLocations())
                .flatMap(Collection::stream).collect(Collectors.toList());

        return shipLocations;

    }


    }