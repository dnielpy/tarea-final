package frontend.panelesReportes;

import java.awt.Color;

import javax.swing.JPanel;

import entidades.CMF;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

import java.awt.*;

import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;

import javax.swing.*;

import java.awt.geom.Ellipse2D;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.axis.CategoryAxis;

import util.ConstantesFrontend;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import frontend.ui.botones.ImageButtonLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReporteCantVisitasEnUnMes extends JPanel implements ConstantesFrontend {

    private CMF cmf;
    private JSpinner spinnerFecha;
    private ChartPanel panelGrafico;
    private DefaultCategoryDataset dataset;
    private LineAndShapeRenderer renderer;

    public ReporteCantVisitasEnUnMes() {
        cmf = CMF.getInstance();

        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 0, 796, 578);

        Font fuenteGeneral = new Font("Arial", Font.PLAIN, 16);

     // Fecha actual
        Date fechaHoy = new Date();

        // Calcular último día del mes actual para límite máximo
        Calendar calMax = Calendar.getInstance();
        calMax.setTime(fechaHoy);
        calMax.set(Calendar.DAY_OF_MONTH, calMax.getActualMaximum(Calendar.DAY_OF_MONTH));
        final Date maxFecha = calMax.getTime();

        // Crear modelo con límite máximo
        SpinnerDateModel modeloFecha = new SpinnerDateModel(fechaHoy, null, maxFecha, Calendar.MONTH);
        spinnerFecha = new JSpinner(modeloFecha);

        // Configurar formato para mostrar solo mes y año
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFecha, "MMMM yyyy");
        spinnerFecha.setEditor(editor);

        spinnerFecha.setFont(fuenteGeneral);
        spinnerFecha.setBounds(382, 13, 205, 30);
        add(spinnerFecha);

        // Listener clásico para actualizar al cambiar fecha
        spinnerFecha.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                actualizarDatos();
            }
        });

        // Listener para que la rueda del mouse cambie el mes/año respetando el límite máximo
        spinnerFecha.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            @Override
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
                int rotacion = e.getWheelRotation();
                Date fechaActual = (Date) spinnerFecha.getValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(fechaActual);
                cal.add(Calendar.MONTH, -rotacion);
                Date nuevaFecha = cal.getTime();

                // Solo cambia si no pasa el máximo permitido
                if (!nuevaFecha.after(maxFecha)) {
                    spinnerFecha.setValue(nuevaFecha);
                }
            }
        });

        spinnerFecha.setFont(fuenteGeneral);
        spinnerFecha.setBounds(382, 13, 205, 30);
        add(spinnerFecha);     

        // Listener para actualizar datos al cambiar mes o año
        spinnerFecha.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                actualizarDatos();
            }
        });

        // Dataset y gráfico
        dataset = new DefaultCategoryDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                "",
                "Día",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setOutlineVisible(false);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 12));
        domainAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 16));
        rangeAxis.setLabelFont(new Font("Arial", Font.BOLD, 18));
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setLowerBound(0);
        rangeAxis.setTickUnit(new NumberTickUnit(1));
        rangeAxis.setLabel("");

        renderer = new LineAndShapeRenderer(true, true) {
            @Override
            public boolean getItemShapeVisible(int row, int column) {
                Number val = dataset.getValue(row, column);
                return val != null && val.intValue() > 0;
            }
        };
        renderer.setSeriesPaint(0, COLOR_AZUL);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 6, 6));
        renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(renderer);

        panelGrafico = new ChartPanel(chart);
        panelGrafico.setBounds(15, 55, 750, 490);
        panelGrafico.setBorder(BorderFactory.createEmptyBorder());
        panelGrafico.setBackground(Color.WHITE);
        panelGrafico.setMouseWheelEnabled(false);
        panelGrafico.setPopupMenu(null);
        panelGrafico.setDomainZoomable(false);
        panelGrafico.setRangeZoomable(false);

        add(panelGrafico);

        JLabel cartelTitulo = new JLabel("Gráfico de visitas por día del mes de:");
        cartelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        cartelTitulo.setBounds(50, 13, 326, 30);
        add(cartelTitulo);
        
        ImageButtonLabel botonReset = new ImageButtonLabel((ImageIcon) null);
        botonReset.setToolTipText("Clic para volver al mes actual");
        botonReset.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Date fechaActual = new Date();
        		spinnerFecha.setValue(fechaActual);
        		actualizarDatos();
        	}
        });
        botonReset.setIcon(new ImageIcon(ReporteCantVisitasEnUnMes.class.getResource("/fotos/reset.png")));
        botonReset.setText("");
        botonReset.setBounds(600, 15, 25, 25);
        add(botonReset);

        actualizarDatos();
    }

    private void actualizarDatos() {
        Date fechaSeleccionada = (Date) spinnerFecha.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaSeleccionada);

        int mes = cal.get(Calendar.MONTH) + 1;
        int anio = cal.get(Calendar.YEAR);

        int[] visitasPorDia = cmf.obtenerCantVisitasEnUnMes(mes, anio);

        dataset.clear();
        int max = 0;

        if (visitasPorDia != null) {
            for (int i = 0; i < visitasPorDia.length; i++) {
                int valor = visitasPorDia[i];
                dataset.addValue(valor, "Visitas", String.valueOf(i + 1));
                if (valor > max) {
                    max = valor;
                }
            }
        }

        // Escalamos el eje Y
        NumberAxis rangeAxis = (NumberAxis) panelGrafico.getChart().getCategoryPlot().getRangeAxis();
        rangeAxis.setLowerBound(0);
        if (max > 0) {
            // Factor de escala: ajusta si quieres más o menos espacio
            int upperBound = (int) Math.ceil(max * 1.4);
            rangeAxis.setUpperBound(upperBound);
        } else {
            rangeAxis.setUpperBound(1); // evitar escala 0
        }

        panelGrafico.revalidate();
        panelGrafico.repaint();
    }

    @Override
    public void show() {
        super.show();
        actualizarDatos();
    }
}
