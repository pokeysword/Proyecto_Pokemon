package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class AudioManager {
    private Clip clip;
    private float volumeLevel = 0.4f;

    public void setVolume(float level) {
        float clamped = Math.max(0.0f, Math.min(1.0f, level));
        volumeLevel = clamped;
        applyVolume();
    }

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
            applyVolume();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            GameView.mostrarLinea("[Audio] No se pudo reproducir: " + ex.getMessage());
        }
    }

    private void applyVolume() {
        if (clip == null) {
            return;
        }
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = control.getMinimum();
            float max = control.getMaximum();
            float gain = min + (max - min) * volumeLevel;
            control.setValue(gain);
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
