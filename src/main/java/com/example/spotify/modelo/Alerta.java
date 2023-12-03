package com.example.spotify.modelo;

import javafx.scene.control.Alert;

/**
 * Clase Java que permite controlar las alertas
 */
public class Alerta {
    /**
     * Muestra por pantalla una alerta del tipo especificado
     *
     * @param title     Titulo de la alerta (String)
     * @param message   Mensaje de la alerta (String)
     * @param alertType Tipo de la alerta (AlertType)
     */
    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alerta = new Alert(alertType);
        alerta.setTitle(title);
        alerta.setContentText(message);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}