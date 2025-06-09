package frontend;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import entidades.CMF;

public class ReportePorcentajeGenero extends JPanel {

	private CMF cmf;
	private DefaultPieDataset datasetPorcientoGenero;
	private PiePlot plotPorcentajeGenero;
	
	public ReportePorcentajeGenero() {
		cmf = CMF.getInstance();
		
		setBounds(0, 0, 796, 578);
		setBackground(Color.WHITE);
		setLayout(null);
		
		// Crear dataset
        datasetPorcientoGenero = new DefaultPieDataset();
        actualizarDatos(); // llenar al principio
        double valor = cmf.porcentajeMujeresRespectoAHombres();
        datasetPorcientoGenero.setValue("Hombres", 100 - valor);
        datasetPorcientoGenero.setValue("Mujeres", valor); 

        // Crear gráfico
        JFreeChart chart = ChartFactory.createPieChart(
                "Porcentaje de Hombres y Mujeres",
                datasetPorcientoGenero,
                true, // leyenda
                true,
                false
        );
        
        // Personalizar el gráfico
        plotPorcentajeGenero = (PiePlot) chart.getPlot();
        plotPorcentajeGenero.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}", new DecimalFormat("0"), new DecimalFormat("0.0%")));
        plotPorcentajeGenero.setSimpleLabels(true); // los pone dentro
        plotPorcentajeGenero.setLabelPaint(Color.WHITE); // color de texto
        plotPorcentajeGenero.setLabelFont(new Font("Arial", Font.BOLD, 16)); // fuente
        plotPorcentajeGenero.setLabelBackgroundPaint(null);
        plotPorcentajeGenero.setLabelOutlinePaint(null);
        plotPorcentajeGenero.setLabelShadowPaint(null);
        plotPorcentajeGenero.setSectionPaint("Hombres", new Color(100, 149, 237)); // Azul
        plotPorcentajeGenero.setSectionPaint("Mujeres", new Color(255, 105, 180)); // Rosa
        plotPorcentajeGenero.setBackgroundPaint(Color.WHITE);
        plotPorcentajeGenero.setLabelFont(new Font("Arial Black", Font.BOLD, 18));
        plotPorcentajeGenero.setCircular(true);
        plotPorcentajeGenero.setLabelGap(0.02);
        
        // Agregar gráfico a un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(57, 33, 680, 485);
        add(chartPanel);
	}
	
	public void actualizarDatos() {
		double valor = cmf.porcentajeMujeresRespectoAHombres();
		datasetPorcientoGenero.setValue("Hombres", 100 - valor);
		datasetPorcientoGenero.setValue("Mujeres", valor);
	}
	
	@Override
	public void show() {
		super.show();
		actualizarDatos();
	}

}
