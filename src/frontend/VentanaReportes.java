package frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;

import componentesPropios.BotonReporte;

public class VentanaReportes extends JPanel implements MouseListener{

    private JPanel panelContenedor;
    private CardLayout cardLayout;

    public VentanaReportes() {
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
		panelContenedor.setBounds(0, 51, 796, 622);
		add(panelContenedor);
		panelContenedor.setLayout(new CardLayout(0, 0));
		
		JPanel panelReportesGenerales = new JPanel();
		panelReportesGenerales.setBackground(Color.WHITE);
		panelContenedor.add(panelReportesGenerales, "GENERAL");
		panelReportesGenerales.setLayout(null);
		
		BotonReporte botonRangoEdad = new BotonReporte("RANGO DE EDADES", 
				"Rangos de edades de los pacientes en un gr\u00E1fico de edades para estudio demogr\u00E1fico de la localidad");
		botonRangoEdad.setBounds(76, 46, 292, 149);
		panelReportesGenerales.add(botonRangoEdad);
		botonRangoEdad.addMouseListener(this);
		
		JPanel embarazadasEnRiesgo = new JPanel();
		embarazadasEnRiesgo.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		embarazadasEnRiesgo.setBackground(SystemColor.menu);
		embarazadasEnRiesgo.setBounds(420, 46, 292, 149);
		panelReportesGenerales.add(embarazadasEnRiesgo);
		embarazadasEnRiesgo.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		panel_5.setBackground(SystemColor.menu);
		panel_5.setBounds(420, 222, 292, 149);
		panelReportesGenerales.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		panel_6.setBackground(SystemColor.menu);
		panel_6.setBounds(76, 222, 292, 149);
		panelReportesGenerales.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		panel_7.setBackground(SystemColor.menu);
		panel_7.setBounds(420, 405, 292, 149);
		panelReportesGenerales.add(panel_7);
		panel_7.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(SystemColor.controlShadow, 1, true));
		panel_8.setBackground(SystemColor.menu);
		panel_8.setBounds(76, 405, 292, 149);
		panelReportesGenerales.add(panel_8);
		panel_8.setLayout(null);
		
		JPanel panelGraficoRangoEdades = new JPanel();
		panelContenedor.add(panelGraficoRangoEdades, "RANGO DE EDADES");
		
		JPanel panel_3 = new JPanel();
		panelContenedor.add(panel_3, "name_347146225710400");
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		BotonReporte botonTocado = (BotonReporte)e.getSource();
		
		CardLayout card = (CardLayout)(panelContenedor.getLayout());
		card.show(panelContenedor, botonTocado.getTitulo());
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
}
