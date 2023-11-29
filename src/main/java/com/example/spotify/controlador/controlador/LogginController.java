package com.example.spotify.controlador.controlador;

import com.example.spotify.controlador.controlador.AppMainController;
import com.example.spotify.controlador.modelo.Alerta;
import com.example.spotify.controlador.modelo.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogginController {
    @FXML
    private TextField nombreTxt;
    @FXML
    private TextField correoTxt;
    @FXML
    private TextField passTxt;

    AppMainController appMainController = new AppMainController();

    @FXML
    public void iniciarSesion(ActionEvent actionEvent) {
        /*Conexion conexion = new Conexion();
        if (conexion.tryConnect()) {
            Alerta.showAlert("Exito", "Se inició sesión correctamente.", Alert.AlertType.INFORMATION);
            cerrarVentana();
            appMainController.ventana();
        } else {
            Alerta.showAlert("Error", "No se pudo iniciar sesión.", Alert.AlertType.INFORMATION);
        }*/

        Alerta.showAlert("Exito", "Se inició sesión correctamente.", Alert.AlertType.INFORMATION);
        cerrarVentana();
        appMainController.ventana();
    }

    public void registrarse(ActionEvent actionEvent){
        Alerta.showAlert("Exito","Registrarse clicado", Alert.AlertType.INFORMATION);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) nombreTxt.getScene().getWindow();
        stage.close();
    }

}