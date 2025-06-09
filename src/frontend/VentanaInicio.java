package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

import entidades.CMF;

public class VentanaInicio extends JPanel {
	
	private CMF cmf;
	private JLabel cantidadPacientes;
	private JLabel cantidadEnRiesgo;
	private JLabel cantidadEmbarazadas;
	private JProgressBar porcientoEnRiesgo;
	private JProgressBar porcientoEmbarazadas;
	
	public VentanaInicio() {
		cmf = CMF.getInstance();
		
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(305, 0, 796, 673);
		
		// Barra superior
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		add(panelSuperior);
		panelSuperior.setBackground(new Color(0, 171, 227));
		panelSuperior.setLayout(null);
		
		JLabel cartelPestanna = new JLabel("INICIO");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);
		
		// Cuadro de cantidad de pacientes
		
		JPanel panelCantidadPacientes = new JPanel();
		panelCantidadPacientes.setBounds(46, 116, 309, 204);
		add(panelCantidadPacientes);
		panelCantidadPacientes.setLayout(null);
		
		JLabel cartelPacientes = new JLabel("PACIENTES");
		cartelPacientes.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPacientes.setBounds(15, 16, 112, 20);
		panelCantidadPacientes.add(cartelPacientes);
		
		JLabel imagenPaciente = new JLabel("");
		imagenPaciente.setIcon(new ImageIcon(VentanaInicio.class.getResource("/fotos/paciente.png")));
		imagenPaciente.setBounds(159, 35, 135, 135);
		panelCantidadPacientes.add(imagenPaciente);
		
		cantidadPacientes = new JLabel();
		cantidadPacientes.setFont(new Font("Arial", Font.PLAIN, 18));
		cantidadPacientes.setBounds(15, 150, 112, 20);
		panelCantidadPacientes.add(cantidadPacientes);
		
		// Cuadro de cantidad de visitas del día
		
		JPanel panelCantidadVisitasDelDia = new JPanel();
		panelCantidadVisitasDelDia.setBounds(420, 116, 309, 204);
		add(panelCantidadVisitasDelDia);
		panelCantidadVisitasDelDia.setLayout(null);
		
		JLabel imagenVisitas = new JLabel("");
		imagenVisitas.setIcon(new ImageIcon(VentanaInicio.class.getResource("/fotos/visitas.png")));
		imagenVisitas.setBounds(159, 35, 135, 135);
		panelCantidadVisitasDelDia.add(imagenVisitas);
		
		JLabel cantidadVisitas = new JLabel("No implementado");
		cantidadVisitas.setFont(new Font("Arial", Font.PLAIN, 18));
		cantidadVisitas.setBounds(15, 150, 129, 20);
		panelCantidadVisitasDelDia.add(cantidadVisitas);
		
		JLabel cartelVisitas = new JLabel("VISITAS DEL D\u00CDA");
		cartelVisitas.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelVisitas.setBounds(15, 16, 150, 20);
		panelCantidadVisitasDelDia.add(cartelVisitas);
		
		// Cuadro de cantidad de pacientes en riesgo
		
		JPanel panelCantidadEnRiesgo = new JPanel();
		panelCantidadEnRiesgo.setBounds(420, 380, 309, 204);
		add(panelCantidadEnRiesgo);
		panelCantidadEnRiesgo.setLayout(null);
		
		JLabel imagenEnRiesgo = new JLabel("");
		imagenEnRiesgo.setIcon(new ImageIcon(VentanaInicio.class.getResource("/fotos/riesgo.png")));
		imagenEnRiesgo.setBounds(159, 26, 135, 135);
		panelCantidadEnRiesgo.add(imagenEnRiesgo);
		
		JLabel cartelPacientesEnRiesgo = new JLabel("EN RIESGO");
		cartelPacientesEnRiesgo.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPacientesEnRiesgo.setBounds(15, 16, 142, 20);
		panelCantidadEnRiesgo.add(cartelPacientesEnRiesgo);
		
