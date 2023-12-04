package com.example.spotify.modelo;

public class Cancion {

    String nombre;
    String duracion;
    String artista;
    String album;
    byte[] mp3;

    public Cancion(String nombre, String duracion, String artista, String album, byte[] mp3) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.artista = artista;
        this.album = album;
        this.mp3 = mp3;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public byte[] getMp3() {
        return mp3;
    }

    public void setMp3(byte[] mp3) {
        this.mp3 = mp3;
    }
}
