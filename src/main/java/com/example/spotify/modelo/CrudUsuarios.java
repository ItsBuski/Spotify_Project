package com.example.spotify.modelo;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CrudUsuarios{
    // CRUD
    public static boolean insertarUsuario(Usuario usuario) {
        Conexion conexion = new Conexion();
        String sentenciaSql = "INSERT INTO usuarios (email, nombre, passw, iconoUsuario) VALUES (?, ?, ?, ?)";
        try {
            if (conexion.tryConnect()) {
                //Inicia transacción
                conexion.getConnection().setAutoCommit(false);
                PreparedStatement sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
                sentencia.setString(1, usuario.getEmail());
                sentencia.setString(2, usuario.getNombre());
                sentencia.setString(3, usuario.getPassw());
                sentencia.setBytes(4, usuario.getIconoUsuario());
                sentencia.execute();
                sentencia.close();
                // Valida la transacción
                conexion.getConnection().commit();
                Alerta.showAlert("Éxito", "Registro realizado con éxito.", Alert.AlertType.CONFIRMATION);
                return true;
            } else {
                Alerta.showAlert("Error","La conexión es nula. Asegúrate de inicializarla correctamente.", Alert.AlertType.WARNING);
                return false;
            }
        } catch (SQLException e) {
            Alerta.showAlert("Error","El usuario con el correo que ingresaste ya exista en la base de datos.", Alert.AlertType.WARNING);
            return false;
        }
    }
    public static boolean loginUsuario(String correo, String pass) {
        Conexion conexion = new Conexion();
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            if (conexion.tryConnect()) {
                String sentenciaSql = "SELECT email FROM usuarios where email = ?";
                sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
                sentencia.setString(1, correo);
                resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    sentenciaSql = "SELECT email, passw FROM usuarios where email = ? and passw = ?";
                    sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
                    sentencia.setString(1, correo);
                    sentencia.setString(2, pass);
                    resultado = sentencia.executeQuery();

                    if (resultado.next()) {
                        Alerta.showAlert("Éxito", "Inicio de sesión exitoso.", Alert.AlertType.CONFIRMATION);
                        return true;
                    } else {
                        Alerta.showAlert("Error", "Contraseña incorrecta.", Alert.AlertType.WARNING);
                        return false;
                    }
                } else {
                    Alerta.showAlert("Error", "El usuario no existe.", Alert.AlertType.WARNING);
                    return false;
                }
            } else {
                Alerta.showAlert("Error", "No se pudo conectar a la base de datos. Verifica la configuración.", Alert.AlertType.WARNING);
                return false;
            }
        } catch (SQLException e) {
            Alerta.showAlert("Error", "Hubo un error desconocido.", Alert.AlertType.WARNING);
            e.printStackTrace();
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
        return false;
    }
    public static void eliminarUsuario() {
        Conexion conexion = new Conexion();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("usuarioActual.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String sentenciaSql = "DELETE FROM usuarios WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1, properties.getProperty("usuario"));
            sentencia.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    public static Usuario obtenerUsuarioParaApp(){
        Conexion conexion = new Conexion();
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            if (conexion.tryConnect()) {
                Properties properties = new Properties();
                properties.load(new FileReader("src/main/resources/usuarioActual.properties"));
                String sentenciaSql = "SELECT nombre, iconoUsuario FROM usuarios where email = ?";
                sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
                sentencia.setString(1, properties.getProperty("usuario"));
                resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    byte[] iconoUsuario = resultado.getBytes("iconoUsuario");
                    return new Usuario(nombre, iconoUsuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static Image recuperarImagenBytes(byte[] imageData) {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
        Image image = new Image(bis);
        return image;
    }
    public static void actualizarUsuarioNombre(String nombre) {
        Conexion conexion = new Conexion();
        String sentenciaSql = "UPDATE usuarios SET nombre = ? WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/usuarioActual.properties"));
            sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1, nombre);
            sentencia.setString(2, properties.getProperty("usuario"));
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
    public static void actualizarUsuarioEmail(String email) {
        Conexion conexion = new Conexion();
        String sentenciaSql = "UPDATE usuarios SET email = ? WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/usuarioActual.properties"));
            sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1, email);
            sentencia.setString(2, properties.getProperty("usuario"));
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
    public static void actualizarUsuarioPassword(String passw) {
        Conexion conexion = new Conexion();
        String sentenciaSql = "UPDATE usuarios SET passw = ? WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/usuarioActual.properties"));
            sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1, passw);
            sentencia.setString(2, properties.getProperty("usuario"));
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
    public static void actualizarUsuarioIcono(byte[] imgData) {
        Conexion conexion = new Conexion();
        String sentenciaSql = "UPDATE usuarios SET iconoUsuario = ? WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/usuarioActual.properties"));
            if (conexion.tryConnect()) {
                sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
                sentencia.setBytes(1, imgData);
                sentencia.setString(2, properties.getProperty("usuario"));
                sentencia.executeUpdate();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}