package frontend.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import util.ConstantesFrontend;

public class ScrollPaneModerno extends JScrollPane implements ConstantesFrontend {

    private float alpha = 0f;
    private Timer fadeInTimer;
    private Timer fadeOutTimer;

    public ScrollPaneModerno(Component view) {
        super(view);
        getViewport().setBackground(Color.WHITE);
        setBorder(BORDE_COMPONENTE); // borde gris claro
        configurarScrollBars();
        configurarHoverListeners();
        iniciarAnimaciones();
    }

    private void configurarScrollBars() {
        JScrollBar vertical = getVerticalScrollBar();
        JScrollBar horizontal = getHorizontalScrollBar();

        vertical.setUI(new ScrollBarModernoUI());
        horizontal.setUI(new ScrollBarModernoUI());

        vertical.setPreferredSize(new Dimension(8, Integer.MAX_VALUE));
        horizontal.setPreferredSize(new Dimension(Integer.MAX_VALUE, 8));

        vertical.setOpaque(false);
        horizontal.setOpaque(false);

        vertical.setUnitIncrement(16);
        horizontal.setUnitIncrement(16);

        vertical.setVisible(true);
        horizontal.setVisible(true);
    }

    private void configurarHoverListeners() {
        MouseAdapter hoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                iniciarFadeIn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                SwingUtilities.convertPointFromScreen(p, ScrollPaneModerno.this);
                if (!getBounds().contains(p)) {
                    iniciarFadeOut();
                }
            }
        };

        this.addMouseListener(hoverListener);
        getViewport().getView().addMouseListener(hoverListener);
    }

    private void iniciarAnimaciones() {
        fadeInTimer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (alpha < 1f) {
                    alpha += 0.1f;
                    if (alpha > 1f) alpha = 1f;
                    repaint();
                } else {
                    fadeInTimer.stop();
                }
            }
        });

        fadeOutTimer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (alpha > 0f) {
                    alpha -= 0.1f;
                    if (alpha < 0f) alpha = 0f;
                    repaint();
                } else {
                    fadeOutTimer.stop();
                }
            }
        });
    }

    private void iniciarFadeIn() {
        fadeOutTimer.stop();
        fadeInTimer.start();
    }

    private void iniciarFadeOut() {
        fadeInTimer.stop();
        fadeOutTimer.start();
    }

    private class ScrollBarModernoUI extends BasicScrollBarUI {

        private final Color colorBarra = new Color(0, 171, 227);  // azul brillante
        private final Color colorFondo = Color.WHITE;

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color colorConAlpha = new Color(
                colorBarra.getRed(),
                colorBarra.getGreen(),
                colorBarra.getBlue(),
                (int) (alpha * 255)
            );
            g2.setColor(colorConAlpha);
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(colorFondo);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return crearBotonInvisible();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return crearBotonInvisible();
        }

        private JButton crearBotonInvisible() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            return button;
        }
    }
}
