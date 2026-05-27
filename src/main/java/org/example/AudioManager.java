package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class AudioManager {
    private Clip clip;

    public void playLoop(String resourcePath) {
        stop();

        URL url = getClass().getResource(resourcePath);
        if (url == null) {
            GameView.mostrarLinea("[Audio] Recurso no encontrado: " + resourcePath);
            return;
        }

        try (AudioInputStream stream = AudioSystem.getAudioInputStream(url)) {
            Clip newClip = AudioSystem.getClip();
            newClip.open(stream);
            newClip.loop(Clip.LOOP_CONTINUOUSLY);
            newClip.start();
            clip = newClip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            GameView.mostrarLinea("[Audio] No se pudo reproducir: " + ex.getMessage());
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }
}

