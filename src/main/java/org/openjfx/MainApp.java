package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
            var cl = getClass().getClassLoader();
            Parent root = FXMLLoader.load(Objects.requireNonNull(cl.getResource("scene.fxml")));

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            stage.setTitle("JavaFX and Gradle");
            stage.setScene(scene);
            stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}