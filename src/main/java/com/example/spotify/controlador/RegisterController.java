package com.example.spotify.controlador;

import java.io.*;
import java.net.URL;

import com.example.spotify.Main;
import com.example.spotify.modelo.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.nio.ByteBuffer;
import javafx.scene.image.*;

import java.sql.Blob;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class RegisterController implements Initializable {
    Main main = new Main();
    @FXML
    private TextField nombreUsuarioNuevoTxt;
    @FXML
    private TextField emailUsuarioNuevoTxt;
    @FXML
    private PasswordField passUsuarioNuevoTxt;
    @FXML
    private PasswordField confirmarPassTxt;
    @FXML
    private ImageView iconoImageView;
    private Image[] iconos;  // Arreglo para almacenar las imágenes
    private String[] rutaIconos;
    private int indiceIconoActual = 0;  // Índice de la imagen actual
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar el arreglo de imágenes
        iconos = new Image[]{
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario1.png")),
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario2.png")),
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario3.png")),
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario4.png"))
        };

        rutaIconos = new String[] {
                new String("/imagenes/iconoUsuario1.png"),
                new String("/imagenes/iconoUsuario2.png"),
                new String("/imagenes/iconoUsuario3.png"),
                new String("/imagenes/iconoUsuario4.png")
        };

        // Mostrar la primera imagen al inicio
        iconoImageView.setImage(iconos[indiceIconoActual]);
    }

    @FXML
    public void confirmarRegistro(ActionEvent actionEvent) {
        Image imagenElegida = iconoImageView.getImage();
        Usuario usuario = new Usuario();
        if (camposNoVacios() && passCoinciden()) {
            // Configurar datos del usuario
            usuario.setNombre(nombreUsuarioNuevoTxt.getText().trim());
            usuario.setEmail(emailUsuarioNuevoTxt.getText().trim());
            usuario.setPassw(passUsuarioNuevoTxt.getText().trim());
            //usuario.setIconoUsuario((Blob));
            if(CrudUsuarios.insertarUsuario(usuario, main)) {
                cerrarVentana();
                Ventana.ventanaMainApp();
                main.setUsuario(usuario.getEmail());
            }
        }
    }

    private boolean camposNoVacios() {
        if (nombreUsuarioNuevoTxt.getText().trim().isBlank()) {
            Alerta.showAlert("Error","Debe ingresar su nombre de usuario.", Alert.AlertType.WARNING);
            return false;
        }
        if (emailUsuarioNuevoTxt.getText().trim().isBlank()) {
            Alerta.showAlert("Error","Debe ingresar su correo electrónico.", Alert.AlertType.WARNING);
            return false;
        }
        if (!emailUsuarioNuevoTxt.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
            Alerta.showAlert("Error", "Formato de correo electrónico inválido.", Alert.AlertType.WARNING);
            return false;
        }
        if (passUsuarioNuevoTxt.getText().trim().isBlank()) {
            Alerta.showAlert("Error","Debe ingresar una contraseña.", Alert.AlertType.WARNING);
            return false;
        }
        if (confirmarPassTxt.getText().trim().isBlank()) {
            Alerta.showAlert("Error","Debe confirmar su contraseña.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private boolean passCoinciden() {
        if (!confirmarPassTxt.getText().equals(passUsuarioNuevoTxt.getText())) {
            Alerta.showAlert("Error","Las contraseñas deben coincidir.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    @FXML
    void seleccionarIcono(ActionEvent event) {
        // Alternar al siguiente índice de imagen
        indiceIconoActual = (indiceIconoActual + 1) % iconos.length;

        // Mostrar la imagen correspondiente
        iconoImageView.setImage(iconos[indiceIconoActual]);
    }

    /**
     * Método para cancelar el registro y volver a la ventana de inicio de sesión.
     * @param actionEvent Evento de acción asociado al botón de cancelar.
     */
    public void cancelarRegistro(ActionEvent actionEvent){
        cerrarVentana();
        Ventana.ventanaLogin();
    }

    /**
     * Método para cerrar la ventana.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) nombreUsuarioNuevoTxt.getScene().getWindow();
        stage.close();
    }
/*
    public void obtenerIcono() {
        for (int i = 0; i < rutaIconos.length; i++) {

        }
        File imagen = new File(ruta);
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(imagen));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/

}