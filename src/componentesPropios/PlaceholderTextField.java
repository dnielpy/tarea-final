package componentesPropios;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {

    private String placeholder;
    private Color placeholderColor = Color.GRAY;
    private Color lineColor = Color.BLACK;
    private Color focusLineColor = new Color(0x2196F3); // Azul Material
    private boolean showError = false;

    private static final Color ERROR_COLOR = Color.RED;
    private static final String ERROR_PLACEHOLDER = "*Campo obligatorio";

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        initComponent();
    }

    public PlaceholderTextField() {
        this("Vacío");
    }

    private void initComponent() {
        setOpaque(false);
        setForeground(Color.BLACK);

        // Redibujar al escribir
        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (showError) limpiarError(); // Si escribe, limpiar error
                repaint();
            }

            public void removeUpdate(DocumentEvent e) {
                repaint();
            }

            public void changedUpdate(DocumentEvent e) {
                repaint();
            }
        });

        // Redibujar al enfocar
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2.setColor(showError ? ERROR_COLOR : placeholderColor);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            FontMetrics fm = g2.getFontMetrics();
            int y = getInsets().top + fm.getAscent();
            g2.drawString(showError ? ERROR_PLACEHOLDER : placeholder, getInsets().left, y);
            g2.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        Color color;
        if (!isEditable()) {
            color = lineColor; // puedes usar new Color(180,180,180) para un gris claro
        } else if (showError) {
            color = ERROR_COLOR;
        } else if (isFocusOwner()) {
            color = focusLineColor;
        } else {
            color = lineColor;
        }

        g2.setColor(color);
        g2.fillRect(0, getHeight() - 1, getWidth(), 2);
        g2.dispose();
    }

    // MÉTODOS PERSONALIZADOS

    public void mostrarErrorCampoObligatorio() {
        if (getText().trim().isEmpty()) {
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

    // SETTERS

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
        repaint();
    }

    public void setLineColor(Color color) {
        this.lineColor = color;
        repaint();
    }

    public void setFocusLineColor(Color color) {
        this.focusLineColor = color;
        repaint();
    }
}