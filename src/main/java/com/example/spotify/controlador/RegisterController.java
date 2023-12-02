package com.example.spotify.controlador;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.ByteBuffer;
import java.io.IOException;
import javafx.scene.image.*;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import com.example.spotify.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.example.spotify.modelo.Alerta;
import javafx.scene.control.PasswordField;
import com.example.spotify.modelo.Usuario;

public class RegisterController implements Initializable {

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
    private int indiceIconoActual = 0;  // Índice de la imagen actual

    Main main = new Main();
    AppMainController appMainController = new AppMainController();
    Usuario usuario = new Usuario();

    public void ventanaRegistro() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/spotify/RegisterView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setResizable(false);

            // Establecer la modalidad antes de mostrar la ventana
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alerta.showAlert("Error","Error al cargar la ventana de registro.",Alert.AlertType.WARNING);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar el arreglo de imágenes
        iconos = new Image[]{
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario1.png")),
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario2.png")),
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario3.png")),
                new Image(getClass().getResourceAsStream("/imagenes/iconoUsuario4.png"))
        };

        // Mostrar la primera imagen al inicio
        iconoImageView.setImage(iconos[indiceIconoActual]);
    }

    @FXML
    public void confirmarRegistro(ActionEvent actionEvent) {
        if (camposNoVacios() && passCoinciden()) {
            // Configurar datos del usuario
            usuario.setNombre(nombreUsuarioNuevoTxt.getText().trim());
            usuario.setEmail(emailUsuarioNuevoTxt.getText().trim());
            usuario.setPassw(passUsuarioNuevoTxt.getText().trim());
            usuario.setIconoUsuario(imageToByteArray(iconoImageView.getImage()));

            // Insertar usuario en la base de datos
            Usuario.insertarUsuario(usuario);

            Alerta.showAlert("Éxito", "Registro realizado con éxito.", Alert.AlertType.CONFIRMATION);
            cerrarVentana();
            appMainController.ventanaPrincipal();
        }
    }

    private boolean camposNoVacios() {
        if (nombreUsuarioNuevoTxt.getText().trim().isEmpty()) {
            Alerta.showAlert("Error","Debe ingresar su nombre de usuario.", Alert.AlertType.WARNING);
            return false;
        }
        if (emailUsuarioNuevoTxt.getText().trim().isEmpty()) {
            Alerta.showAlert("Error","Debe ingresar su correo electrónico.", Alert.AlertType.WARNING);
            return false;
        }
        if (passUsuarioNuevoTxt.getText().trim().isEmpty()) {
            Alerta.showAlert("Error","Debe ingresar una contraseña.", Alert.AlertType.WARNING);
            return false;
        }
        if (confirmarPassTxt.getText().trim().isEmpty()) {
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

    private byte[] imageToByteArray(Image image) {
        if (image != null) {
            PixelReader pixelReader = image.getPixelReader();
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            WritablePixelFormat<ByteBuffer> format = PixelFormat.getByteBgraInstance();
            byte[] buffer = new byte[width * height * 4];
            pixelReader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);
            return buffer;
        }
        return null;
    }

    /**
     * Método para cancelar el registro y volver a la ventana de inicio de sesión.
     * @param actionEvent Evento de acción asociado al botón de cancelar.
     */
    public void cancelarRegistro(ActionEvent actionEvent){
        Stage stage = (Stage) nombreUsuarioNuevoTxt.getScene().getWindow();
        stage.close();
        main.start(stage);
    }

    /**
     * Método para cerrar la ventana.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) nombreUsuarioNuevoTxt.getScene().getWindow();
        stage.close();
    }

}