package com.example.spotify.controlador;

import com.example.spotify.modelo.CrudUsuarios;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierController implements Initializable {
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
                new String("src/main/resources/imagenes/iconoUsuario1.png"),
                new String("src/main/resources/imagenes/iconoUsuario2.png"),
                new String("src/main/resources/imagenes/iconoUsuario3.png"),
                new String("src/main/resources/imagenes/iconoUsuario4.png")
        };

        // Mostrar la primera imagen al inicio
        iconoImageView.setImage(iconos[indiceIconoActual]);
    }

    public void confirmarIcono()  {
        CrudUsuarios.actualizarUsuarioIcono(obtenerIcono());
    }

    public byte[] obtenerIcono() {
        String file = "";
        for (int i = 0; i < iconos.length; i++) {
            if (iconos[i] == iconoImageView.getImage()) {
                file = rutaIconos[i];
            }
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        byte[] imageData = bos.toByteArray();
        return imageData;
    }

}
