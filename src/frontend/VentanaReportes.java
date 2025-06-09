package frontend;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import componentesPropios.BotonReporte;
import componentesPropios.TablaPersonalizada;
import entidades.CMF;

public class VentanaReportes extends JPanel implements MouseListener{

	private JPanel panelContenedor;
	private JPanel panelEncabezado;
	private CMF cmf;
	private JTable table;
	private PersonaTableModel model;
	private JLabel cartelEncabezado;

    public VentanaReportes() {
    	cmf = CMF.getInstance();
    	setBackground(Color.WHITE);
		setBounds(305, 0, 796, 673);
		setLayout(null);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		add(panelSuperior);
		panelSuperior.setBackground(new Color(0, 171, 227));
		panelSuperior.setLayout(null);
		
		JLabel cartelPestanna = new JLabel("REPORTES");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);
		
		panelContenedor = new JPanel();
		panelContenedor.setBounds(0, 95, 796, 578);
		add(panelContenedor);
		panelContenedor.setLayout(new CardLayout(0, 0));
		
		JPanel panelReportesGenerales = new JPanel();
		panelReportesGenerales.setBackground(Color.WHITE);
		panelContenedor.add(panelReportesGenerales, "GENERAL");
		panelReportesGenerales.setLayout(null);
		
		BotonReporte botonRangoEdad = new BotonReporte("RANGO DE EDADES", 
				"Rangos de edades de los pacientes representado en un gr\u00E1fico de barras para estudio demogr\u00E1fico de la localidad");
		botonRangoEdad.setBounds(75, 13, 292, 149);
		panelReportesGenerales.add(botonRangoEdad);
		botonRangoEdad.addMouseListener(this);
		
		BotonReporte botonEmbarazadasEnRiesgo = new BotonReporte("EMBARAZADAS EN RIESGO",
				"Lista de las embarazadas que se encuentran en una situaci\u00F3n de riesgo para su embarazo");
		botonEmbarazadasEnRiesgo.setBounds(419, 13, 292, 149);
		panelReportesGenerales.add(botonEmbarazadasEnRiesgo);
		botonEmbarazadasEnRiesgo.addMouseListener(this);
		
		BotonReporte botonPocentajeGenero = new BotonReporte("PORCENTAJE DE GÉNEROS",
				"Porcentaje que representa cada género del total de pacientes");
		botonPocentajeGenero.setBounds(75, 189, 292, 149);
		panelReportesGenerales.add(botonPocentajeGenero);
		botonPocentajeGenero.addMouseListener(this);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		panel_5.setBackground(SystemColor.menu);
		panel_5.setBounds(419, 189, 292, 149);
		panelReportesGenerales.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		panel_7.setBackground(SystemColor.menu);
		panel_7.setBounds(419, 372, 292, 149);
		panelReportesGenerales.add(panel_7);
		panel_7.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		panel_8.setBackground(SystemColor.menu);
		panel_8.setBounds(75, 372, 292, 149);
		panelReportesGenerales.add(panel_8);
		panel_8.setLayout(null);
		
		ReporteRangoEdades panelGraficoRangoEdades = new ReporteRangoEdades();	
		panelContenedor.add(panelGraficoRangoEdades, "RANGO DE EDADES");

		
		
		JPanel panelEmbarazadasEnRiesgo = new JPanel();
		panelEmbarazadasEnRiesgo.setBackground(Color.WHITE);
		panelContenedor.add(panelEmbarazadasEnRiesgo, "EMBARAZADAS EN RIESGO");
		panelEmbarazadasEnRiesgo.setLayout(null);

		model = new PersonaTableModel(cmf.obtenerEmbarazadasEnRiesgo()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = TablaPersonalizada.crearTablaPersonalizada(model);

		// Configuración de columnas (si quieres hacerla personalizada por tabla)
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);

		// Filtro (si aplica)
		final TableRowSorter<PersonaTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		// ScrollPane con tabla
		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 630, 406);

		// Panel contenedor
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(79, 60, 630, 437);
		panelEmbarazadasEnRiesgo.add(panelTabla);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setLayout(null);
		panelTabla.add(scrollPane);
		
		JPanel panelPorcientoMujeres = new JPanel();
		panelPorcientoMujeres.setBackground(Color.WHITE);
		panelContenedor.add(panelPorcientoMujeres, "PORCENTAJE DE GÉNEROS");
		
		 // Crear dataset
        DefaultPieDataset datasetPorcientoGenero = new DefaultPieDataset();
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
        PiePlot plotPorcentajeGenero = (PiePlot) chart.getPlot();
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
        panelPorcientoMujeres.setLayout(null);
        
        // Agregar gráfico a un panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(57, 33, 680, 485);
        panelPorcientoMujeres.add(chartPanel);
		
		panelEncabezado = new JPanel();
		panelEncabezado.setBackground(Color.WHITE);
		panelEncabezado.setBounds(0, 51, 796, 45);
		panelEncabezado.setVisible(false);
		add(panelEncabezado);
		panelEncabezado.setLayout(null);
		
		JLabel botonAtras = new JLabel("");
		botonAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout card = (CardLayout)(panelContenedor.getLayout());
				card.show(panelContenedor, "GENERAL");
				panelEncabezado.setVisible(false);			
			}
		});
		botonAtras.setIcon(new ImageIcon(VentanaReportes.class.getResource("/fotos/atras.png")));
		botonAtras.setFont(new Font("Arial", Font.BOLD, 16));
		botonAtras.setBounds(47, 15, 18, 18);
		botonAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelEncabezado.add(botonAtras);
		
		JSeparator separadorEncabezado = new JSeparator();
		separadorEncabezado.setPreferredSize(new Dimension(1, 3));
		separadorEncabezado.setForeground(SystemColor.controlShadow);
		separadorEncabezado.setBounds(44, 42, 700, 3);
		panelEncabezado.add(separadorEncabezado);
		
		cartelEncabezado = new JLabel("ENCABEZADO");
		cartelEncabezado.setFont(new Font("Arial", Font.BOLD, 16));
		cartelEncabezado.setBounds(77, 15, 637, 18);
		panelEncabezado.add(cartelEncabezado);
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {
		BotonReporte botonTocado = (BotonReporte)e.getSource();
		
		CardLayout card = (CardLayout)(panelContenedor.getLayout());
		card.show(panelContenedor, botonTocado.getTitulo());
		cartelEncabezado.setText(botonTocado.getTitulo());
		panelEncabezado.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void show() {
		super.show();
		CardLayout card = (CardLayout)(panelContenedor.getLayout());
		card.show(panelContenedor, "GENERAL");
		panelEncabezado.setVisible(false);
	}
}
