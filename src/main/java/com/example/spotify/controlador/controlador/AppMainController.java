package com.example.spotify.controlador.controlador;

import com.example.spotify.controlador.modelo.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMainController {
    @FXML
    private VBox cancionesBuscadasUsuarios;
    @FXML
    private VBox listasUsuarios;

    public void ventana() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/spotify/AppMainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
            Stage stage = new Stage();
            stage.setResizable(false);

            // Establecer la modalidad antes de mostrar la ventana
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}