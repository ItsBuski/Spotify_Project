package com.example.spotify.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Clase que gestiona la conexión y desconexión a la base de datos MySQL para la aplicación Spotify.
 */
public class Conexion {

    // casa 192.168.1.133
    private final String url = "jdbc:mysql://192.168.1.140:3306/Spotify" ;
    private final String usuario = "userspotify";
    private final String password = "Spotify1234!";

   public Connection connection;

    /**
     * Intenta establecer la conexión con la base de datos.
     *
     * @return true si la conexión se establece con éxito, false si hay un error.
     */
    public boolean tryConnect() {
        try {
            connection = DriverManager.getConnection(url, usuario, password);
            return true;
        } catch (SQLException e) {
            connection = null;
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Intenta cerrar la conexión a la base de datos.
     */
    public void tryDisconnect() {
        try {
            // Cerrar la conexión y establecerla a null.
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            // Manejar excepciones de SQL al cerrar la conexión.
            sqle.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
}