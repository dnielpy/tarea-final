package frontend.ui.placeholders;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import util.ConstantesFrontend;
import frontend.ui.botones.ImageButtonLabel;

public class PlaceholderAndToggle extends JPanel implements ConstantesFrontend {

    private final JPasswordField passwordField;
    private final ImageButtonLabel toggleEye;

    private String placeholder;
    private boolean isPasswordVisible;
    private boolean showError;

    private Color placeholderColor = Color.GRAY;
    private Color lineColor = Color.BLACK;
    private Color focusLineColor = COLOR_AZUL;
    private Color errorColor = Color.RED;
    private int characterLimit = -1;

    private static final String ERROR_PLACEHOLDER = "*Campo obligatorio";

    public PlaceholderAndToggle(String placeholder) {
        this.placeholder = placeholder;
        this.isPasswordVisible = false;
        this.showError = false;

        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(300, 35));

        // Campo de contraseña
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setForeground(Color.BLACK);
        passwordField.setEchoChar('•');
        passwordField.setOpaque(false);
        passwordField.setBorder(null);

        Document doc = passwordField.getDocument();
        if (doc instanceof AbstractDocument) {
            ((AbstractDocument) doc).setDocumentFilter(new InputValidationFilter());
        }

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) { repaint(); }
            public void focusLost(FocusEvent e) { repaint(); }
        });

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (showError) limpiarError();
                repaint();
            }
            public void removeUpdate(DocumentEvent e) { repaint(); }
            public void changedUpdate(DocumentEvent e) { repaint(); }
        });

        add(passwordField);

        // Botón del ojo (posición dinámica)
        toggleEye = new ImageButtonLabel(new ImageIcon(getClass().getResource("/fotos/no visible (1).png")));
        toggleEye.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                alternarVisibilidad();
            }
        });
        add(toggleEye);

        // Recolocar componentes cuando cambie el tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repositionComponents();
            }
        });

        // Primera colocación
        repositionComponents();
    }

    private void alternarVisibilidad() {
        if (isPasswordVisible) {
            passwordField.setEchoChar('•');
            toggleEye.setIcon(new ImageIcon(getClass().getResource("/fotos/no visible (1).png")));
            isPasswordVisible = false;
        } else {
            passwordField.setEchoChar((char) 0);
            toggleEye.setIcon(new ImageIcon(getClass().getResource("/fotos/visible (1).png")));
            isPasswordVisible = true;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Línea inferior
        Color borderColor;
        if (!passwordField.isEditable()) {
            borderColor = COLOR_GRIS;
        } else if (showError) {
            borderColor = errorColor;
        } else if (passwordField.isFocusOwner()) {
            borderColor = focusLineColor;
        } else {
            borderColor = lineColor;
        }
        g2.setColor(borderColor);
        g2.fillRect(0, getHeight() - 1, getWidth(), 1);

        // Placeholder
        if (passwordField.getPassword().length == 0 && !passwordField.isFocusOwner()) {
            g2.setColor(showError ? errorColor : placeholderColor);
            g2.setFont(passwordField.getFont().deriveFont(Font.ITALIC));
            FontMetrics fm = g2.getFontMetrics();
            int y = passwordField.getY() + fm.getAscent() + (getHeight() - fm.getHeight()) / 2 - 1;
            String texto = showError ? ERROR_PLACEHOLDER : placeholder;
            g2.drawString(texto, passwordField.getX() + 5, y);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color color;
        if (!passwordField.isEditable()) {
            color = COLOR_GRIS;
        } else if (showError) {
            color = errorColor;
        } else if (passwordField.isFocusOwner()) {
            color = focusLineColor;
        } else {
            color = lineColor;
        }

        g2.setColor(color);
        g2.fillRect(0, getHeight() - 1, getWidth(), 2);
        g2.dispose();
    }

    public void mostrarErrorCampoObligatorio() {
        if (passwordField.getPassword().length == 0) {
            showError = true;
            repaint();
        }
    }

    public void limpiarError() {
        if (showError) {
            showError = false;
            repaint();
        }
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public void setEditable(boolean editable) {
        passwordField.setEditable(editable);
    }

    public void setCharacterLimit(int limit) {
        this.characterLimit = limit;
    }

    public char[] getPassword() {
        return passwordField.getPassword();
    }

    public void addActionListener(ActionListener listener) {
        passwordField.addActionListener(listener);
    }

    public void setLineColor(Color color) {
        this.lineColor = color;
        repaint();
    }

    public void setFocusLineColor(Color color) {
        this.focusLineColor = color;
        repaint();
    }

    public void requestFieldFocus() {
        passwordField.requestFocusInWindow();
    }

    private class InputValidationFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            replace(fb, offset, 0, text, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
            if (text != null) {
                Document doc = fb.getDocument();
                String actual = doc.getText(0, doc.getLength());
                StringBuilder sb = new StringBuilder(actual);
                sb.replace(offset, offset + length, text);
                String nuevoTexto = sb.toString();

                boolean longitudValida = characterLimit < 0 || nuevoTexto.length() <= characterLimit;

                if (longitudValida) {
                    super.replace(fb, offset, length, text, attr);
                }
            }
        }
    }
    
    private void repositionComponents() {
        int width = getWidth();
        int height = getHeight();

        int buttonSize = 30;
        int spacing = 5;

        toggleEye.setBounds(width - buttonSize, 0, buttonSize, height);
        passwordField.setBounds(0, 0, width - buttonSize - spacing, height);
    }
}