		cantidadEnRiesgo = new JLabel();
		cantidadEnRiesgo.setFont(new Font("Arial", Font.PLAIN, 18));
		cantidadEnRiesgo.setBounds(15, 141, 129, 20);
		panelCantidadEnRiesgo.add(cantidadEnRiesgo);
		
		porcientoEnRiesgo = new JProgressBar();
		porcientoEnRiesgo.setStringPainted(true);
		porcientoEnRiesgo.setForeground(new Color(0, 171, 227));
		porcientoEnRiesgo.setFont(new Font("Arial", Font.BOLD, 16));
		porcientoEnRiesgo.setBorder(null);
		porcientoEnRiesgo.setBackground(Color.WHITE);
		porcientoEnRiesgo.setBounds(15, 170, 279, 18);
		panelCantidadEnRiesgo.add(porcientoEnRiesgo);

		// Cuadro de cantidad de embarazadas
		
		JPanel panelCantidadEmbarazadas = new JPanel();
		panelCantidadEmbarazadas.setBounds(46, 380, 309, 204);
		add(panelCantidadEmbarazadas);
		panelCantidadEmbarazadas.setLayout(null);
		
		JLabel cartelEmbarazadas = new JLabel("EMBARAZADAS");
		cartelEmbarazadas.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelEmbarazadas.setBounds(15, 16, 142, 20);
		panelCantidadEmbarazadas.add(cartelEmbarazadas);
		
		cantidadEmbarazadas = new JLabel();
		cantidadEmbarazadas.setFont(new Font("Arial", Font.PLAIN, 18));
		cantidadEmbarazadas.setBounds(15, 141, 129, 20);
		panelCantidadEmbarazadas.add(cantidadEmbarazadas);
		
		JLabel imagenEmbarazadas = new JLabel("");
		imagenEmbarazadas.setIcon(new ImageIcon(VentanaInicio.class.getResource("/fotos/embarazada.png")));
		imagenEmbarazadas.setBounds(159, 26, 135, 135);
		panelCantidadEmbarazadas.add(imagenEmbarazadas);

		porcientoEmbarazadas = new JProgressBar();
		porcientoEmbarazadas.setBackground(Color.WHITE);
		porcientoEmbarazadas.setForeground(new Color(0, 171, 227));
		porcientoEmbarazadas.setStringPainted(true);
		porcientoEmbarazadas.setFont(new Font("Arial", Font.BOLD, 16));
		porcientoEmbarazadas.setBounds(15, 170, 279, 18);
		panelCantidadEmbarazadas.add(porcientoEmbarazadas);

		porcientoEmbarazadas.setBorder(null);

		actualizarDatos();
	}
	
	public void actualizarDatos() {
		cantidadPacientes.setText(String.valueOf(cmf.obtenerTotalPacientes()));
		cantidadEnRiesgo.setText(String.valueOf(cmf.obtenerPacientesEnRiesgo()));
		cantidadEmbarazadas.setText(String.valueOf(cmf.obtenerCantidadDeEmbarazadas() + 
				" de " + String.valueOf(cmf.obtenerCantidadMujeres())));
		
		double pacientesEnRiesgo = cmf.porcentajeEnRiesgoDelTotal();
		if (pacientesEnRiesgo > 0) {
			porcientoEnRiesgo.setString(String.valueOf(pacientesEnRiesgo) + "%");
			porcientoEnRiesgo.setValue((int) pacientesEnRiesgo);
		} else {
			porcientoEnRiesgo.setVisible(false);
		}
		
		double embarazadas = cmf.porcentajeEmbarazadasRespectoAMujeres();
		if (embarazadas > 0) {
			porcientoEmbarazadas.setString(String.valueOf(embarazadas) + "%");
			porcientoEmbarazadas.setValue((int) embarazadas);
		} else {
			porcientoEmbarazadas.setVisible(false);
		}
	}
	
	@Override
	public void show() {
		super.show();
		actualizarDatos();
	}
}
