package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }


    @Bean
    public CommandLineRunner initData(PlayerRepository repository, GameRepository repositoryJuego, GamePlayerRepository gameplayerRep,ShipRepository shiprepository,SalvoRepository salvoRepository,ScoreRepository scoreRepository) {
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

            Date fecha=new Date();

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
            Ship barco4 = new Ship("crucero", Arrays.asList("A6", "A7", "A8"),gamePlayer2);
            Ship barco5 = new Ship("destructor", Arrays.asList("E1", "E2", "E3"),gamePlayer2);
            Ship barco6 = new Ship("acorazado", Arrays.asList("F1", "F2", "F3"),gamePlayer2);

            Salvo salvo1=new Salvo(1,Arrays.asList("H3", "C9", "A5"),gamePlayer1);
            Salvo salvo2=new Salvo(2,Arrays.asList("H2", "C6", "A8"),gamePlayer2);
            Salvo salvo3=new Salvo(3,Arrays.asList("H4", "B2", "A1"),gamePlayer3);
            Salvo salvo4=new Salvo(4,Arrays.asList("B6", "C6", "A7"),gamePlayer4);
            Salvo salvo5=new Salvo(5,Arrays.asList("H2", "C4", "A5"),gamePlayer5);
            Salvo salvo6=new Salvo(6,Arrays.asList("H3", "B4", "A2"),gamePlayer6);

            Score score1= new Score(juego1,jugador1,1.0,fecha);
            Score score2= new Score(juego1,jugador5,0.5,fecha);
            Score score3= new Score(juego5,jugador3,0.0,fecha);
            Score score4= new Score(juego3,jugador2,1.0,fecha);
            Score score5= new Score(juego2,jugador2,0.5,fecha);
            Score score6= new Score(juego4,jugador4,1.0,fecha);

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

            salvoRepository.save(salvo1);
            salvoRepository.save(salvo2);
            salvoRepository.save(salvo3);
            salvoRepository.save(salvo4);
            salvoRepository.save(salvo5);
            salvoRepository.save(salvo6);

            scoreRepository .save(score1);
            scoreRepository .save(score2);
            scoreRepository .save(score3);
            scoreRepository .save(score4);
            scoreRepository .save(score5);
            scoreRepository .save(score6);
        };
    }
}