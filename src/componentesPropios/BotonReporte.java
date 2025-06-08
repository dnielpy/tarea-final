package componentesPropios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class BotonReporte extends JPanel {

	private String titulo;
	
	public BotonReporte(String titulo, String texto) {
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

}
