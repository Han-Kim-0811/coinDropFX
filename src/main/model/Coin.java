package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.Driver;
import ui.StageBuilder;

/**
 * Class that represents Coin
 */
public class Coin{
    private Circle coin;
    private double xVel;
    private double yVel;

    /**
     * Constructor of Coin
     *
     * @param x is the x-coordinate of the center of the coin
     * @param y is the y-coordinate of the center of the coin
     */
    public Coin(double x, double y){
        coin = new Circle(x, y, StageBuilder.SHAPESIZE / 2);
        coin.setFill(Color.GOLD);
        xVel = 0;
        yVel = StageBuilder.VELOCITY;
    }

    /**
     * Getter method of xVel
     *
     * @return horizontal velocity of the coin
     */
    public double getxVel(){
        return this.xVel;
    }

    /**
     * Getter method of yVel
     *
     * @return vertical velocity of the coin
     */
    public double getyVel(){
        return this.yVel;
    }

    /**
     * Setter method of xVel
     *
     * @param xVel is horizontal velocity to set
     * @return void
     */
    public void setxVel(double xVel){
        this.xVel = xVel;
    }

    /**
     * Getter method of coin
     *
     * @return the circle, which represents the coin
     */
    public Circle getCoin(){
        return this.coin;
    }
}