package componentesPropios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class BotonBlanco extends JButton implements MouseListener{
	public BotonBlanco(String texto) {
        super(texto);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.BLACK);
        setBackground(SystemColor.menu);
    	setFont(new Font("Arial", Font.PLAIN, 18));
        setFocusPainted(false); // Quitar el marco de foco
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void mouseEntered(MouseEvent arg0) {
        setBackground(SystemColor.controlHighlight);
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        setBackground(SystemColor.menu);
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
