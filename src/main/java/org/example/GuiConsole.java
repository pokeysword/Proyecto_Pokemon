package org.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GuiConsole {
    private final JFrame frame;
    private final JTextArea output;
    private final JTextField input;
    private final QueueInputStream inputReader;
    private final BlockingQueue<Integer> inputQueue;

    public GuiConsole(String title) {
        frame = new JFrame(title);
        output = new JTextArea();
        input = new JTextField();
        inputQueue = new LinkedBlockingQueue<>();
        inputReader = new QueueInputStream(inputQueue);

        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);

        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(event -> sendInput());
        input.addActionListener(event -> sendInput());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8, 8));
        frame.add(new JScrollPane(output), BorderLayout.CENTER);
        frame.add(input, BorderLayout.SOUTH);
        frame.add(sendButton, BorderLayout.EAST);
        frame.setPreferredSize(new Dimension(720, 420));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void attachToSystemIO() {
        System.setIn(inputReader);
        System.setOut(new PrintStream(new TextAreaOutputStream(output), true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(new TextAreaOutputStream(output), true, StandardCharsets.UTF_8));
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
            input.requestFocusInWindow();
        });
    }

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

    private void appendOutput(String text) {
        SwingUtilities.invokeLater(() -> {
            output.append(text);
            output.setCaretPosition(output.getDocument().getLength());
        });
    }

    private static class TextAreaOutputStream extends OutputStream {
        private final JTextArea target;

        private TextAreaOutputStream(JTextArea target) {
            this.target = target;
        }

        @Override
        public void write(int b) {
            write(new byte[] { (byte) b }, 0, 1);
        }

        @Override
        public void write(byte[] b, int off, int len) {
            String text = new String(b, off, len, StandardCharsets.UTF_8);
            SwingUtilities.invokeLater(() -> {
                target.append(text);
                target.setCaretPosition(target.getDocument().getLength());
            });
        }
    }

    private static class QueueInputStream extends InputStream {
        private final BlockingQueue<Integer> queue;

        private QueueInputStream(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public int read() {
            try {
                return queue.take();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return -1;
            }
        }

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

        @Override
        public void close() {
            // No-op para evitar que Scanner cierre System.in.
        }
    }
}
