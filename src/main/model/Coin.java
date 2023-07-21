package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.StageBuilder;

/**
 * Class that represents a coin.
 * Coins are component of the pachinko machine.
 */
public class Coin{
    private Circle coinShape;
    private double xVel;
    private double yVel;

    /**
     * Constructor of Coin object.
     *
     * @param x is the x-coordinate of the center of the coin.
     * @param y is the y-coordinate of the center of the coin.
     */
    public Coin(double x, double y){
        coinShape = new Circle(x, y, StageBuilder.SHAPESIZE / 2);
        coinShape.setFill(Color.GOLD);
        xVel = 0;
        yVel = StageBuilder.VELOCITY;
    }

    /**
     * Getter method of xVel.
     *
     * @return the horizontal velocity of the coin.
     */
    public double getxVel(){
        return xVel;
    }

    /**
     * Getter method of yVel.
     *
     * @return the vertical velocity of the coin.
     */
    public double getyVel(){
        return yVel;
    }

    /**
     * Setter method of xVel.
     *
     * @param xVel is horizontal velocity to set.
     */
    public void setxVel(double xVel){
        this.xVel = xVel;
    }

    /**
     * Getter method of coinShape.
     *
     * @return the JavaFX shape circle, which represents the coin.
     */
    public Circle getCoinShape(){
        return coinShape;
    }
}