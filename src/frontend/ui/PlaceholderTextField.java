package frontend.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.text.*;

import frontend.ConstantesFrontend;

public class PlaceholderTextField extends JTextField implements ConstantesFrontend {

	public enum InputFormat {ANY, NUMERIC, ALPHABETIC, ALPHANUMERIC}

    private String placeholder;
    private Color placeholderColor = Color.GRAY;
    private Color lineColor = Color.BLACK;
    private Color focusLineColor = COLOR_AZUL;
    private boolean showError = false;
    private InputFormat inputFormat = InputFormat.ANY;
    private int characterLimit = -1; // -1 = sin límite
    
    private static final Color ERROR_COLOR = Color.RED;
    private static final String ERROR_PLACEHOLDER = "*Campo obligatorio";

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        initComponent();
    }

    public PlaceholderTextField() {
        this("Vac\u00EDo");
    }

    private void initComponent() {
        setOpaque(false);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.PLAIN, 16));

        // Filtro para validar entrada
        ((AbstractDocument) this.getDocument()).setDocumentFilter(new InputValidationFilter());

        // Redibujar al escribir
        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (showError) limpiarError();
                repaint();
            }

            public void removeUpdate(DocumentEvent e) { repaint(); }
            public void changedUpdate(DocumentEvent e) { repaint(); }
        });

        // Redibujar al enfocar
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) { repaint(); }
            @Override
            public void focusLost(FocusEvent e) { repaint(); }
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
    public void setEditable(boolean editable) {
    	super.setEditable(editable);
    	setFocusable(editable);
    };
    
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        Color color;
        if (!isEditable()) {
            color = COLOR_GRIS;
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

    // --- MÉTODOS PERSONALIZADOS ---

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

    // --- SETTERS ---

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

    public void setInputFormat(InputFormat format) {
        this.inputFormat = format;
    }

    // --- VALIDACIÓN DE ENTRADA ---

    private class InputValidationFilter extends DocumentFilter {
    	@Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            replace(fb, offset, 0, text, attr);
        }

    	@Override
    	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
    		if (text != null) {
    			String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
    			StringBuilder sb = new StringBuilder(oldText).replace(offset, offset + length, text);
    			String newText = limpiarEspacios(sb.toString());

    			if (esValidoSegunFormato(newText) && (characterLimit < 0 || newText.length() <= characterLimit)) {
    				super.replace(fb, 0, fb.getDocument().getLength(), newText, attrs);
    			}
    		}
    	}

        private String limpiarEspacios(String s) {
            // Elimina espacios al principio y más de un espacio seguido
            return s.replaceAll("^\\s+", "").replaceAll("\\s{2,}", " ");
        }

        private boolean esValidoSegunFormato(String text) {
            String regex;
            switch (inputFormat) {
                case NUMERIC:
                    regex = "[0-9]*";
                    break;
                case ALPHABETIC:
                    regex = "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*";
                    break;
                case ALPHANUMERIC:
                    regex = "[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ ]*";
                    break;
                case ANY:
                default:
                    regex = ".*"; // Permitir cualquier carácter
            }
            return text.matches(regex);
        }
    }
    
    public void setCharacterLimit(final int limit) {
        this.characterLimit = limit;
    }

}
