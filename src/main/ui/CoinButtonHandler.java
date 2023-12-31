package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Pachinko;

/**
 * Class that handles the event for CoinButton.
 */
public class CoinButtonHandler implements EventHandler<ActionEvent> {
    private double x;
    private double y;

    /**
     * Constructor for CoinButtonHandler.
     *
     * @param x is the x-coordinate for the coin to spawn.
     * @param y is the y-coordinate for the coin to spawn.
     */
    public CoinButtonHandler(double x, double y){
        this.x = x;
        this.y = y;
    }


    /**
     * Method that spawns a coin under the button when clicked.
     * When there isn't any coins left to spawn, the method will reset the pachinko machine.
     *
     * @param e is the event "Click button" to handle.
     */
    @Override
    public void handle(ActionEvent e){
        Pachinko pachinko = Pachinko.getInstance();

        if(pachinko.getCoinCount() > 0) {
            pachinko.addCoin(x, y);
        }else if(pachinko.getCoins().isEmpty()){
            StageBuilder.rebuildPachinko();
        }
        StageBuilder.setTitle("Coin Drop Game (Coins left: " + pachinko.getCoinCount() +
                " Wins: " + pachinko.getWinCount() + ")");
    }
}
