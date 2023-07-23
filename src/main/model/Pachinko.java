package model;

import java.util.ArrayList;

import ui.CoinAnimation;
import ui.StageBuilder;

/**
 * A class that represents the pachinko machine.
 * The pachinko machine is main structure of the coin drop game.
 */
public class Pachinko{
    private static Pachinko pachinko;
    private CoinAnimation animation;
    private ArrayList<Coin> coins;
    private int[][] layout;
    private int coinCount;
    private int winCount;

    /**
     * Constructor for Pachinko instance.
     */
    private Pachinko(){
        coins = new ArrayList<>();

        animation = new CoinAnimation();
        animation.start();

        genLayout();

        coinCount = 10;
        winCount = 0;

        StageBuilder.setTitle("Coin Drop Game (Coins left: " + coinCount + " Wins: " + winCount + ")");
    }

    /**
     * Getter for the single Pachinko instance.
     *
     * @return the single pachinko instance
     */
    public static Pachinko getInstance() {
        if(pachinko == null) {
            pachinko = new Pachinko();
        }

        return pachinko;
    }

    /**
     * Getter method of layout.
     *
     * @return the layout of borders, obstacles, winning pots of the pachinko machine.
     */
    public int[][] getLayout() {
        return layout;
    }

    /**
     * Getter method of coins.
     *
     * @return the list of coins existing in the game.
     */
    public ArrayList<Coin> getCoins() {
        return coins;
    }

    /**
     * Getter method of coinCount.
     *
     * @return the count of unused coins.
     */
    public int getCoinCount() {
        return coinCount;
    }

    /**
     * Setter method of coinCount.
     */
    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    /**
     * Getter method of winCount.
     *
     * @return the count of coins dropped into the winning pots.
     */
    public int getWinCount() {
        return winCount;
    }

    /**
     * Setter method of winCount.
     */
    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    /**
     * Method to add a coin to the game.
     * The added coin will drop according to the rules.
     */
    public void addCoin(double x, double y) {
        Coin coin = new Coin(x, y);
        coins.add(coin);
        StageBuilder.addCoin(coin.getCoinShape());
        coinCount--;
    }

    /**
     * Method to generate the layout of the pachinko machine.
     */
    public void genLayout(){
        layout = new int[24][20];
        genBorder();
        genButton();
        genWinPot();
        genObstacle();
    }

    /**
     * Method to generate the borders in the layout.
     * The integer 1 represents the borders.
     */
    private void genBorder(){
        for(int i = 0; i < layout.length; i++){
            layout[i][0] = 1;
            layout[i][layout[i].length - 1] = 1;
        }
    }

    /**
     * Method to generate the buttons in the layout.
     * The integer 2 represents the buttons
     */
    private void genButton(){
        for(int i = 1; i < layout[0].length - 1; i++){
            layout[0][i] = 2;
        }
    }

    /**
     * Method to generate the winning pots in the layout.
     * The integer 3 represents the winning pots.
     */
    private void genWinPot(){
        for(int i = 0; i < 5; i++){
            layout[layout.length - 1][(int)(Math.random() * 18) + 1] = 3;
        }
    }

    /**
     * Method to randomly generate the obstacles in the layout.
     * The integer 4 represents the obstacles.
     */
    private void genObstacle(){
        for(int i = 2; i < layout.length - 2; i++){
            for(int j = 1; j < layout[i].length - 1; j++){
                if(!isObstaclesNear(j, i) && (int)(Math.random() * 3) == 0){
                    layout[i][j] = 4;
                }
            }
        }
    }

    /**
     * Method to check if an obstacles is near the given position.
     *
     * @param x is the x-coordinate of the position to check.
     * @param y is the y-coordinate of the position to check.
     * @return true if an obstacles is near, false if not.
     */
    private boolean isObstaclesNear(int x, int y){
        for(int i = y - 1; i <= y + 1; i++){
            for(int j = x - 1; j <= x + 1; j++){
                if(layout[i][j] == 4){
                    return true;
                }
            }
        }
        return false;
    }
}