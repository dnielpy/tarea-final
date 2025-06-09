package componentesPropios;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class BotonReporte extends JPanel implements MouseListener {

	private String titulo;
	
	public BotonReporte(String titulo, String texto) {
		addMouseListener(this);
		setTitulo(titulo);
		
		setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		setBackground(SystemColor.menu);
		setLayout(null);
		setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel cartelRangoEdades = new JLabel("<html><div style='width:" + "px;'>" + texto + "</div></html>");
		cartelRangoEdades.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelRangoEdades.setBounds(12, 49, 268, 87);
		cartelRangoEdades.setBorder(null); 
		cartelRangoEdades.setVerticalAlignment(SwingConstants.TOP);     // Alineación vertical arriba
		cartelRangoEdades.setHorizontalAlignment(SwingConstants.LEFT);   // Alineación horizontal izquierda
		add(cartelRangoEdades);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 38, 268, 3);
		add(separator);
		separator.setForeground(SystemColor.controlShadow);
		separator.setPreferredSize(new Dimension(1, 3));

		JLabel cartelTitulo = new JLabel(titulo);
		cartelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
		cartelTitulo.setBounds(12, 13, 257, 16);
		add(cartelTitulo);

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
		setBackground(SystemColor.LIGHT_GRAY);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
