package componentesPropios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;

public class BotonBlanco extends JButton {
	public BotonBlanco(String texto) {
        super(texto);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.BLACK);
        setBackground(SystemColor.menu);
    	setFont(new Font("Arial", Font.PLAIN, 18));
        setFocusPainted(false); // Quitar el marco de foco
    }
}
