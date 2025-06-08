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

		int[] pacientesPorEdad = {5, 12, 20, 14, 8, 6, 3, 1};

		DefaultCategoryDataset dataset = crearDataset(pacientesPorEdad);

		JFreeChart graficoBarras = ChartFactory.createBarChart(
				"Distribución por Rango de Edad",
				"Rango de Edad",
				"Cantidad de Pacientes",
				dataset
		);

		ChartPanel panel = new ChartPanel(graficoBarras);
		panel.setBounds(50, 103, 680, 500); // Asigna posición y tamaño al gráfico
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Esto es solo pa probar, despues lo pongo donde va");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(50, 74, 680, 16);
		add(lblNewLabel);
	}

	private DefaultCategoryDataset crearDataset(int[] edades) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String serie = "Pacientes";

		String[] etiquetas = {
				"0-10", "11-20", "21-30", "31-40",
				"41-50", "51-60", "61-70", "71+"
		};

		for (int i = 0; i < edades.length && i < etiquetas.length; i++) {
			dataset.addValue(edades[i], serie, etiquetas[i]);
		}

		return dataset;
	}
}