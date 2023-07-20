package model;

import java.util.ArrayList;

import ui.CoinAnimation;
import ui.StageBuilder;

/**
 * A class that represents the main structure of the coin drop game
 */
public class Pachinko{
    private CoinAnimation animation;
    private ArrayList<Coin> coins;
    private int[][] layout;
    private int coinCount;
    private int winCount;

    /**
     * Constructor for Pachinko object
     */
    public Pachinko(){
        coins = new ArrayList<>();

        animation = new CoinAnimation();
        animation.start();

        genLayout();

        coinCount = 10;
        winCount = 0;

        StageBuilder.setTitle("Coin Drop Game (Coins left: " + coinCount + " Wins: " + winCount + ")");
    }

    public int[][] getLayout() {
        return layout;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public void addCoin(double x, double y) {
        Coin coin = new Coin(x, y);
        coins.add(coin);
        StageBuilder.addCoin(coin.getCoin());
        coinCount--;
    }

    /**
     * Method to generate the layout of the pachinko game
     */
    public void genLayout(){
        layout = new int[24][20];
        genBorder();
        genButton();
        genWinPot();
        genDebris();
    }

    /**
     * Method to generate the borders of layout
     */
    private void genBorder(){
        for(int i = 0; i < layout.length; i++){
            layout[i][0] = 1;
            layout[i][layout[i].length - 1] = 1;
        }
    }

    /**
     * Method to generate the buttons of layout
     */
    private void genButton(){
        for(int i = 1; i < layout[0].length - 1; i++){
            layout[0][i] = 2;
        }
    }

    /**
     * Method to generate the winning pots of layout
     */
    private void genWinPot(){
        for(int i = 0; i < 5; i++){
            layout[layout.length - 1][(int)(Math.random() * 18) + 1] = 3;
        }
    }

    /**
     * Method to randomly generate the debris of layout
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
}