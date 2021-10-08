package teo.guessnumber.config;

import teo.guessnumber.GuessCount;
import teo.guessnumber.MaxNumber;
import teo.guessnumber.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "teo.guessnumber")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // == fields ==
    @Value("${game.maxNumber}")
    private int maxNumber;

    @Value("${game.guessCount}")
    private int guessCount;

    @Value("${game.minNumber}")
    private int minNumber;

    // == bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }
    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }
    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
}


