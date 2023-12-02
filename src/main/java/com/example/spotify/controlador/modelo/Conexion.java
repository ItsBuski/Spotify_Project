package com.example.spotify.controlador.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    static String url = "jdbc:mysql://10.11.67.89:3306/Spotify" ;
    static String usuario = "userspotify";
    static String password= "Spotify1234!";
    static Connection connection;

    //jdbc:mysql://
    public boolean tryConnect() {
        try {
            connection = DriverManager.getConnection(url, usuario, password);
            return true;
        } catch (SQLException e) {
            connection = null;
            return false;
        }
    }

    public void tryDisconnect() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}