package frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class VentanaReportes extends JPanel {

    private JPanel panelContenedorVistas;
    private CardLayout cardLayout;

    private final Color COLOR_NORMAL = new Color(0, 171, 227);
    private final Color COLOR_PRESIONADO = Color.WHITE;

    private List<JButton> botonesNavbar = new ArrayList<>();

    public VentanaReportes() {
        setLayout(new BorderLayout());

        // Primero inicializa el contenedor de vistas y el layout
        cardLayout = new CardLayout();
        panelContenedorVistas = new JPanel(cardLayout);

        // Agregar las distintas vistas
        panelContenedorVistas.add(crearVistaPacientes(), "PACIENTES");
        panelContenedorVistas.add(crearVistaEmbarazadas(), "EMBARAZADAS");
        panelContenedorVistas.add(crearVistaRiesgo(), "RIESGO");

        // Mostrar una vista por defecto
        cardLayout.show(panelContenedorVistas, "PACIENTES");

        // Luego crear y añadir la barra de navegación
        JPanel navbar = crearNavbar();
        add(navbar, BorderLayout.NORTH);

        // Añadir el contenedor al panel principal
        add(panelContenedorVistas, BorderLayout.CENTER);

        // Marcar botón por defecto como activo
        if (!botonesNavbar.isEmpty()) {
            setBotonActivo(botonesNavbar.get(0));
        }
    }

    private JPanel crearNavbar() {
        JPanel navbar = new JPanel();
        navbar.setLayout(new GridLayout(1, 3));
        navbar.setPreferredSize(new Dimension(0, 50));

        JButton btnPacientes = crearBotonEstilizado("Pacientes", "PACIENTES");
        JButton btnEmbarazadas = crearBotonEstilizado("Embarazadas", "EMBARAZADAS");
        JButton btnRiesgo = crearBotonEstilizado("En Riesgo", "RIESGO");

        navbar.add(btnPacientes);
        navbar.add(btnEmbarazadas);
        navbar.add(btnRiesgo);

        return navbar;
    }

    private JButton crearBotonEstilizado(String texto, final String vista) {
        final JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(COLOR_NORMAL);
        boton.setForeground(Color.WHITE);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));

        botonesNavbar.add(boton);  // Guardamos referencia

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedorVistas, vista);
                setBotonActivo(boton);
            }
        });

        return boton;
    }

    private void setBotonActivo(JButton botonActivo) {
        for (JButton b : botonesNavbar) {
            if (b == botonActivo) {
                b.setBackground(COLOR_PRESIONADO);
                b.setForeground(Color.BLACK);
            } else {
                b.setBackground(COLOR_NORMAL);
                b.setForeground(Color.WHITE);
            }
        }
    }

    private JPanel crearVistaPacientes() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Vista de todos los pacientes"));
        return panel;
    }

    private JPanel crearVistaEmbarazadas() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Vista de embarazadas"));
        return panel;
    }

    private JPanel crearVistaRiesgo() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Vista de pacientes en riesgo"));
        return panel;
    }
    
}
