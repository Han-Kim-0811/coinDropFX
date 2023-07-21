package model;

import javafx.scene.control.Button;
import ui.CoinButtonHandler;

/**
 * Class that represents a coin button.
 * Coin buttons are buttons, which spawns coins when pushed.
 * Coin buttons are component of the pachinko machine.
 */
public class CoinButton extends Button {

    /**
     * Constructor of CoinButton instance.
     *
     * @param x is the x-coordinate of the button according to the layout.
     * @param y is the y-coordinate of the button according to the layout.
     * @param size is the width and height of the button.
     */
    public CoinButton(int x, int y, double size) {
        super("");
        setLayoutX(x * size);
        setLayoutY(y * size);
        setPrefWidth(size);
        setPrefHeight(size);
        setOnAction(new CoinButtonHandler((x + 0.5) * size, (y + 1.5) * size));
    }
}
