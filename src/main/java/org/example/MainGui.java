package org.example;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada para iniciar el juego con interfaz grafica.
 */
public class MainGui {
    /**
     * Inicializa la GUI y arranca el juego en un hilo separado.
     *
     * @param args argumentos de linea de comandos (no se usan).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                GuiConsole console = new GuiConsole("Pokemon - GUI");
                GameView.setBattleStatusView(console);
                console.attachToSystemIO();
                console.show();

                Thread gameThread = new Thread(() -> Main.main(new String[0]), "game-thread");
                gameThread.setDaemon(true);
                gameThread.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
