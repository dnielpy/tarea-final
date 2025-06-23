package frontend.formularios;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import frontend.ui.TablaPersonalizada;
import entidades.registros.Analisis;
import entidades.registros.Visita;
import entidades.registros.HistoriaClinica;
import frontend.ConstantesFrontend;
import frontend.tablas.AnalisisTableModel;
import frontend.tablas.VisitaTableModel;

public class FormularioHistoriaClinica extends JDialog implements ConstantesFrontend {

	private JLabel cartelIDHistoria;
	private JLabel cartelCantVisitas;
	private JLabel cartelCantAnalisis;
	private VisitaTableModel modeloVisitas;
	private AnalisisTableModel modeloAnalisis;
	private JTable tablaVisitas;
	private JTable tablaAnalisis;

	private HistoriaClinica hc;

	public FormularioHistoriaClinica(Window window, HistoriaClinica hc) {
		super(window, "Historia Clínica", ModalityType.APPLICATION_MODAL);
		this.hc = hc;
		setBackground(COLOR_GRIS_CLARO);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HistoriaClinica.class.getResource("/fotos/Logo peque.png")));
		setFont(new Font("Arial", Font.PLAIN, 16));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 650);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		initComponents();
		cargarDatos();
	}

	public void initComponents() {

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

		cartelIDHistoria = new JLabel("Historia Cl\u00EDnica #");
		cartelIDHistoria.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelIDHistoria.setBounds(26, 38, 178, 19);
		panelInfoGeneral.add(cartelIDHistoria);

		cartelCantVisitas = new JLabel("Visitas: ");
		cartelCantVisitas.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelCantVisitas.setBounds(216, 38, 116, 19);
		panelInfoGeneral.add(cartelCantVisitas);

		cartelCantAnalisis = new JLabel("An\u00E1lisis orientados: ");
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

		modeloVisitas = new VisitaTableModel(new ArrayList<Visita>());
		modeloVisitas.setMostrarFecha(true);
		modeloVisitas.setMostrarHistoriaClinica(false);
		tablaVisitas = TablaPersonalizada.crearTablaPersonalizada(modeloVisitas);
		JScrollPane scrollVisitas = TablaPersonalizada.envolverEnScroll(tablaVisitas, 20, 20, 510, 150);
		panelVisitas.add(scrollVisitas);

		// Análisis
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

		modeloAnalisis = new AnalisisTableModel(new ArrayList<Analisis>());
		modeloAnalisis.setMostrarResultados(false);
		modeloAnalisis.setMostrarFechaOrientado(true);
		modeloAnalisis.setMostrarEstado(true);
		modeloAnalisis.setMostrarFechaResultado(true);
		tablaAnalisis = TablaPersonalizada.crearTablaPersonalizada(modeloAnalisis);
		JScrollPane scrollAnalisis = TablaPersonalizada.envolverEnScroll(tablaAnalisis, 20, 20, 510, 150);
		panelAnalisis.add(scrollAnalisis);

		// Fondo
		JPanel panelVerde = new JPanel();
		panelVerde.setBackground(COLOR_VERDE);
		panelVerde.setBounds(0, 0, 619, 89);
		getContentPane().add(panelVerde);
		panelVerde.setLayout(null);

		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(COLOR_AZUL);
		panelAzul.setBounds(0, 500, 619, 139);
		getContentPane().add(panelAzul);
		panelAzul.setLayout(null);

		JPanel panelGris = new JPanel();
		panelGris.setBounds(0, 96, 620, 395);
		getContentPane().add(panelGris);
		panelGris.setLayout(null);
	}

	public void cargarDatos() {
		boolean datosValidos = (hc != null);

		List<Visita> visitas = new ArrayList<>();
		List<Analisis> analisis = new ArrayList<>();

		if (datosValidos) {
			if (hc.getRegistroVisitas() != null) {
				visitas = hc.getRegistroVisitas();
			}
			if (hc.getAnalisis() != null) {
				analisis = hc.getAnalisis();
			}
		}

		cartelIDHistoria.setText("Historia Clínica #" + (datosValidos ? hc.getId() : ""));
		cartelCantVisitas.setText("Visitas: " + visitas.size());
		cartelCantAnalisis.setText("Análisis orientados: " + analisis.size());

		if (modeloVisitas != null) {
			modeloVisitas.setVisitas(visitas);
			modeloVisitas.fireTableDataChanged();
		}

		if (modeloAnalisis != null) {
			modeloAnalisis.setAnalisisList(analisis);
			modeloAnalisis.fireTableDataChanged();
		}
	}
}
