package ui;

import javafx.animation.AnimationTimer;

import model.Coin;
import model.Pachinko;

import java.util.ArrayList;

/**
 * A class that handles the animations for coins
 */
public class CoinAnimation extends AnimationTimer {
    /**
     * Method that animates the coins
     */
    @Override
    public void handle(long arg0) {
        Pachinko pachinko = Pachinko.getInstance();

        int[][] layout = pachinko.getLayout();
        ArrayList<Coin> coins = pachinko.getCoins();

        for(Coin coin : coins){
            double x = coin.getCoinShape().getCenterX();
            double y = coin.getCoinShape().getCenterY();

            int layoutX = (int)(x / StageBuilder.SHAPE_SIZE);
            int layoutY = (int)((y + StageBuilder.SHAPE_SIZE / 2) / StageBuilder.SHAPE_SIZE);
            if(coin.getxVel() == 0 && layoutY >= 0 && layoutY < layout.length && layoutX >= 0 && layoutX < layout[layoutY].length && layout[layoutY][layoutX] == 4){
                if(layout[layoutY][layoutX + 1] != 1 && (layout[layoutY][layoutX - 1] == 1 || (int)(Math.random() * 2) == 0)){
                    coin.setxVel(StageBuilder.VELOCITY);
                }else{
                    coin.setxVel(-StageBuilder.VELOCITY);
                }
            }

            x += coin.getxVel();
            y += coin.getyVel();
            coin.getCoinShape().setCenterX(x);
            coin.getCoinShape().setCenterY(y);

            if(((int)(x - StageBuilder.SHAPE_SIZE / 2) % (int)StageBuilder.SHAPE_SIZE) == 0){
                coin.setxVel(0);
            }

            if(y - StageBuilder.SHAPE_SIZE / 2 > StageBuilder.HEIGHT) {
                StageBuilder.removeCoin(coin.getCoinShape());
                coins.remove(coin);
                if(layout[layout.length - 1][layoutX] == 3){
                    pachinko.setWinCount(pachinko.getWinCount() + 1);
                    StageBuilder.setTitle("Coin Drop Game (Coins left: " + pachinko.getCoinCount() +
                            " Wins: " + pachinko.getWinCount() + ")");
                }
                break;
            }
        }
    }
}