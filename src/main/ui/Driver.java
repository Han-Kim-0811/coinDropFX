package ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The driver of the coin drop game application
 *
 * @author Han Kim
 * @version 2023-07-19
 */
public class Driver extends Application {
    /**
     * Main method, which launches the coin drop game application
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
        StageBuilder.buildStage(stage);
    }
}