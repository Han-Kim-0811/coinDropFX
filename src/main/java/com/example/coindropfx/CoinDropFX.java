package com.example.coindropfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * A pachinko coin drop game program implemented with JavaFX
 *
 * @author Donghan Kim (Han Kim) (SID: 100382712)
 * @version 2022-04-04
 */
public class CoinDropFX extends Application {
    private final double WIDTH = 500.0;
    private final double HEIGHT  = 600.0;
    private final double SHAPESIZE = 25.0;
    private final double VELOCITY = 2.5;

    private Pane root;

    /**
     * Main method
     *
     * @param args isn't used
     * @return void
     */
    public static void main(String[] args) {
        launch();
    }


    /**
     * Start method for javaFX
     *
     * @param stage is the stage to show
     * @return void
     */
    @Override
    public void start(Stage stage){
        root = new Pane();
        Pachinko pachinko = new Pachinko(stage);

        pachinko.add();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * A class that represents the pachinko coin drop game
     */
    private class Pachinko{
        private Stage stage;
        private CoinAnimation animation;
        private ArrayList<Coin> coins;
        private int[][] layout;
        private int coin_count;
        private int win_count;

        /**
         * Constructor for pachinko
         *
         * @param stage is the stage to show
         */
        public Pachinko(Stage stage){
            this.stage = stage;

            coins = new ArrayList<Coin>();

            animation = new CoinAnimation();
            animation.start();

            genLayout();

            coin_count = 10;
            win_count = 0;
            stage.setTitle("FX Pachinko (Coins left: " + coin_count + " Wins: " + win_count + ")");
        }

        /**
         * Method to add the pachinko to the stage
         *
         * @return void
         */
        public void add(){
            for(int i = 0; i < layout.length; i++){
                for(int j = 0; j < layout[i].length; j++){
                    switch(layout[i][j]){
                        case 1:
                            Rectangle wall = new Rectangle(j * SHAPESIZE, i * SHAPESIZE, SHAPESIZE, SHAPESIZE);
                            root.getChildren().add(wall);
                            break;
                        case 2:
                            Button button = new Button("");
                            button.setLayoutX(j * SHAPESIZE);
                            button.setLayoutY(i * SHAPESIZE);
                            button.setPrefHeight(SHAPESIZE);
                            button.setPrefWidth(SHAPESIZE);
                            button.setOnAction(new ButtonHandler((j + 0.5) * SHAPESIZE, (i + 1.5) * SHAPESIZE));
                            root.getChildren().add(button);
                            break;
                        case 3:
                            Rectangle win = new Rectangle(j * SHAPESIZE, i * SHAPESIZE, SHAPESIZE, SHAPESIZE);
                            win.setFill(Color.GREEN);
                            root.getChildren().add(win);
                            break;
                        case 4:
                            Polygon debris = new Polygon(j * SHAPESIZE, (i + 1)* SHAPESIZE, (j + 1) * SHAPESIZE, (i + 1) * SHAPESIZE, (j + 0.5) * SHAPESIZE, i * SHAPESIZE);
                            debris.setFill(Color.RED);
                            root.getChildren().add(debris);
                    }
                }
            }
        }

        /**
         * Method to generate the layout of the pachinko game
         *
         * @return void
         */
        private void genLayout(){
            layout = new int[24][20];
            genBorder();
            genButton();
            genWinPot();
            genDebris();
        }

        /**
         * Method to generate the borders of layout
         *
         * @return void
         */
        private void genBorder(){
            for(int i = 0; i < layout.length; i++){
                layout[i][0] = 1;
                layout[i][layout[i].length - 1] = 1;
            }
        }

        /**
         * Method to generate the buttons of layout
         *
         * @return void
         */
        private void genButton(){
            for(int i = 1; i < layout[0].length - 1; i++){
                layout[0][i] = 2;
            }
        }

        /**
         * Method to generate the winning pots of layout
         *
         * @return void
         */
        private void genWinPot(){
            for(int i = 0; i < 5; i++){
                layout[layout.length - 1][(int)(Math.random() * 18) + 1] = 3;
            }
        }

        /**
         * Method to randomly generate the debris of layout
         *
         * @return void
         */
        private void genDebris(){
            for(int i = 2; i < layout.length - 2; i++){
                for(int j = 1; j < layout[i].length - 1; j++){
                    if(!isDebrisNear(j, i) && (int)(Math.random() * 3) == 0){
                        layout[i][j] = 4;
                    }
                }
            }
        }

        /**
         * Method to check if a debris is near the given position
         *
         * @param x is the x-coordinate of the position to check
         * @param y is the y-coordinate of the position to check
         * @return true if a debris is near, false if not
         */
        private boolean isDebrisNear(int x, int y){
            for(int i = y - 1; i <= y + 1; i++){
                for(int j = x - 1; j <= x + 1; j++){
                    if(layout[i][j] == 4){
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * A class that handles the animations for the coin
         */
        private class CoinAnimation extends AnimationTimer{

            /**
             * Method that animates the coins
             *
             * @return void
             */
            @Override
            public void handle(long arg0) {
                for(Coin coin : coins){
                    double x = coin.getCoin().getCenterX();
                    double y = coin.getCoin().getCenterY();

                    int layoutX = (int)(x / SHAPESIZE);
                    int layoutY = (int)((y + SHAPESIZE / 2) / SHAPESIZE);
                    if(coin.getxVel() == 0 && layoutY >= 0 && layoutY < layout.length && layoutX >= 0 && layoutX < layout[layoutY].length && layout[layoutY][layoutX] == 4){
                        if(layout[layoutY][layoutX + 1] != 1 && (layout[layoutY][layoutX - 1] == 1 || (int)(Math.random() * 2) == 0)){
                            coin.setxVel(VELOCITY);
                        }else{
                            coin.setxVel(-VELOCITY);
                        }
                    }

                    x += coin.getxVel();
                    y += coin.getyVel();
                    coin.getCoin().setCenterX(x);
                    coin.getCoin().setCenterY(y);

                    if(((int)(x - SHAPESIZE / 2) % (int)SHAPESIZE) == 0){
                        coin.setxVel(0);
                    }

                    if(y - SHAPESIZE / 2 > HEIGHT) {
                        coin.remove();
                        coins.remove(coin);
                        if(layout[layout.length - 1][layoutX] == 3){
                            win_count++;
                            stage.setTitle("FX Bouncing Balls (Coins left: " + coin_count + " Wins: " + win_count + ")");
                        }
                        break;
                    }
                }
            }
        }

        /**
         * Class that handles the event for generating a coin under the button when clicked
         */
        private class ButtonHandler implements EventHandler<ActionEvent> {
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
                if(coin_count > 0) {
                    Coin coin = new Coin(x, y);
                    coins.add(coin);
                    coin.add();
                    coin_count--;
                }else if(coins.isEmpty()){
                    root.getChildren().remove(0, root.getChildren().size());
                    genLayout();
                    add();
                    coin_count = 10;
                }
                stage.setTitle("FX Bouncing Balls (Coins left: " + coin_count + " Wins: " + win_count + ")");
            }
        }

        /**
         * Class that represents Coin
         */
        private class Coin{
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
                coin = new Circle(x, y, SHAPESIZE / 2);
                coin.setFill(Color.GOLD);
                xVel = 0;
                yVel = VELOCITY;
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

            /**
             * Method that adds the coin to the stage
             *
             * @return void
             */
            public void add(){
                root.getChildren().add(coin);
            }

            /**
             * Method that removes the coin from the stage
             *
             * @return void
             */
            public void remove(){
                root.getChildren().remove(coin);
            }
        }
    }
}