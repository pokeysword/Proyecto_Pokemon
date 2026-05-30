package org.example;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Consola grafica que redirige System.in/out y muestra el estado de batalla.
 */
public class GuiConsole implements BattleStatusView {
    private final JFrame frame;
    private final JTextArea output;
    private final JTextField input;
    private final QueueInputStream inputReader;
    private final BlockingQueue<Integer> inputQueue;
    private final JLabel jugadorLabel;
    private final JLabel rivalLabel;
    private final JProgressBar jugadorBar;
    private final JProgressBar rivalBar;

    /**
     * Crea la ventana y los componentes de la consola.
     *
     * @param title titulo de la ventana.
     */
    public GuiConsole(String title) {
        frame = new JFrame(title);
        output = new JTextArea();
        input = new JTextField();
        inputQueue = new LinkedBlockingQueue<>();
        inputReader = new QueueInputStream(inputQueue);

        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setOpaque(true);
        Color panelSolid = new Color(15, 18, 24);
        output.setBackground(panelSolid);
        output.setForeground(new Color(245, 245, 245));
        output.setFont(new Font("Consolas", Font.PLAIN, 13));

        input.setFont(new Font("Consolas", Font.PLAIN, 14));
        input.setBackground(new Color(255, 255, 255));
        input.setForeground(new Color(30, 30, 30));
        input.setCaretColor(new Color(30, 30, 30));
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 120, 180), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));

        jugadorLabel = new JLabel("Jugador");
        rivalLabel = new JLabel("Rival");
        jugadorBar = crearBarraVida();
        rivalBar = crearBarraVida();

        JPanel statusPanel = new JPanel(new GridLayout(2, 1, 6, 6));
        statusPanel.setOpaque(false);
        statusPanel.add(crearFilaVida(jugadorLabel, jugadorBar));
        statusPanel.add(crearFilaVida(rivalLabel, rivalBar));

        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(event -> sendInput());
        input.addActionListener(event -> sendInput());

        JPanel inputPanel = new JPanel(new BorderLayout(6, 6));
        inputPanel.setOpaque(true);
        inputPanel.setBackground(panelSolid);
        inputPanel.add(input, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        JScrollPane outputScroll = new JScrollPane(output);
        outputScroll.setOpaque(true);
        outputScroll.getViewport().setOpaque(true);
        outputScroll.getViewport().setBackground(panelSolid);

        JPanel content = new BackgroundPanel(loadBackgroundImage());
        content.setLayout(new BorderLayout(8, 8));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(content);
        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(outputScroll, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(720, 480));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Actualiza etiquetas y barras de vida en la interfaz.
     *
     * @param nombre1 nombre del Pokemon 1.
     * @param ps1 PS actuales del Pokemon 1.
     * @param max1 PS maximos del Pokemon 1.
     * @param nombre2 nombre del Pokemon 2.
     * @param ps2 PS actuales del Pokemon 2.
     * @param max2 PS maximos del Pokemon 2.
     */
    @Override
    public void updateBattleStatus(String nombre1, int ps1, int max1, String nombre2, int ps2, int max2) {
        SwingUtilities.invokeLater(() -> {
            actualizarBarra(jugadorLabel, jugadorBar, nombre1, ps1, max1);
            actualizarBarra(rivalLabel, rivalBar, nombre2, ps2, max2);
        });
    }

    /**
     * Crea una fila con etiqueta y barra de vida.
     *
     * @param label etiqueta de nombre.
     * @param bar barra de vida.
     * @return panel con la fila.
     */
    private JPanel crearFilaVida(JLabel label, JProgressBar bar) {
        JPanel row = new JPanel(new BorderLayout(6, 6));
        row.add(label, BorderLayout.WEST);
        row.add(bar, BorderLayout.CENTER);
        return row;
    }

    /**
     * Crea una barra de vida por defecto.
     *
     * @return barra inicializada.
     */
    private JProgressBar crearBarraVida() {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setValue(100);
        bar.setStringPainted(true);
        bar.setForeground(new Color(40, 180, 90));
        bar.setOpaque(false);
        return bar;
    }

    /**
     * Actualiza el texto y color de la barra.
     *
     * @param label etiqueta de nombre.
     * @param bar barra de vida.
     * @param nombre nombre del Pokemon.
     * @param actual PS actuales.
     * @param maximo PS maximos.
     */
    private void actualizarBarra(JLabel label, JProgressBar bar, String nombre, int actual, int maximo) {
        int maxSeguro = Math.max(1, maximo);
        int actualSeguro = Math.max(0, Math.min(actual, maxSeguro));
        int porcentaje = (int) Math.round((actualSeguro * 100.0) / maxSeguro);

        label.setText(nombre + " (" + actualSeguro + "/" + maxSeguro + ")");
        bar.setValue(porcentaje);
        bar.setString(porcentaje + "%");

        if (porcentaje < 10) {
            bar.setForeground(new Color(200, 60, 60));
        } else if (porcentaje < 50) {
            bar.setForeground(new Color(200, 170, 0));
        } else {
            bar.setForeground(new Color(40, 180, 90));
        }
    }

    /**
     * Redirige System.in/out/err hacia la consola grafica.
     */
    public void attachToSystemIO() {
        System.setIn(inputReader);
        System.setOut(new PrintStream(new TextAreaOutputStream(output), true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(new TextAreaOutputStream(output), true, StandardCharsets.UTF_8));
    }

    /**
     * Muestra la ventana y enfoca el campo de entrada.
     */
    public void show() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            input.requestFocusInWindow();
        });
    }

    /**
     * Envia el texto del input como bytes a la cola de entrada.
     */
    private void sendInput() {
        String text = input.getText();
        if (text == null || text.trim().isEmpty()) {
            return;
        }
        input.setText("");
        appendOutput("> " + text + System.lineSeparator());
        byte[] bytes = (text + System.lineSeparator()).getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            inputQueue.offer((int) b);
        }
    }

    /**
     * Anade texto al area de salida.
     *
     * @param text contenido a agregar.
     */
    private void appendOutput(String text) {
        SwingUtilities.invokeLater(() -> {
            output.append(text);
            output.setCaretPosition(output.getDocument().getLength());
        });
    }

    private Image loadBackgroundImage() {
        URL resource = getClass().getResource("/fondo.png");
        if (resource == null) {
            return null;
        }
        return new ImageIcon(resource).getImage();
    }

    /**
     * Panel que dibuja una imagen de fondo escalada.
     */
    private static class BackgroundPanel extends JPanel {
        private final Image background;
        private Image scaledBackground;
        private int lastW;
        private int lastH;

        private BackgroundPanel(Image background) {
            this.background = background;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background == null) {
                return;
            }
            Graphics2D g2 = (Graphics2D) g.create();
            int panelW = getWidth();
            int panelH = getHeight();
            if (panelW > 0 && panelH > 0 && (scaledBackground == null || panelW != lastW || panelH != lastH)) {
                lastW = panelW;
                lastH = panelH;
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                scaledBackground = background.getScaledInstance(panelW, panelH, Image.SCALE_SMOOTH);
            }
            if (scaledBackground != null) {
                g2.drawImage(scaledBackground, 0, 0, this);
            }
            g2.dispose();
        }
    }

    /**
     * OutputStream que escribe en un JTextArea.
     */
    private static class TextAreaOutputStream extends OutputStream {
        private final JTextArea target;

        /**
         * Crea el stream asociado a un area de texto.
         *
         * @param target area objetivo.
         */
        private TextAreaOutputStream(JTextArea target) {
            this.target = target;
        }

        /**
         * Escribe un byte en el area de texto.
         *
         * @param b byte a escribir.
         */
        @Override
        public void write(int b) {
            write(new byte[] { (byte) b }, 0, 1);
        }

        /**
         * Escribe un bloque de bytes en el area de texto.
         *
         * @param b buffer de datos.
         * @param off offset inicial.
         * @param len longitud.
         */
        @Override
        public void write(byte[] b, int off, int len) {
            String text = new String(b, off, len, StandardCharsets.UTF_8);
            SwingUtilities.invokeLater(() -> {
                target.append(text);
                target.setCaretPosition(target.getDocument().getLength());
            });
        }
    }

    /**
     * InputStream que consume bytes desde una cola.
     */
    private static class QueueInputStream extends InputStream {
        private final BlockingQueue<Integer> queue;

        /**
         * Crea el stream asociado a una cola.
         *
         * @param queue cola de entrada.
         */
        private QueueInputStream(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        /**
         * Lee un byte bloqueante.
         *
         * @return byte leido o -1 si se interrumpe.
         */
        @Override
        public int read() {
            try {
                return queue.take();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return -1;
            }
        }

        /**
         * Lee hasta len bytes desde la cola.
         *
         * @param b buffer destino.
         * @param off offset inicial.
         * @param len longitud maxima.
         * @return cantidad leida o -1 si no hay datos.
         */
        @Override
        public int read(byte[] b, int off, int len) {
            if (b == null) {
                throw new NullPointerException("buffer");
            }
            if (off < 0 || len < 0 || off + len > b.length) {
                throw new IndexOutOfBoundsException("off/len invalid");
            }
            if (len == 0) {
                return 0;
            }
            int first = read();
            if (first == -1) {
                return -1;
            }
            b[off] = (byte) first;
            int count = 1;
            while (count < len && !queue.isEmpty()) {
                Integer next = queue.poll();
                if (next == null) {
                    break;
                }
                b[off + count] = next.byteValue();
                count++;
            }
            return count;
        }

        /**
         * No cierra System.in para evitar afectar al Scanner.
         */
        @Override
        public void close() {
            // No-op para evitar que Scanner cierre System.in.
        }
    }
}
