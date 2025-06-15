package componentesPropios;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ImageButtonLabel extends JLabel {

    private float alpha = 1.0f;

    public ImageButtonLabel(ImageIcon icon) {
        super(icon);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Interacciones
        if (!isEnabled()) {
        	addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    alpha = 0.6f;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    alpha = 1.0f;
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    alpha = 0.4f;
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    alpha = 0.6f;
                    repaint();
                }
            });
        }   

        // Focus (para teclas o tabulación)
        setFocusable(true);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                alpha = 0.6f;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                alpha = 1.0f;
                repaint();
            }
        });
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paintComponent(g2);
        g2.dispose();
    }
}

