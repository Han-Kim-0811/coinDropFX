package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Class that handles the event for generating a coin under the button when clicked
 */
public class ButtonHandler implements EventHandler<ActionEvent> {
    private double x;
    private double y;

    /**
     * Constructor for ButtonHandler
     *
     * @param x is the x-coordinate for the coin to spawn
     * @param y is the y-coordinate for the coin to spawn
     */
    public ButtonHandler(double x, double y){
        this.x = x;
        this.y = y;
    }


    /**
     * Method that spawns a coin under the button when clicked
     *
     * @param e is the event "Click button" to handle
     */
    @Override
    public void handle(ActionEvent e){
        if(StageBuilder.pachinko.getCoinCount() > 0) {
            StageBuilder.pachinko.addCoin(x, y);
        }else if(StageBuilder.pachinko.getCoins().isEmpty()){
            StageBuilder.rebuildPachinko();
        }
        StageBuilder.setTitle("Coin Drop Game (Coins left: " + StageBuilder.pachinko.getCoinCount() +
                " Wins: " + StageBuilder.pachinko.getWinCount() + ")");
    }
}
