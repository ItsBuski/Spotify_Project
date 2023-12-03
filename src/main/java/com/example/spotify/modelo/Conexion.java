package com.example.spotify.modelo;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexion {

    static String url = "jdbc:mysql://192.168.1.133:3306/Spotify" ;
    static String usuario = "userspotify";
    static String password = "Spotify1234!";
    public static Connection connection;

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

    public void tryDisconnect() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}