package com.example.spotify.controlador;

import com.example.spotify.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import com.example.spotify.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import com.example.spotify.modelo.Conexion;

public class AppMainController {

    @FXML
    private Label nombreUsuariotxt;

    public void mostrarUsuariosPorConsola() {
        // Llamas a tu método para obtener todos los usuarios
        List<Usuario> usuarios = Usuario.obtenerTodosLosUsuarios();

        // Iteras sobre la lista e imprimes cada usuario
        for (Usuario usuario : usuarios) {
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Contraseña: " + usuario.getPassw());
            System.out.println("Icono de Usuario: " + Arrays.toString(usuario.getIconoUsuario()));
            System.out.println("------------------------");
        }
    }

    public void ventanaPrincipal() {
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

    public void busqueda(ActionEvent actionEvent){
        mostrarUsuariosPorConsola();
    }

    public void crearListaReproduccion(ActionEvent actionEvent){

    }

    public void configuracionCuenta(ActionEvent actionEvent){

    }

    public void cerrarSesion(ActionEvent actionEvent){
        Conexion conexion = new Conexion();
        try {
            cerrarVentana();
            conexion.tryDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) nombreUsuariotxt.getScene().getWindow();
        stage.close();
    }

}