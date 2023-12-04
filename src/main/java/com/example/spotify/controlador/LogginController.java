package com.example.spotify.controlador;

import com.example.spotify.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.example.spotify.modelo.Alerta;
import com.example.spotify.modelo.Ventana;
import com.example.spotify.modelo.CrudUsuarios;

/**
 * Controlador para la interfaz de inicio de sesión (LogginView).
 */
public class LogginController {
    Main main = new Main();
    @FXML
    private TextField correoTxt;
    @FXML
    private TextField passTxt;

    /**
     * Maneja el evento de inicio de sesión.
     *
     * @param actionEvent El evento de acción.
     */
    @FXML
    public void iniciarSesion(ActionEvent actionEvent) {
       verificarDatosUsuario();
    }

    /**
     * Verifica los datos del usuario ingresados en la interfaz de inicio de sesión.
     */
    public void verificarDatosUsuario() {
        String correo = correoTxt.getText();
        String pass = passTxt.getText();

        if (!correo.isBlank()) {
            if (correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
                if (!pass.isBlank()) {
                    if (CrudUsuarios.loginUsuario(correo, pass, main)) {
                        cerrarVentana();
                        Ventana.ventanaMainApp();
                        main.setUsuario(correo);
                    }
                } else {
                    Alerta.showAlert("Error", "Por favor, ingrese su contraseña.", Alert.AlertType.WARNING);
                }
            } else {
                Alerta.showAlert("Error", "Formato de correo electrónico inválido.", Alert.AlertType.WARNING);
            }
        } else {
            Alerta.showAlert("Error", "Por favor, ingrese su correo electrónico.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Maneja el evento de registro.
     *
     * @param actionEvent El evento de acción.
     */
    public void registrarse(ActionEvent actionEvent){
        cerrarVentana();
        Ventana.ventanaRegister();
    }

    /**
     * Cierra la ventana actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) correoTxt.getScene().getWindow();
        stage.close();
    }
}