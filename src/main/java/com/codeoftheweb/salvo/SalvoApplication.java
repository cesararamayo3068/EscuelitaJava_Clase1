package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }


    @Bean
    public CommandLineRunner initData(PlayerRepository repository, GameRepository repositoryJuego, GamePlayerRepository gameplayerRep) {
        return (args) -> {

            Player jugador1 =new Player("j.bauer@ctu.gov");
            Player jugador2 =new Player("t.almeida@ctu.gov");
             Player jugador3 =new Player("c.obrian@ctu.gov");
             Player jugador4 =new Player("d.palmer@whitehouse.gov");

            repository.save(jugador1);
            repository.save(jugador2);
            repository.save(jugador3);
            repository.save(jugador4);

            Game juego1 = new Game();
            Game juego2 = new Game();
            Game juego3 = new Game();
            Game juego4= new Game();
            Game juego5 =new Game();
            Game juego6 = new Game();


            juego1.setGameCreation(LocalDateTime.parse("2018-02-17T15:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego2.setGameCreation(LocalDateTime.parse("2018-02-17T16:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego3.setGameCreation(LocalDateTime.parse("2018-02-17T17:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego4.setGameCreation(LocalDateTime.parse("2018-02-17T18:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego5.setGameCreation(LocalDateTime.parse("2018-02-17T19:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego6.setGameCreation(LocalDateTime.parse("2018-02-17T20:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));


            repositoryJuego.save(juego1);
            repositoryJuego.save(juego2);
            repositoryJuego.save(juego3);
            repositoryJuego.save(juego4);
            repositoryJuego.save(juego5);
            repositoryJuego.save(juego6);

            gameplayerRep.save(new GamePlayer(juego1,jugador1));
            gameplayerRep.save(new GamePlayer(juego1,jugador3));

            gameplayerRep.save(new GamePlayer(juego2,jugador1));
            gameplayerRep.save(new GamePlayer(juego2,jugador3));

            gameplayerRep.save(new GamePlayer(juego3,jugador3));
            gameplayerRep.save(new GamePlayer(juego3,jugador2));

            gameplayerRep.save(new GamePlayer(juego4,jugador1));
            gameplayerRep.save(new GamePlayer(juego4,jugador3));

            gameplayerRep.save(new GamePlayer(juego5,jugador2));
            gameplayerRep.save(new GamePlayer(juego5,jugador1));

            gameplayerRep.save(new GamePlayer(juego6,jugador4));


        };
    }
}