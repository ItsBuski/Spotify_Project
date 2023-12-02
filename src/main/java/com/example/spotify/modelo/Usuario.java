package com.example.spotify.modelo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

public class Usuario {
    private String email;
    private String nombre;
    private String passw;
    private byte[] iconoUsuario;
    static Conexion conexion = new Conexion();

    public Usuario() {
        this.email = "";
        this.nombre = "";
        this.passw = "";
        this.iconoUsuario = null;
    }

    public Usuario(String email, String nombre, String passw, byte[] iconoUsuario) {
        this.email = email;
        this.nombre = nombre;
        this.passw = passw;
        this.iconoUsuario = iconoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public byte[] getIconoUsuario() {
        return iconoUsuario;
    }

    public void setIconoUsuario(byte[] iconoUsuario) {
        this.iconoUsuario = iconoUsuario;
    }

    // CRUD

    // Insertar Usuario
    public static void insertarUsuario(Usuario usuario) {
        String sentenciaSql = "INSERT INTO usuarios (email, nombre, passw, iconoUsuario) VALUES (?, ?, ?, ?)";
        try {
            //Inicia transacción
            conexion.connection.setAutoCommit( false);
            PreparedStatement sentencia = Conexion.connection.prepareStatement(sentenciaSql);
            sentencia.setString(1, usuario.getEmail());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getPassw());
            sentencia.setBytes(4, usuario.getIconoUsuario());
            sentencia.execute();
            sentencia.close();
            // Valida la transacción
            conexion.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar Usuario
    public static void actualizarUsuario(Usuario usuario) {
        String sentenciaSql = "UPDATE usuarios SET nombre = ?, passw = ?, iconoUsuario = ? WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = Conexion.connection.prepareStatement(sentenciaSql);
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

    // Mostrar Usuarios
    public static List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement statement = Conexion.connection.prepareStatement(sql);
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

    // Eliminar Usuario
    public static void eliminarUsuario(String email) {
        String sentenciaSql = "DELETE FROM usuarios WHERE email = ?";
        PreparedStatement sentencia = null;
        try {
            sentencia = Conexion.connection.prepareStatement(sentenciaSql);
            sentencia.setString(1, email);
            sentencia.execute();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", passw='" + passw + '\'' +
                ", iconoUsuario=" + Arrays.toString(iconoUsuario) +
                '}';
    }
}