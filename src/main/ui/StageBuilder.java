package ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Pachinko;

public class StageBuilder {
    public static final double WIDTH = 500.0;
    public static final double HEIGHT  = 600.0;
    public static final double SHAPESIZE = 25.0;
    public static final double VELOCITY = 2.5;

    public static Pachinko pachinko;

    private static Stage stage;
    private static Pane root;

    public static void buildStage(Stage stage) {
        StageBuilder.stage = stage;
        root = new Pane();

        pachinko = new Pachinko();
        buildPachinko();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


    public static void addCoin(Circle coin) {
        root.getChildren().add(coin);
    }

    public static void removeCoin(Circle coin){
        root.getChildren().remove(coin);
    }

    private static void buildPachinko() {
        int[][] layout = pachinko.getLayout();

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

    public static void setTitle(String title) {
        stage.setTitle(title);
    }

    public static void rebuildPachinko() {
        pachinko.genLayout();
        pachinko.setCoinCount(10);
        root.getChildren().remove(0, root.getChildren().size());
        buildPachinko();
    }
}
