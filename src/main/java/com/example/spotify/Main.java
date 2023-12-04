package com.example.spotify;

import com.example.spotify.modelo.Conexion;
import com.example.spotify.modelo.Ventana;
import javafx.stage.Stage;
import javafx.application.Application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

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
    @Override
    public void start(Stage stage){
        Conexion conexion = new Conexion();
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

    public static void usuarioActual(String correo) {
        Properties properties = new Properties();
        properties.setProperty("usuario", correo);
        try {
            properties.store(new FileWriter("src/main/resources/usuarioActual.properties"), "Usuario actual.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO cambiar a null el usuario cuando cerremos sesion
    // TODO Modificar nombre usuario
    // TODO Modificar email usuario
    // TODO Modificar contraseña usuario
    // TODO Cancion clase
    // TODO Reproductor clase
    // TODO CRUD canciones (administradores)
    // TODO Inicio sesion de administrador
    // TODO Ventana class añadir ventanas faltantes de las nuevas vistas por añadir
    // TODO Busqueda canciones como usuario
    // TODO Creacion de listas de reproduccion
    // TODO pasar mp3 a bytes y a blob en BBDD
    // TODO pasar blob en BBDD a bytes y a mp3 para reproducir
}