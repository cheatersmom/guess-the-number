package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator{

    // == constants ==
    public static final String MAIN_MESSAGE = "game.main.message";
    public static final String RESULT_MESSAGE = "game.result.message";
    public static final String GAME_WON = "game.won";
    public static final String GAME_LOST = "game.lost";
    public static final String INVALID_NUMBER = "invalid.number";
    public static final String FIRST_MESSAGE = "first.message";
    public static final String LOWER = "game.lower";
    public static final String HIGHER = "game.higher";

    // == fields ==
    private final Game game;
    private final MessageSource messageSource;

    // == constructors ==
    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource = messageSource;
    }

    // == public methods ==
    @PostConstruct
    public void init(){
        log.info("game = {}", game);
    }

    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
//        return "number is between " +
//                game.getSmallest() +
//                " and " +
//                game.getBiggest() +
//                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return getMessage(GAME_WON, game.getNumber());
        } else if (game.isGameLost()) {
            return  getMessage(GAME_LOST, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_NUMBER);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_MESSAGE);
        } else {
            String direction = getMessage(LOWER);

            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }

            return getMessage(RESULT_MESSAGE, direction, game.getRemainingGuesses());
        }
    }

    // == private methods ==
    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}

