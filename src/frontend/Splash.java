package frontend;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import util.ConstantesFrontend;
import util.UtilSonido;

import java.awt.Font;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class Splash extends JDialog implements ConstantesFrontend {

    private final JPanel contentPanel = new JPanel();
    private float opacity = 0f;
    private Timer fadeInTimer;
    private Timer displayTimer;
    private Runnable despuesDelSplash;

    public Splash(final Runnable despuesDelSplash) {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setBounds(100, 100, 755, 555);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
         
        setOpacity(opacity);
        this.despuesDelSplash = despuesDelSplash;

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel imagenLogo = new JLabel("");
        imagenLogo.setHorizontalAlignment(SwingConstants.CENTER);
        imagenLogo.setIcon(new ImageIcon(Splash.class.getResource("/fotos/hospital-logo-and-symbols-templa.png")));
        imagenLogo.setBounds(0, 120, 755, 178);
        contentPanel.add(imagenLogo);

        JLabel imagenCMF = new JLabel("");
        imagenCMF.setIcon(new ImageIcon(Splash.class.getResource("/fotos/Tipografia-Peque.png")));
        imagenCMF.setHorizontalAlignment(SwingConstants.CENTER);
        imagenCMF.setBounds(0, 295, 755, 57);
        contentPanel.add(imagenCMF);

        JLabel cartelCita = new JLabel("\"La verdadera medicina no es la que cura, sino la que precave\"");
        cartelCita.setForeground(COLOR_VERDE);
        cartelCita.setFont(new Font("MV Boli", Font.PLAIN, 20));
        cartelCita.setHorizontalAlignment(SwingConstants.CENTER);
        cartelCita.setBounds(0, 435, 755, 57);
        contentPanel.add(cartelCita);

        JLabel cartelAutor = new JLabel("\u2014 Jos\u00E9 Mart\u00ED");
        cartelAutor.setHorizontalAlignment(SwingConstants.CENTER);
        cartelAutor.setForeground(COLOR_AZUL);
        cartelAutor.setFont(new Font("MV Boli", Font.BOLD, 20));
        cartelAutor.setBounds(0, 474, 755, 57);
        contentPanel.add(cartelAutor);

        iniciarFadeIn();
        UtilSonido.reproducir("sonidos/splash.wav");
    }

    private void iniciarFadeIn() {
        fadeInTimer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.05f;
                if (opacity >= 1f) {
                    opacity = 1f;
                    setOpacity(opacity);
                    fadeInTimer.stop();
                    iniciarTiempoDeEspera();
                } else {
                    setOpacity(opacity);
                }
            }
        });
        fadeInTimer.start();
    }

    private void iniciarTiempoDeEspera() {
        displayTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTimer.stop();
                if (despuesDelSplash != null) {
                    despuesDelSplash.run();
                }
                dispose();
            }
        });
        displayTimer.setRepeats(false);
        displayTimer.start();
    }
}
