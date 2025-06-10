package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class VentanaHojasDeCargo extends JPanel {

	public VentanaHojasDeCargo() {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(305, 0, 796, 673);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 796, 51); // ajustado al ancho del panel principal
		add(panelSuperior);
		panelSuperior.setBackground(new Color(0, 171, 227));
		panelSuperior.setLayout(null);

		JLabel cartelPestanna = new JLabel("HOJAS DE CARGO");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 200, 51);
		panelSuperior.add(cartelPestanna);
		
		

	}
}