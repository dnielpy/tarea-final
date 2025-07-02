package frontend.ui.botones;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import util.ConstantesFrontend;

public class BotonReporte extends JPanel implements MouseListener, ConstantesFrontend {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	
	public BotonReporte(String titulo, String texto) {
		addMouseListener(this);
		setTitulo(titulo);
		
		setBorder(BORDE_COMPONENTE);
		setBackground(COLOR_GRIS_CLARO);
		setLayout(null);
		setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel cartelTexto = new JLabel("<html><div style='width:" + "px;'>" + texto + "</div></html>");
		cartelTexto.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelTexto.setBounds(12, 49, 268, 87);
		cartelTexto.setBorder(null); 
		cartelTexto.setVerticalAlignment(SwingConstants.TOP);     // Alineación vertical arriba
		cartelTexto.setHorizontalAlignment(SwingConstants.LEFT);   // Alineación horizontal izquierda
		add(cartelTexto);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 38, 268, 3);
		add(separator);
		separator.setForeground(COLOR_GRIS);
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
		setBackground(COLOR_GRIS_CURSOR);		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setBackground(COLOR_GRIS_CLARO);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		setBackground(COLOR_PRESIONADO);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
