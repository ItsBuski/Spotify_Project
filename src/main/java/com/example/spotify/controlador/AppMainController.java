package com.example.spotify.controlador;

import com.example.spotify.Main;
import com.example.spotify.modelo.CrudUsuarios;
import com.example.spotify.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import com.example.spotify.modelo.Ventana;
import com.example.spotify.modelo.Conexion;

import java.net.URL;
import java.util.ResourceBundle;

public class AppMainController implements Initializable {
    @FXML
    private Label nombreUsuariotxt;
    @FXML
    private ImageView iconoUsuarioMain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Conexion conexion = new Conexion();
        if (conexion.tryConnect()) {
            Usuario usuario = CrudUsuarios.obtenerUsuarioParaApp();
            nombreUsuariotxt.setText(usuario.getNombre());
            iconoUsuarioMain.setImage(CrudUsuarios.recuperarImagenBytes(usuario.getIconoUsuario()));
        }
    }

    public void busqueda(ActionEvent actionEvent){

    }

    public void crearListaReproduccion(ActionEvent actionEvent){

    }

    public void configuracionCuenta(ActionEvent actionEvent){
        Ventana.ventanaModifier();
    }

    public void cerrarSesion(ActionEvent actionEvent){
        Conexion conexion = new Conexion();
        try {
            cerrarVentana();
            conexion.tryDisconnect();
            Ventana.ventanaLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) nombreUsuariotxt.getScene().getWindow();
        stage.close();
    }
}