package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaReportes extends JPanel {

	public VentanaReportes() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(0, 0, 832, 689);
		add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		panelPrincipal.add(panelSuperior);
		panelSuperior.setBackground(new Color(0, 171, 227));
		panelSuperior.setLayout(null);
		
		JLabel cartelPestanna = new JLabel("REPORTES");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);
	}

}
