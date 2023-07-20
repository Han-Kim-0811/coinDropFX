package ui;

import javafx.animation.AnimationTimer;

import model.Coin;

import java.util.ArrayList;

/**
 * A class that handles the animations for the coin
 */
public class CoinAnimation extends AnimationTimer {
    /**
     * Method that animates the coins
     *
     * @return void
     */
    @Override
    public void handle(long arg0) {
        int[][] layout = StageBuilder.pachinko.getLayout();
        ArrayList<Coin> coins = StageBuilder.pachinko.getCoins();

        for(Coin coin : coins){
            double x = coin.getCoin().getCenterX();
            double y = coin.getCoin().getCenterY();

            int layoutX = (int)(x / StageBuilder.SHAPESIZE);
            int layoutY = (int)((y + StageBuilder.SHAPESIZE / 2) / StageBuilder.SHAPESIZE);
            if(coin.getxVel() == 0 && layoutY >= 0 && layoutY < layout.length && layoutX >= 0 && layoutX < layout[layoutY].length && layout[layoutY][layoutX] == 4){
                if(layout[layoutY][layoutX + 1] != 1 && (layout[layoutY][layoutX - 1] == 1 || (int)(Math.random() * 2) == 0)){
                    coin.setxVel(StageBuilder.VELOCITY);
                }else{
                    coin.setxVel(-StageBuilder.VELOCITY);
                }
            }

            x += coin.getxVel();
            y += coin.getyVel();
            coin.getCoin().setCenterX(x);
            coin.getCoin().setCenterY(y);

            if(((int)(x - StageBuilder.SHAPESIZE / 2) % (int)StageBuilder.SHAPESIZE) == 0){
                coin.setxVel(0);
            }

            if(y - StageBuilder.SHAPESIZE / 2 > StageBuilder.HEIGHT) {
                StageBuilder.removeCoin(coin.getCoin());
                coins.remove(coin);
                if(layout[layout.length - 1][layoutX] == 3){
                    StageBuilder.pachinko.setWinCount(StageBuilder.pachinko.getWinCount() + 1);
                    StageBuilder.setTitle("Coin Drop Game (Coins left: " + StageBuilder.pachinko.getCoinCount() +
                            " Wins: " + StageBuilder.pachinko.getWinCount() + ")");
                }
                break;
            }
        }
    }
}