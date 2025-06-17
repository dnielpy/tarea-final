package componentesPropios;

import javax.swing.*;
import frontend.ConstantesFrontend;

import java.awt.*;

public class PanelResumen extends JPanel implements ConstantesFrontend {

    private JLabel cartelCantidad;
    private JProgressBar barraProgreso;
    private JLabel cartelTitulo;
    private JLabel cartelImagen;
    private double valorProgreso; 
    private String cantidadTexto; 
    private boolean porcentaje;

    public PanelResumen(String titulo, boolean porcentaje, ImageIcon imagen) {
    	this.porcentaje = porcentaje;
    	
        setBorder(BORDE_COMPONENTE);
        setBackground(SystemColor.menu);
        setBounds(0, 0, 309, 204);
        setLayout(null); 

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
        
        actualizarPanel(); 
    }
    
    public void crearBarraProgreso() {
    	 barraProgreso = new JProgressBar();
         barraProgreso.setStringPainted(true);
         barraProgreso.setBounds(15, 175, 279, 18);
         barraProgreso.setForeground(COLOR_AZUL);
         barraProgreso.setFont(new Font("Arial", Font.BOLD, 16));
         barraProgreso.setBorder(null);
         barraProgreso.setBackground(Color.WHITE);

         barraProgreso.setVisible(false);
         add(barraProgreso);
       
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
        cartelCantidad.setText(this.cantidadTexto);

        if (porcentaje) {    	 
            if (this.valorProgreso > 0) {
            	barraProgreso.setValue((int)this.valorProgreso);
            	barraProgreso.setString(String.valueOf(valorProgreso) + "%");
            	barraProgreso.setVisible(true);
            } else {
            	barraProgreso.setVisible(false);
            }
        }
    }
}
