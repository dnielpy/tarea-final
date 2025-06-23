package frontend.panelesReportes;

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
import frontend.ConstantesFrontend;

public class ReporteRangoEdades extends JPanel implements ConstantesFrontend {

    private CMF cmf;
    private DefaultCategoryDataset dataset;
    private ChartPanel panelGrafica;

    public ReporteRangoEdades() {
        cmf = CMF.getInstance();

        setBounds(0, 0, 796, 578);
        setBackground(Color.WHITE);
        setLayout(null);

        dataset = new DefaultCategoryDataset();

        JFreeChart graficoBarras = ChartFactory.createBarChart(
                "Distribución por Rango de Edad",
                "Rango de Edad",
                "Cantidad de Pacientes",
                dataset
        );

        graficoBarras.removeLegend();
        
        // Configurar el plot
        CategoryPlot plot = graficoBarras.getCategoryPlot();
        plot.setBackgroundPaint(SystemColor.menu);
        plot.setDomainGridlinePaint(SystemColor.controlShadow);
        plot.setRangeGridlinePaint(SystemColor.controlShadow);
        plot.setOutlineVisible(false);

        // Personalización de barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, COLOR_AZUL);
        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardBarPainter());

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
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLowerBound(0); // Siempre mostrar desde 0

        // Crear panel del gráfico
        panelGrafica = new ChartPanel(graficoBarras);
        panelGrafica.setBounds(15, 50, 750, 490);
        panelGrafica.setDomainZoomable(false);
        panelGrafica.setRangeZoomable(false);
        panelGrafica.setPopupMenu(null); // Desactivar clic derecho

        add(panelGrafica);

        // Cargar los datos al inicio
        actualizarDatos();
    }

    public void actualizarDatos() {
        int[] nuevasEdades = cmf.obtenerRangosDeEdad();
        String serie = "Pacientes";
        String[] etiquetas = {
            "0-10", "11-20", "21-30", "31-40", "41-50",
            "51-60", "61-70", "71-80", "81-90", ">91"
        };

        dataset.clear();

        for (int i = 0; i < nuevasEdades.length && i < etiquetas.length; i++) {
            dataset.addValue(nuevasEdades[i], serie, etiquetas[i]);
        }

        // Forzar redibujado y ajuste automático del gráfico
        panelGrafica.restoreAutoBounds();
        panelGrafica.revalidate();
        panelGrafica.repaint();
    }

    @Override
    public void show() {
        super.show();
        actualizarDatos();
    }
}
