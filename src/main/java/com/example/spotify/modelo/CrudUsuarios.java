package com.example.spotify.modelo;

import com.example.spotify.Main;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUsuarios{

    // CRUD

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario El objeto Usuario a insertar.
     * @return true si la inserción es exitosa, false si hay un error.
     */

    public static boolean insertarUsuario(Usuario usuario, Main main) {
        String sentenciaSql = "INSERT INTO usuarios (email, nombre, passw, iconoUsuario) VALUES (?, ?, ?, ?)";
        try {
            if (main.getConexion().tryConnect()){
                //Inicia transacción
                main.getConexion().getConnection().setAutoCommit(false);
                PreparedStatement sentencia = main.getConexion().getConnection().prepareStatement(sentenciaSql);
                sentencia.setString(1, usuario.getEmail());
                sentencia.setString(2, usuario.getNombre());
                sentencia.setString(3, usuario.getPassw());
                sentencia.setBlob(4, usuario.getIconoUsuario());
                sentencia.execute();
                sentencia.close();
                // Valida la transacción
                main.getConexion().getConnection().commit();
                Alerta.showAlert("Éxito", "Registro realizado con éxito.", Alert.AlertType.CONFIRMATION);
                return true;
            } else {
                Alerta.showAlert("Error","La conexión es nula. Asegúrate de inicializarla correctamente.", Alert.AlertType.WARNING);
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean loginUsuario(String correo, String pass, Main main) {
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            if (main.getConexion().tryConnect()) {
                String sentenciaSql = "SELECT email FROM usuarios where email = ?";
                sentencia = main.getConexion().getConnection().prepareStatement(sentenciaSql);
                sentencia.setString(1, correo);
                resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    sentenciaSql = "SELECT email, passw FROM usuarios where email = ? and passw = ?";
                    sentencia = main.getConexion().getConnection().prepareStatement(sentenciaSql);
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


    /**
     * Actualiza la información de un usuario en la base de datos.
     *
     * @param usuario El objeto Usuario con la información actualizada.
     */
    /*
    public void actualizarUsuario(Usuario usuario) {
        String sentenciaSql = "UPDATE usuarios SET nombre = ?, passw = ?, iconoUsuario = ? WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getPassw());
            sentencia.setBytes(3, usuario.getIconoUsuario());
            sentencia.setString(4, usuario.getEmail());
            sentencia.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
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
*/
    // Mostrar Usuarios
    /**
     * Obtiene una lista de todos los usuarios almacenados en la base de datos.
     *
     * @return Lista de objetos Usuario.
     */
    /*
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement statement = conexion.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String nombre = resultSet.getString("nombre");
                String password = resultSet.getString("passw");
                byte[] iconoUsuario = resultSet.getBytes("iconoUsuario");
                usuarios.add(new Usuario(email, nombre, password, iconoUsuario));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /*
    public void eliminarUsuario(String email) {
        String sentenciaSql = "DELETE FROM usuarios WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = conexion.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1, email);
            sentencia.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    */

    public static Usuario obtenerUsuario(Main main){
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            String sentenciaSql = "SELECT nombre, iconoUsuario FROM usuarios where email = ?";
            sentencia = main.getConexion().getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1, main.getUsuario());
            resultado = sentencia.executeQuery();

            if(resultado.next()) {
                String nombre = resultado.getString("nombre");
                Blob iconoUsuario = resultado.getBlob("iconoUsuario");
                Usuario usuario = new Usuario(nombre, iconoUsuario);
                return usuario;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}