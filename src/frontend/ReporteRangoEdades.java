package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import entidades.CMF;

public class ReporteRangoEdades extends JPanel {

	private CMF cmf;
	private DefaultCategoryDataset dataset;
	
	public ReporteRangoEdades() {
		cmf = CMF.getInstance();
		
		setBounds(0, 0, 796, 578);
		setBackground(Color.WHITE);
		
		dataset = new DefaultCategoryDataset();

		JFreeChart graficoBarras = ChartFactory.createBarChart(
				"Distribuci\u00F3n por Rango de Edad",
				"Rango de Edad",
				"Cantidad de Pacientes",
				dataset
		);
		setLayout(null);
		
		// Acceder al plot
		CategoryPlot plot = graficoBarras.getCategoryPlot();
		plot.setBackgroundPaint(SystemColor.menu); // fondo claro
		plot.setDomainGridlinePaint(SystemColor.controlShadow);           
		plot.setRangeGridlinePaint(SystemColor.controlShadow);            
		plot.setOutlineVisible(false); // Quitar bordes interiores

		// Personalizar las barras
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(0, 171, 227)); // color plano azul
		renderer.setDrawBarOutline(false);
		renderer.setBarPainter(new StandardBarPainter()); // eliminar sombreado, dejarlo plano

		// Mostrar los valores encima de las barras
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelPaint(Color.BLACK);
		renderer.setBaseItemLabelFont(new Font("Arial", Font.PLAIN, 16));

		// Ejes
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 16));
		domainAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 16));
		rangeAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));

		ChartPanel panelGrafica = new ChartPanel(graficoBarras);
		panelGrafica.setBounds(58, 43, 680, 473);
		add(panelGrafica);
		
		actualizarDatos();
	}
	
	public void actualizarDatos() {
	    int[] nuevasEdades = cmf.obtenerRangosDeEdad();
	    String serie = "Pacientes";
	    String[] etiquetas = {
	        "0-10", "11-20", "21-30", "31-40", "41-50",
	        "51-60", "61-70", "71-80", "81-90", ">91"
	    };

	    dataset.clear(); // limpia los datos anteriores

	    for (int i = 0; i < nuevasEdades.length && i < etiquetas.length; i++) {
	        dataset.addValue(nuevasEdades[i], serie, etiquetas[i]);
	    }
	}
	
	@Override
	public void show() {
		super.show();
		actualizarDatos();
	}

}
