package componentesPropios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;

public class BotonMenu extends JButton{
	
	private boolean esActivo;
	
	// Constructor
    public BotonMenu(String texto) {
        super(texto);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
        setForeground(Color.WHITE);
        setBackground(new Color(0, 171, 227));
    	setFont(new Font("Arial", Font.PLAIN, 18));
    	setBounds(0, 0, 250, 70);
        setFocusPainted(false); // Quitar el marco de foco
    }
    
    public BotonMenu() {
        super();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
        setForeground(Color.WHITE);
        setBackground(new Color(0, 171, 227));
    	setFont(new Font("Arial Black", Font.PLAIN, 18));
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
	        setBackground(new Color(0, 171, 227));
		}	
		this.esActivo = esActivo;
	}
    
}
