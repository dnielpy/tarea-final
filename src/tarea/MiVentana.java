package tarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiVentana extends JFrame {

    // Componentes de la interfaz
    private JButton botonSaludo;
    private JTextField campoTexto;
    private JLabel etiqueta;

    public MiVentana() {
        // Configuración básica de la ventana
        setTitle("Mi Aplicación Escolar");  // Título
        setSize(400, 300);                 // Tamaño (ancho, alto)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar al salir
        setLayout(new FlowLayout());        // Organización de componentes

        // Inicializar componentes
        etiqueta = new JLabel("Ingresa tu nombre:");
        campoTexto = new JTextField(20);    // 20 columnas de ancho
        botonSaludo = new JButton("Saludar");

        // Añadir acción al botón
        botonSaludo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoTexto.getText();
                JOptionPane.showMessageDialog(
                        MiVentana.this,
                        "¡Hola, " + nombre + "! Bienvenido a IntelliJ en Ubuntu."
                );
            }
        });

        // Añadir componentes a la ventana
        add(etiqueta);
        add(campoTexto);
        add(botonSaludo);

        // Mostrar ventana
        setVisible(true);
    }
}