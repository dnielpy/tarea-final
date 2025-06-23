package frontend.panelesPrincipales;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import frontend.ConstantesFrontend;
import frontend.panelesReportes.ReporteCantVisitasEnUnMes;
import frontend.panelesReportes.ReporteEmbarazadasEnRiesgo;
import frontend.panelesReportes.ReportePorcentajeGenero;
import frontend.panelesReportes.ReporteRangoEdades;
import frontend.ui.botones.BotonReporte;
import frontend.ui.botones.ImageButtonLabel;

public class VentanaReportes extends JPanel implements MouseListener, ConstantesFrontend {

	private JPanel panelContenedor;
	private JPanel panelEncabezado;
	private JLabel cartelEncabezado;

    public VentanaReportes() {
    	setBackground(Color.WHITE);
		setBounds(305, 0, 796, 673);
		setLayout(null);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		add(panelSuperior);
		panelSuperior.setBackground(COLOR_AZUL);
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
		
		BotonReporte botonReporteVisitasMes = new BotonReporte("VISITAS EN UN MES",
				"Muestra una gráfica de línea de las visitas de un mes, permitiendo analizar comportamientos y patrones dentro de estas");
		
		botonReporteVisitasMes.setBounds(419, 189, 292, 149);
		panelReportesGenerales.add(botonReporteVisitasMes);
		botonReporteVisitasMes.addMouseListener(this);
		
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
		
		ReporteEmbarazadasEnRiesgo panelEmbarazadasEnRiesgo = new ReporteEmbarazadasEnRiesgo();
		panelContenedor.add(panelEmbarazadasEnRiesgo, "EMBARAZADAS EN RIESGO");
		
		ReportePorcentajeGenero panelPorcientoMujeres = new ReportePorcentajeGenero();
		panelContenedor.add(panelPorcientoMujeres, "PORCENTAJE DE GÉNEROS");
		
		ReporteCantVisitasEnUnMes panelReporteCantVisitasEnUnMes = new ReporteCantVisitasEnUnMes();
		panelContenedor.add(panelReporteCantVisitasEnUnMes, "VISITAS EN UN MES");
		
		panelEncabezado = new JPanel();
		panelEncabezado.setBackground(Color.WHITE);
		panelEncabezado.setBounds(0, 51, 796, 45);
		panelEncabezado.setVisible(false);
		add(panelEncabezado);
		panelEncabezado.setLayout(null);
		
		ImageButtonLabel botonAtras = new ImageButtonLabel
				(new ImageIcon(VentanaReportes.class.getResource("/fotos/atras.png")));
		botonAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout card = (CardLayout)(panelContenedor.getLayout());
				card.show(panelContenedor, "GENERAL");
				panelEncabezado.setVisible(false);			
			}
		});
		botonAtras.setBounds(47, 15, 18, 18);
		panelEncabezado.add(botonAtras);
		
		JSeparator separadorEncabezado = new JSeparator();
		separadorEncabezado.setPreferredSize(new Dimension(1, 3));
		separadorEncabezado.setForeground(COLOR_GRIS);
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
