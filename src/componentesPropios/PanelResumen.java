package componentesPropios;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class PanelResumen extends JPanel {

    private JLabel cartelCantidad;
    private JProgressBar barraProgreso;
    private JLabel cartelTitulo;
    private JLabel cartelImagen;
    private double valorProgreso; // Valor para la barra de progreso
    private String cantidadTexto; // Texto para la etiqueta de cantidad
    private boolean porcentaje;

    public PanelResumen(String titulo, boolean porcentaje, ImageIcon imagen) {
    	this.porcentaje = porcentaje;
    	
        setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
        setBackground(SystemColor.menu);
        setBounds(0, 0, 309, 204);
        setLayout(null); // Usaremos null layout para posicionamiento manual

        cartelTitulo = new JLabel(titulo);
        cartelTitulo.setFont(new Font("Arial", Font.PLAIN, 18));
        cartelTitulo.setBounds(15, 16, 156, 22);
        add(cartelTitulo);

        cartelImagen = new JLabel("");
        cartelImagen.setIcon(imagen);
        cartelImagen.setBounds(159, 28, 135, 135);
        add(cartelImagen);

        cartelCantidad = new JLabel();
        cartelCantidad.setFont(new Font("Arial", Font.PLAIN, 18));
        cartelCantidad.setBounds(25, 141, 119, 20);
        add(cartelCantidad);
        
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(1, 3));
        separator.setForeground(SystemColor.controlShadow);
        separator.setBounds(15, 165, 279, 3);
        add(separator);

        if (this.porcentaje) {
        	crearBarraProgreso();
        }

        actualizarPanel(); // Aplicar la configuración inicial
    }
    
    public void crearBarraProgreso() {
    	 barraProgreso = new JProgressBar();
         barraProgreso.setStringPainted(true);
         barraProgreso.setBounds(15, 175, 279, 18);
         barraProgreso.setForeground(new Color(0, 171, 227));
         barraProgreso.setFont(new Font("Arial", Font.BOLD, 16));
         barraProgreso.setBorder(null);
         barraProgreso.setBackground(Color.WHITE);

         // Inicialmente no mostramos la barra
         barraProgreso.setVisible(false);
         add(barraProgreso);

         // Valores iniciales.  Deben ser inicializados para que no sean null
         this.valorProgreso = 0;
         this.cantidadTexto = "";
    }


    // Método para actualizar los valores y la visibilidad de la barra
    public void actualizarDatos(String cantidad, double progreso) {
        this.cantidadTexto = cantidad;
        this.valorProgreso = progreso;
        actualizarPanel();
    }
    
    public void actualizarDatos(String cantidad) {
        this.cantidadTexto = cantidad;
        actualizarPanel();
    }

    private void actualizarPanel() {
        //1. Seteamos el texto de la etiqueta cantidad
        cartelCantidad.setText(this.cantidadTexto);

        //2. Configuramos la barra de progreso
        if (porcentaje) {    	 
            if (this.valorProgreso > 0) {
            	barraProgreso.setValue((int)this.valorProgreso);
            	barraProgreso.setString(String.valueOf(valorProgreso) + "%");
            	barraProgreso.setVisible(true);

            } else {
            	barraProgreso.setVisible(false);
            }
        }
        
        // Es importante revalidar y repintar para que los cambios se vean
        //revalidate();
        //repaint();
    }
}
