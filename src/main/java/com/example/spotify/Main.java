package com.example.spotify;

import com.example.spotify.modelo.Conexion;
import com.example.spotify.modelo.Ventana;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Clase principal que inicia la aplicación JavaFX para el sistema Spotify.
 */
public class Main extends Application {
    /**
     * Método principal que se llama al iniciar la aplicación.
     * Se encarga de cargar la interfaz de inicio de sesión y mostrar la ventana correspondiente.
     *
     * @param stage El objeto Stage que representa la ventana principal de la aplicación.
     */
    Conexion conexion = new Conexion();
    String usuario = "";

    @Override
    public void start(Stage stage){

        if (conexion.tryConnect()){
            Ventana.ventanaLogin();
        } else {
            System.out.println("Error al conectarse a la base de datos.");
        }
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch();
    }
    /**
     * Método adicional para mostrar la ventana principal.
     * Se llama desde otras partes del código cuando se necesita volver a mostrar la ventana principal.
     */
    public static void mostrarVentana() {
        launch();
    }

    public Conexion getConexion() {
        return conexion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // TODO cambiar a null el usuario cuando cerremos sesion
}