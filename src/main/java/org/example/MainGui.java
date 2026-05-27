package org.example;

import javax.swing.SwingUtilities;

public class MainGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                GuiConsole console = new GuiConsole("Pokemon - GUI");
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

