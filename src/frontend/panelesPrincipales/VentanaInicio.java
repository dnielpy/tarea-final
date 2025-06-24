package frontend.panelesPrincipales;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import util.ConstantesFrontend;
import frontend.ui.PanelResumen;
import entidades.CMF;

public class VentanaInicio extends JPanel implements ConstantesFrontend {
	
	private CMF cmf;
	private PanelResumen panelResumenPacientes;
	private PanelResumen panelResumenEnRiesgo;
	private PanelResumen panelResumenEmbarazadas;
	private PanelResumen panelResumenVisitasDelDia;

	public VentanaInicio() {
		cmf = CMF.getInstance();
		
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 795, 675);
		
		// Barra superior
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		add(panelSuperior);
		panelSuperior.setBackground(COLOR_AZUL);
		panelSuperior.setLayout(null);
		
		JLabel cartelPestanna = new JLabel("INICIO");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);
		
		// Cuadro de cantidad de pacientes

        panelResumenPacientes = new PanelResumen("PACIENTES", false, 
        		new ImageIcon(VentanaInicio.class.getResource("/fotos/paciente.png")));
        panelResumenPacientes.setSize(310, 205);
        panelResumenPacientes.setLocation(50, 115);
        add(panelResumenPacientes);
		
		// Cuadro de cantidad de visitas del día
		
		panelResumenVisitasDelDia = new PanelResumen("VISITAS DEL D\u00CDA", false,
				new ImageIcon(VentanaInicio.class.getResource("/fotos/visitas.png")));
		panelResumenVisitasDelDia.setSize(310, 205);
		panelResumenVisitasDelDia.setLocation(420, 115);
		add(panelResumenVisitasDelDia);
		
		// Cuadro de cantidad de pacientes en riesgo
		
		panelResumenEnRiesgo = new PanelResumen("EN RIESGO", true, 
				new ImageIcon(VentanaInicio.class.getResource("/fotos/riesgo.png")));
		panelResumenEnRiesgo.setSize(310, 205);
		panelResumenEnRiesgo.setLocation(420, 380);
		add(panelResumenEnRiesgo);
		panelResumenEnRiesgo.setLayout(null);

		// Cuadro de cantidad de embarazadas
		
		panelResumenEmbarazadas = new PanelResumen("EMBARAZADAS", true, 
				new ImageIcon(VentanaInicio.class.getResource("/fotos/embarazada.png")));
		panelResumenEmbarazadas.setSize(310, 205);
		panelResumenEmbarazadas.setLocation(50, 380);
		add(panelResumenEmbarazadas);

		actualizarDatos();
	}
	
	public void actualizarDatos() {
		panelResumenPacientes.actualizarDatos(String.valueOf(cmf.obtenerTotalPacientes()));
		panelResumenEnRiesgo.actualizarDatos(String.valueOf(cmf.obtenerPacientesEnRiesgo()), 
				cmf.porcentajeEnRiesgoDelTotal());
		
		String textoEmbarazadas = String.valueOf(cmf.obtenerCantidadDeEmbarazadas() + 
				" de " + String.valueOf(cmf.obtenerCantidadMujeres()));
		panelResumenEmbarazadas.actualizarDatos(textoEmbarazadas, cmf.porcentajeEmbarazadasRespectoAMujeres());
		panelResumenVisitasDelDia.actualizarDatos(String.valueOf(cmf.obtenerCantidadVisitasPorFecha(LocalDate.now())));
	}
	
	@Override
	public void show() {
		super.show();
		actualizarDatos();
	}
}
