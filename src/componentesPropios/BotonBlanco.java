package componentesPropios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import frontend.ConstantesFrontend;

import java.awt.event.*;

public class BotonBlanco extends JButton implements MouseListener, FocusListener, ConstantesFrontend {

    private Color colorNormal = COLOR_GRIS_CLARO;
    private Color colorHover = COLOR_GRIS_CURSOR;
    private Color colorTextoNormal = Color.BLACK;
    private Color colorTextoFocus = COLOR_AZUL;

    private boolean mousePresionado = false;

    public BotonBlanco(String texto) {
        super(texto);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(colorTextoNormal);
        setBackground(colorNormal);
        setFont(new Font("Arial", Font.PLAIN, 18));
        setFocusPainted(false);
        setFocusable(true);

        addMouseListener(this);
        addFocusListener(this);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setFocusable(enabled);
        if (!enabled) {
            setBackground(colorNormal);
            setForeground(colorTextoNormal);
        }
    }

    // MouseListener

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isEnabled() && !mousePresionado) {
            setBackground(colorHover);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (isEnabled() && !mousePresionado) {
            setBackground(colorNormal);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isEnabled()) {
            mousePresionado = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePresionado = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // No implementado
    }

    // FocusListener

    @Override
    public void focusGained(FocusEvent e) {
        if (isEnabled()) {
            setForeground(colorTextoFocus);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (isEnabled()) {
            setForeground(colorTextoNormal);
        }
    }
}
