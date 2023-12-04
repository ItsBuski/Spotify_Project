package com.example.spotify.modelo;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.FileInputStream;
import java.io.IOException;

public class MP3Player {
    private AdvancedPlayer player;

    public MP3Player(String mp3FilePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(mp3FilePath);
            player = new AdvancedPlayer(fileInputStream);
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (player != null) {
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    // Se llama cuando la reproducción ha terminado
                    System.out.println("Reproducción finalizada");
                }
            });

            new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
        }
    }

    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player("src/main/resources/MP3/cancionActual.mp3");
        mp3Player.play();
    }
}
