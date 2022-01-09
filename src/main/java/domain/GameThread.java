package domain;

import bootstrap.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import static domain.SnakeNLadder.ladder;
import static domain.SnakeNLadder.snake;


public class GameThread implements Callable<Integer> {
    private static final int WIN = 100;
    int playerPosition = 0;
    int playerNumber;

    public static Logger logger = LoggerFactory.getLogger(GameThread.class);

    public GameThread(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    @Override
    public Integer call() throws InterruptedException {

        int diceValue;

        while (true) {
            diceValue = rollDice();
            //System.out.println(playerNumber + " " + playerPosition);

            playerPosition = calculatePlayerValue(playerPosition, diceValue);
            Thread.sleep(100);
            if (isWin(playerPosition)) {
                Driver.winArray[Driver.i] = playerNumber;
                Driver.i++;
                Thread.currentThread().setName("Player " + Driver.i);
                return playerNumber;
            }
        }
    }

    public int calculatePlayerValue(int playerPosition, int diceValue) {

        playerPosition = playerPosition + diceValue;

        if (playerPosition > WIN) {
            playerPosition = playerPosition - diceValue;
        } else if (snake.containsKey(playerPosition)) {
            logger.info("Swallowed by snake at " + playerPosition);
            System.out.println("snake");
            playerPosition = snake.get(playerPosition);
        } else if (ladder.containsKey(playerPosition)) {
            logger.info("Climbed a ladder at " + playerPosition);
            System.out.println("ladder");
            playerPosition = ladder.get(playerPosition);
        }
        return playerPosition;
    }

    public boolean isWin(int playerPosition) {
        return WIN == playerPosition;
    }

    public int rollDice () {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

}
