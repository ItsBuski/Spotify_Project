package com.example.spotify.controlador;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.example.spotify.modelo.Alerta;
import com.example.spotify.modelo.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogginController {

    @FXML
    private TextField correoTxt;
    @FXML
    private TextField passTxt;

    AppMainController appMainController = new AppMainController();
    RegisterController registerController = new RegisterController();

    @FXML
    public void iniciarSesion(ActionEvent actionEvent) {
       verificarDatosUsuario();
    }

    public void verificarDatosUsuario() {
        String correo = correoTxt.getText();
        String pass = passTxt.getText();

        if (!correo.isEmpty()) {
            if (correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
                if (!pass.isEmpty()) {
                    Conexion conexion = new Conexion();
                    String sentenciaSql = "SELECT email, passw FROM usuarios where email = ? and passw = ?";
                    PreparedStatement sentencia = null;
                    ResultSet resultado = null;

                    if (conexion.tryConnect()) {
                        try {
                            sentencia = conexion.connection.prepareStatement(sentenciaSql);
                            sentencia.setString(1, correo);
                            sentencia.setString(2, pass);
                            resultado = sentencia.executeQuery();

                            if (resultado.next()) {
                                String email = resultado.getString("email");
                                String passw = resultado.getString("passw");
                                Alerta.showAlert("Éxito", "Inicio de sesión exitoso.", Alert.AlertType.CONFIRMATION);
                                cerrarVentana();
                                appMainController.ventanaPrincipal();
                            } else {
                                Alerta.showAlert("Error", "Correo o contraseña incorrectos.", Alert.AlertType.WARNING);
                            }
                        } catch (SQLException sqle) {
                            sqle.printStackTrace();
                        } finally {
                            if (sentencia != null && resultado != null) {
                                try {
                                    sentencia.close();
                                    resultado.close();
                                } catch (SQLException sqle) {
                                    sqle.printStackTrace();
                                }
                            }
                        }
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

    public void registrarse(ActionEvent actionEvent){
        cerrarVentana();
        registerController.ventanaRegistro();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) correoTxt.getScene().getWindow();
        stage.close();
    }

}