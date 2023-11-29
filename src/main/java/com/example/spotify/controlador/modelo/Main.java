package com.example.spotify.controlador.modelo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/spotify/LogginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            scene.getStylesheets().add(getClass().getResource("/estilos/boton.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Spotify!");
            stage.setScene(scene);
            stage.close();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}