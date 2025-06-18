package frontend;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import componentesPropios.TablaPersonalizada;
import entidades.HistoriaClinica;
import entidades.Visita;

public class FormularioHistoriaClinica extends JDialog implements ConstantesFrontend {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormularioHistoriaClinica dialog = new FormularioHistoriaClinica(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioHistoriaClinica(Window window, HistoriaClinica hc) {
		super(window, "Historia Clínica", ModalityType.APPLICATION_MODAL);
		setLocationRelativeTo(window);
		setBackground(COLOR_GRIS_CLARO);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HistoriaClinica.class.getResource("/fotos/Logo peque.png")));
		setFont(new Font("Arial", Font.PLAIN, 16));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 650);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		initComponents(hc);
	}
	
	public void initComponents(HistoriaClinica hc) {
		
		// General
		
		JLabel cartelInformacionGeneral = new JLabel("Informaci\u00F3n general");
		cartelInformacionGeneral.setOpaque(true);
		cartelInformacionGeneral.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacionGeneral.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacionGeneral.setBorder(BORDE_PEQUENO);
		cartelInformacionGeneral.setBackground(Color.WHITE);
		cartelInformacionGeneral.setBounds(50, 30, 232, 26);
		getContentPane().add(cartelInformacionGeneral);
		
		JPanel panelInfoGeneral = new JPanel();
		panelInfoGeneral.setBorder(BORDE_GRANDE);
		panelInfoGeneral.setBackground(Color.WHITE);
		panelInfoGeneral.setBounds(35, 44, 550, 89);
		getContentPane().add(panelInfoGeneral);
		panelInfoGeneral.setLayout(null);
		
		JLabel cartelIDHistoria = new JLabel("Historia Cl\u00EDnica #");
		cartelIDHistoria.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelIDHistoria.setBounds(26, 38, 178, 19);
		panelInfoGeneral.add(cartelIDHistoria);
		
		JLabel cartelCantVisitas = new JLabel("Visitas: ");
		cartelCantVisitas.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelCantVisitas.setBounds(216, 38, 116, 19);
		panelInfoGeneral.add(cartelCantVisitas);
		
		JLabel cartelCantAnalisis = new JLabel("An\u00E1lisis orientados: ");
		cartelCantAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelCantAnalisis.setBounds(344, 38, 178, 19);
		panelInfoGeneral.add(cartelCantAnalisis);
		
		// Visitas
		
		JLabel cartelVisitas = new JLabel("Visitas realizadas");
		cartelVisitas.setOpaque(true);
		cartelVisitas.setHorizontalAlignment(SwingConstants.CENTER);
		cartelVisitas.setFont(new Font("Arial", Font.BOLD, 18));
		cartelVisitas.setBorder(BORDE_PEQUENO);
		cartelVisitas.setBackground(Color.WHITE);
		cartelVisitas.setBounds(50, 145, 232, 26);
		getContentPane().add(cartelVisitas);
		
		JPanel panelVisitas = new JPanel();
		panelVisitas.setBorder(BORDE_GRANDE);
		panelVisitas.setBounds(35, 160, 550, 185);
		getContentPane().add(panelVisitas);
		panelVisitas.setBackground(Color.WHITE);
		panelVisitas.setLayout(null);
		
		VisitaTableModel modelo = new VisitaTableModel(new ArrayList<Visita>());
		modelo.setMostrarFecha(true);
		modelo.setMostrarHistoriaClinica(false);
		JTable tabla = TablaPersonalizada.crearTablaPersonalizada(modelo);
		JScrollPane scroll = TablaPersonalizada.envolverEnScroll(tabla, 20, 20, 510, 150);
		panelVisitas.add(scroll);
		
		// Analisis
		
		JLabel cartelAnalisis = new JLabel("An\u00E1lisis orientados");
		cartelAnalisis.setOpaque(true);
		cartelAnalisis.setHorizontalAlignment(SwingConstants.CENTER);
		cartelAnalisis.setFont(new Font("Arial", Font.BOLD, 18));
		cartelAnalisis.setBorder(BORDE_PEQUENO);
		cartelAnalisis.setBackground(Color.WHITE);
		cartelAnalisis.setBounds(50, 355, 232, 26);
		getContentPane().add(cartelAnalisis);
		
		JPanel panelAnalisis = new JPanel();
		panelAnalisis.setBorder(BORDE_GRANDE);
		panelAnalisis.setBounds(35, 370, 550, 185);
		getContentPane().add(panelAnalisis);
		panelAnalisis.setBackground(Color.WHITE);
		panelAnalisis.setLayout(null);
		
		// Fondo
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBackground(COLOR_VERDE);
		panelVerde.setBounds(0, 0, 619, 89);
		getContentPane().add(panelVerde);
		panelVerde.setLayout(null);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(COLOR_AZUL);
		panelAzul.setBounds(0, 500, 619, 115);
		getContentPane().add(panelAzul);
		panelAzul.setLayout(null);
		
		JPanel panelGris = new JPanel();
		panelGris.setBounds(0, 96, 620, 395);
		getContentPane().add(panelGris);
		panelGris.setLayout(null);
	}
}
