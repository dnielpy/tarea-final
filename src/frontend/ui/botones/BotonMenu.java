package frontend.ui.botones;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import util.ConstantesFrontend;

public class BotonMenu extends JButton implements ConstantesFrontend {
	
	private boolean esActivo;
	
	// Constructor
    public BotonMenu(String texto) {
        super(texto);
        inicializarBoton();
    }
    
    public BotonMenu() {
        super();
        inicializarBoton();
    }
    
    public void inicializarBoton() {
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
        setForeground(Color.WHITE);
        setBackground(COLOR_AZUL);
    	setFont(new Font("Arial", Font.PLAIN, 18));
    	setBounds(0, 0, 250, 70);
        setFocusPainted(false); // Quitar el marco de foco
    }

	public boolean estaActivo() {
		return esActivo;
	}

	public void setActivo(boolean esActivo) {
		if (esActivo) {
			setForeground(Color.BLACK);
			setBackground(Color.WHITE);
		} else {
			setForeground(Color.WHITE);
	        setBackground(COLOR_AZUL);
		}	
		this.esActivo = esActivo;
	}
    
}
