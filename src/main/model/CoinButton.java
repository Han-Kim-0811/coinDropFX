package model;

import javafx.scene.control.Button;
import ui.ButtonHandler;

public class CoinButton extends Button {

    public CoinButton(int x, int y, double size) {
        super("");
        setLayoutX(x * size);
        setLayoutY(y * size);
        setPrefWidth(size);
        setPrefHeight(size);
        setOnAction(new ButtonHandler((x + 0.5) * size, (y + 1.5) * size));
    }
}
