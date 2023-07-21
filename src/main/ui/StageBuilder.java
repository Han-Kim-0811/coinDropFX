package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.CoinButton;
import model.Pachinko;

/**
 * Class that is responsible for making the GUI of the coin drop game application.
 * This class will build the GUI according to the layout of the pachinko machine.
 * This is a utility class, which is not meant to be constructed.
 */
public class StageBuilder {
    public static final double WIDTH = 500.0;
    public static final double HEIGHT  = 600.0;
    public static final double SHAPE_SIZE = 25.0;
    public static final double VELOCITY = 2.5;

    public static Pachinko pachinko;

    private static Stage stage;
    private static Pane root;

    /**
     * Static method that builds the GUI.
     *
     * @param stage is the stage to build.
     */
    public static void buildStage(Stage stage) {
        StageBuilder.stage = stage;
        root = new Pane();

        pachinko = new Pachinko();
        buildPachinko();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Static method that adds a coin to the GUI.
     *
     * @param coin is the coin to add.
     */
    public static void addCoin(Circle coin) {
        root.getChildren().add(coin);
    }

    /**
     * Static method that removes a coin to the GUI.
     *
     * @param coin is the coin to remove.
     */
    public static void removeCoin(Circle coin){
        root.getChildren().remove(coin);
    }

    /**
     * Static method that builds the GUI of pachinko machine accroding to its layout.
     */
    private static void buildPachinko() {
        int[][] layout = pachinko.getLayout();

        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[i].length; j++){
                switch(layout[i][j]){
                    case 1:
                        Rectangle wall = new Rectangle(j * SHAPE_SIZE, i * SHAPE_SIZE, SHAPE_SIZE, SHAPE_SIZE);
                        root.getChildren().add(wall);
                        break;
                    case 2:
                        Button coinButton = new CoinButton(j, i, SHAPE_SIZE);
                        root.getChildren().add(coinButton);
                        break;
                    case 3:
                        Rectangle win = new Rectangle(j * SHAPE_SIZE, i * SHAPE_SIZE, SHAPE_SIZE, SHAPE_SIZE);
                        win.setFill(Color.GREEN);
                        root.getChildren().add(win);
                        break;
                    case 4:
                        Polygon debris = new Polygon(j * SHAPE_SIZE, (i + 1)* SHAPE_SIZE, (j + 1) * SHAPE_SIZE, (i + 1) * SHAPE_SIZE, (j + 0.5) * SHAPE_SIZE, i * SHAPE_SIZE);
                        debris.setFill(Color.RED);
                        root.getChildren().add(debris);
                }
            }
        }
    }

    /**
     * Static method that changes the title of the window of the GUI.
     * The title of the window is used to show the following two information.
     *      1. The number of coins left.
     *      2. The number of coins dropped into the winning pot.
     */
    public static void setTitle(String title) {
        stage.setTitle(title);
    }

    /**
     * Static method that rebuilds pachinko machine.
     * The rebuild process has the following two procedures.
     *      1. Reset the layout of the pachinko machine.
     *      2. Reset the number of coins left.
     */
    public static void rebuildPachinko() {
        pachinko.genLayout();
        pachinko.setCoinCount(10);
        root.getChildren().remove(0, root.getChildren().size());
        buildPachinko();
    }
}
