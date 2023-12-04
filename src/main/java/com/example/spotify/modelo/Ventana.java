package com.example.spotify.modelo;

import com.example.spotify.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Ventana {

    public static void ventanaLogin(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Ventana.class.getResource("/com/example/spotify/userViews/LogginView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            scene.getStylesheets().add(Ventana.class.getResource("/estilos/boton.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Inicio de sesión");
            stage.setScene(scene);
            stage.close();
            stage.show();
        } catch (IOException e) {
            Alerta.showAlert("Error","Error al cargar la ventana de login.", Alert.AlertType.WARNING);
        }
    }

    public static void ventanaRegister() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/spotify/userViews/RegisterView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Registro");
            // Establecer la modalidad antes de mostrar la ventana
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alerta.showAlert("Error","Error al cargar la ventana de registro.", Alert.AlertType.WARNING);
        }
    }

    public static void ventanaMainApp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/spotify/userViews/AppMainView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Spotify");
            // Establecer la modalidad antes de mostrar la ventana
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alerta.showAlert("Error","Error al cargar la ventana principal.", Alert.AlertType.WARNING);
        }
    }

    public static void ventanaModifier() {

    }

    public static void ventanaAdmin(){

    }

}
