package com.example.spotify.modelo;

import javafx.scene.image.Image;

import java.io.FileInputStream;

/**
 * Clase que representa a un Usuario en la aplicación Spotify.
 */
public class Usuario {
    private String email;
    private String nombre;
    private String passw;
    private byte[] iconoUsuario;

    /**
     * Constructor sin argumentos para la clase Usuario.
     * Inicializa los campos con valores predeterminados.
     */
    public Usuario() {
        this.email = "";
        this.nombre = "";
        this.passw = "";
        this.iconoUsuario = null;
    }

    /**
     * Constructor con argumentos para la clase Usuario.
     * Permite inicializar todos los campos de un usuario.
     *
     * @param email        El correo electrónico del usuario.
     * @param nombre       El nombre del usuario.
     * @param passw        La contraseña del usuario.
     * @param iconoUsuario El icono asociado al usuario en forma de arreglo de bytes.
     */
    public Usuario(String email, String nombre, String passw, byte[] iconoUsuario) {
        this.email = email;
        this.nombre = nombre;
        this.passw = passw;
        this.iconoUsuario = iconoUsuario;
    }

    public Usuario(String nombre, byte[] iconoUsuario) {
        this.nombre = nombre;
        this.iconoUsuario = iconoUsuario;
    }

    // Métodos getter y setter para los campos de la clase Usuario

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

    /**
     * Devuelve una representación en cadena del objeto Usuario.
     *
     * @return Cadena que representa el objeto Usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }
}