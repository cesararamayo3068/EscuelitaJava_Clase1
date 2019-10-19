package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }


    @Bean
    public CommandLineRunner initData(PlayerRepository repository, GameRepository repositoryJuego, GamePlayerRepository gameplayerRep,ShipRepository shiprepository) {
        return (args) -> {

            Player jugador1 =new Player("j.bauer@ctu.gov");
            Player jugador2 =new Player("t.almeida@ctu.gov");
             Player jugador3 =new Player("c.obrian@ctu.gov");
             Player jugador4 =new Player("d.palmer@whitehouse.gov");
             Player jugador5 =new Player("kim_bauer@gmail.com");


            Game juego1 = new Game();
            Game juego2 = new Game();
            Game juego3 = new Game();
            Game juego4= new Game();
            Game juego5 =new Game();
            Game juego6 = new Game();
            Game juego7=new Game();
            Game juego8 =new Game();


            juego1.setGameCreation(LocalDateTime.parse("2018-02-17T15:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego2.setGameCreation(LocalDateTime.parse("2018-02-17T16:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego3.setGameCreation(LocalDateTime.parse("2018-02-17T17:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego4.setGameCreation(LocalDateTime.parse("2018-02-17T18:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego5.setGameCreation(LocalDateTime.parse("2018-02-17T19:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego6.setGameCreation(LocalDateTime.parse("2018-02-17T20:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego7.setGameCreation(LocalDateTime.parse("2018-02-17T19:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            juego8.setGameCreation(LocalDateTime.parse("2018-02-17T20:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            GamePlayer gamePlayer1=new GamePlayer(juego1,jugador1);
            GamePlayer gamePlayer2=new GamePlayer(juego1,jugador2);

            GamePlayer gamePlayer3=new GamePlayer(juego2,jugador1);
            GamePlayer gamePlayer4=new GamePlayer(juego2,jugador2);

            GamePlayer gamePlayer5=new GamePlayer(juego3,jugador3);
            GamePlayer gamePlayer6=new GamePlayer(juego3,jugador2);

            GamePlayer gamePlayer7=new GamePlayer(juego4,jugador3);
            GamePlayer gamePlayer8=new GamePlayer(juego4,jugador1);

            GamePlayer gamePlayer9=new GamePlayer(juego5,jugador2);
            GamePlayer gamePlayer10=new GamePlayer(juego5,jugador1);

            GamePlayer gamePlayer11=new GamePlayer(juego6,jugador5);

            GamePlayer gamePlayer12=new GamePlayer(juego7,jugador2);

            GamePlayer gamePlayer13=new GamePlayer(juego8,jugador5);
            GamePlayer gamePlayer14=new GamePlayer(juego8,jugador2);



            Ship barco1 = new Ship("crucero", Arrays.asList("A1", "A2", "A3"),gamePlayer1);
            Ship barco2 = new Ship("destructor", Arrays.asList("B1", "B2", "B3"),gamePlayer1);
            Ship barco3 = new Ship("acorazado", Arrays.asList("C1", "C2", "C3"),gamePlayer1);
            Ship barco4 = new Ship("crucero", Arrays.asList("A1", "A2", "A3"),gamePlayer2);
            Ship barco5 = new Ship("destructor", Arrays.asList("B1", "B2", "B3"),gamePlayer2);
            Ship barco6 = new Ship("acorazado", Arrays.asList("C1", "C2", "C3"),gamePlayer2);




            repository.save(jugador1);
            repository.save(jugador2);
            repository.save(jugador3);
            repository.save(jugador4);
            repository.save(jugador5);



            repositoryJuego.save(juego1);
            repositoryJuego.save(juego2);
            repositoryJuego.save(juego3);
            repositoryJuego.save(juego4);
            repositoryJuego.save(juego5);
            repositoryJuego.save(juego6);
            repositoryJuego.save(juego7);
            repositoryJuego.save(juego8);

            gameplayerRep.save(gamePlayer1);
            gameplayerRep.save(gamePlayer2);
            gameplayerRep.save(gamePlayer3);
            gameplayerRep.save(gamePlayer4);
            gameplayerRep.save(gamePlayer5);
            gameplayerRep.save(gamePlayer6);
            gameplayerRep.save(gamePlayer7);
            gameplayerRep.save(gamePlayer8);
            gameplayerRep.save(gamePlayer9);
            gameplayerRep.save(gamePlayer10);
            gameplayerRep.save(gamePlayer11);
            gameplayerRep.save(gamePlayer12);
            gameplayerRep.save(gamePlayer13);
            gameplayerRep.save(gamePlayer14);



            shiprepository.save(barco1);
            shiprepository.save(barco2);
            shiprepository.save(barco3);
            shiprepository.save(barco4);
            shiprepository.save(barco5);
            shiprepository.save(barco6);


        };
    }
}