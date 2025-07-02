package util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UtilSonido {

    private static final Map<String, Clip> sonidosCargados = new HashMap<>();

    public static void reproducir(String rutaRelativa) {
        Clip clip = obtenerClip(rutaRelativa);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    private static Clip obtenerClip(String rutaRelativa) {
        Clip clip = sonidosCargados.get(rutaRelativa);

        if (clip == null) {
            clip = cargarClipDesdeClasspath(rutaRelativa);
            if (clip != null) {
                sonidosCargados.put(rutaRelativa, clip);
            }
        }

        return clip;
    }

    private static Clip cargarClipDesdeClasspath(String rutaRelativa) {
        Clip clip = null;
        try {
            URL url = UtilSonido.class.getClassLoader().getResource(rutaRelativa);
            if (url != null) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(ais);
            } else {
                System.err.println("Sonido no encontrado en el classpath: " + rutaRelativa);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error cargando sonido '" + rutaRelativa + "': " + e.getMessage());
        }
        return clip;
    }

    public static void liberarRecursos() {
        for (Clip clip : sonidosCargados.values()) {
            clip.close();
        }
        sonidosCargados.clear();
    }
}

