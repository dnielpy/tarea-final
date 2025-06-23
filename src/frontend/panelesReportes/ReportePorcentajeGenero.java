package frontend.panelesReportes;

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
    private ChartPanel chartPanel;

    public ReportePorcentajeGenero() {
        cmf = CMF.getInstance();

        setBounds(0, 0, 796, 578);
        setBackground(Color.WHITE);
        setLayout(null);

        // Crear dataset
        datasetPorcientoGenero = new DefaultPieDataset();
        actualizarDatos(); // llenar al principio

        // Crear gráfico
        JFreeChart chart = ChartFactory.createPieChart(
                "",
                datasetPorcientoGenero,
                true,
                true,
                false
        );

        // Personalizar el plot
        plotPorcentajeGenero = (PiePlot) chart.getPlot();
        plotPorcentajeGenero.setLabelGenerator(
                new StandardPieSectionLabelGenerator("{2}", new DecimalFormat("0"), new DecimalFormat("0.0%"))
        );
        plotPorcentajeGenero.setSimpleLabels(true);
        plotPorcentajeGenero.setLabelPaint(Color.WHITE);
        plotPorcentajeGenero.setLabelFont(new Font("Arial", Font.BOLD, 16));
        plotPorcentajeGenero.setLabelBackgroundPaint(null);
        plotPorcentajeGenero.setLabelOutlinePaint(null);
        plotPorcentajeGenero.setLabelShadowPaint(null);
        plotPorcentajeGenero.setSectionPaint("Hombres", new Color(100, 149, 237));
        plotPorcentajeGenero.setSectionPaint("Mujeres", new Color(255, 105, 180));
        plotPorcentajeGenero.setBackgroundPaint(Color.WHITE);
        plotPorcentajeGenero.setOutlineVisible(false); // quitar borde exterior
        plotPorcentajeGenero.setShadowPaint(null);     // quitar sombra
        plotPorcentajeGenero.setLabelGap(0.02);
        plotPorcentajeGenero.setCircular(true);

        // Crear ChartPanel
        chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(57, 33, 680, 485);

        // Desactivar zoom y popup
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setPopupMenu(null);

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
