package componentesPropios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import frontend.ConstantesFrontend;

public class BotonBlanco extends JButton implements MouseListener, ConstantesFrontend {
	
	public BotonBlanco(String texto) {
        super(texto);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        // setBorder(BORDE_COMPONENTE);
        setForeground(Color.BLACK);
        setBackground(COLOR_GRIS_CLARO);
    	setFont(new Font("Arial", Font.PLAIN, 18));
        setFocusPainted(false); // Quitar el marco de foco
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void mouseEntered(MouseEvent arg0) {
        setBackground(COLOR_GRIS);
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        setBackground(COLOR_GRIS_CLARO);
    }

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
