package com.codeoftheweb.salvo;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import javax.servlet.http.HttpServletRequest;
import com.codeoftheweb.salvo.models.*;
import com.codeoftheweb.salvo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SalvoApplication {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    public static void main(String[] args) {
        SpringApplication.run(SalvoApplication.class, args);
    }


    @Bean
    public CommandLineRunner initData(PlayerRepository repository, GameRepository repositoryJuego, GamePlayerRepository gameplayerRep, ShipRepository shiprepository, SalvoRepository salvoRepository, ScoreRepository scoreRepository) {
        return (args) -> {


            Player player1 =new Player("j.bauer@ctu.gov");
            Player player2 =new Player("t.almeida@ctu.gov");
             Player player3 =new Player("c.obrian@ctu.gov");
             Player player4 =new Player("d.palmer@whitehouse.gov");
             Player player5 =new Player("kim_bauer@gmail.com");
             player1.setPassword(passwordEncoder().encode("123"));
            player2.setPassword(passwordEncoder().encode("1234"));


             Game game1 = new Game();
            Game game2 = new Game();
            Game game3 = new Game();
            Game game4= new Game();
            Game game5 =new Game();
            Game game6 = new Game();
            Game game7=new Game();
            Game game8 =new Game();

            Date fecha=new Date();

            game1.setGameCreation(LocalDateTime.parse("2018-02-17T15:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            game2.setGameCreation(LocalDateTime.parse("2018-02-17T16:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            game3.setGameCreation(LocalDateTime.parse("2018-02-17T17:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            game4.setGameCreation(LocalDateTime.parse("2018-02-17T18:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            game5.setGameCreation(LocalDateTime.parse("2018-02-17T19:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            game6.setGameCreation(LocalDateTime.parse("2018-02-17T20:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            game7.setGameCreation(LocalDateTime.parse("2018-02-17T19:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            game8.setGameCreation(LocalDateTime.parse("2018-02-17T20:20:15", DateTimeFormatter.ISO_LOCAL_DATE_TIME));

            GamePlayer gamePlayer1=new GamePlayer(game1,player1);
            GamePlayer gamePlayer2=new GamePlayer(game1,player2);

            GamePlayer gamePlayer3=new GamePlayer(game2,player1);
            GamePlayer gamePlayer4=new GamePlayer(game2,player2);

            GamePlayer gamePlayer5=new GamePlayer(game3,player3);
            GamePlayer gamePlayer6=new GamePlayer(game3,player2);

            GamePlayer gamePlayer7=new GamePlayer(game4,player3);
            GamePlayer gamePlayer8=new GamePlayer(game4,player1);

            GamePlayer gamePlayer9=new GamePlayer(game5,player2);
            GamePlayer gamePlayer10=new GamePlayer(game5,player1);

            GamePlayer gamePlayer11=new GamePlayer(game6,player5);

            GamePlayer gamePlayer12=new GamePlayer(game7,player2);

            GamePlayer gamePlayer13=new GamePlayer(game8,player5);
            GamePlayer gamePlayer14=new GamePlayer(game8,player2);



            Ship ship1 = new Ship("crucero", Arrays.asList("A1", "A2", "A3"),gamePlayer1);
            Ship ship2 = new Ship("Destroyer", Arrays.asList("B1", "B2", "B3"),gamePlayer1);
            Ship ship3 = new Ship("Submarine", Arrays.asList("C1", "C2", "C3"),gamePlayer1);
            Ship ship4 = new Ship("crucero", Arrays.asList("A6", "A7", "A8"),gamePlayer2);
            Ship ship5 = new Ship("destructor", Arrays.asList("E1", "E2", "E3"),gamePlayer2);
            Ship ship6 = new Ship("acorazado", Arrays.asList("F1", "F2", "F3"),gamePlayer2);

            Salvo salvo1=new Salvo(1,Arrays.asList("H3", "C9", "A5"),gamePlayer1);
            Salvo salvo2=new Salvo(2,Arrays.asList("H2", "C6", "A8"),gamePlayer2);
            Salvo salvo3=new Salvo(3,Arrays.asList("H4", "B2", "A1"),gamePlayer3);
            Salvo salvo4=new Salvo(4,Arrays.asList("B6", "C6", "A7"),gamePlayer4);
            Salvo salvo5=new Salvo(5,Arrays.asList("H2", "C4", "A5"),gamePlayer5);
            Salvo salvo6=new Salvo(6,Arrays.asList("H3", "B4", "A2"),gamePlayer6);

            Score score1= new Score(game1,player1,1.0,fecha);
            Score score2= new Score(game1,player5,0.5,fecha);
            Score score3= new Score(game5,player3,0.0,fecha);
            Score score4= new Score(game3,player2,1.0,fecha);
            Score score5= new Score(game2,player2,0.5,fecha);
            Score score6= new Score(game4,player4,1.0,fecha);

            repository.save(player1);
            repository.save(player2);
            repository.save(player3);
            repository.save(player4);
            repository.save(player5);



            repositoryJuego.save(game1);
            repositoryJuego.save(game2);
            repositoryJuego.save(game3);
            repositoryJuego.save(game4);
            repositoryJuego.save(game5);
            repositoryJuego.save(game6);
            repositoryJuego.save(game7);
            repositoryJuego.save(game8);

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



            shiprepository.save(ship1);
            shiprepository.save(ship2);
            shiprepository.save(ship3);
            shiprepository.save(ship4);
            shiprepository.save(ship5);
            shiprepository.save(ship6);

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
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputUserName-> {
            Player player = playerRepository.findByuserName(inputUserName);
            if (player != null) {
                return new User(player.getUserName(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputUserName);
            }
        });
    }
}
@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/web/**").permitAll()
                //
                .antMatchers("/api/*").permitAll()
                .antMatchers("/**").hasAuthority("USER")
                .and()
                .formLogin();
        http.formLogin()
                .usernameParameter("name")
                .passwordParameter("pwd")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");



            // desactiva la comprobación de tokens CSRF

            http.csrf().disable();
            http.headers().frameOptions().disable();
            // si el usuario no está autenticado, solo envíe una respuesta de falla de autenticación
            http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

            // si el inicio de sesión es exitoso, simplemente borre las banderas que solicitan autenticación
            http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

            // si falla el inicio de sesión, solo envíe una respuesta de falla de autenticación
            http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

            // si el cierre de sesión es exitoso, solo envíe una respuesta exitosa
            http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        }

        private void clearAuthenticationAttributes(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            }
        }


}
