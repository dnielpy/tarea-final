package frontend.panelesPrincipales;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import entidades.CMF;
import entidades.registros.Visita;
import frontend.ConstantesFrontend;
import frontend.formularios.FormularioVisitas;
import frontend.tablas.VisitaTableModel;
import frontend.ui.TablaPersonalizada;

import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVisitas extends JPanel implements ConstantesFrontend {
	private CMF cmf;
	private JTable table;
	private VisitaTableModel model;

	public VentanaVisitas() {
		this.cmf = CMF.getInstance();
		model = new VisitaTableModel(cmf.obtenerListaVisitas());
		model.setMostrarFecha(false);
		initComponents();
	}

	private void abrirFormulario(Visita visita) {
		Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
		FormularioVisitas formularioVisitas = new FormularioVisitas(ventanaPrincipal, visita);
		formularioVisitas.setLocationRelativeTo(ventanaPrincipal);
		formularioVisitas.setVisible(true);

		// Actualizar la tabla después de guardar o editar una visita
		model.setVisitas(cmf.obtenerListaVisitas());
		model.fireTableDataChanged(); // Redibujar la tabla
	}

	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(305, 0, 796, 673);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		add(panelSuperior);
		panelSuperior.setBackground(COLOR_AZUL);
		panelSuperior.setLayout(null);

		JLabel cartelPestanna = new JLabel("VISITAS");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);

		table = TablaPersonalizada.crearTablaPersonalizada(model);
		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 630, 406);

		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(80, 141, 630, 436);
		add(panelTabla);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setLayout(null);
		panelTabla.add(scrollPane);

		JButton botonAgregarVisita = new JButton("AGREGAR VISITA");
		botonAgregarVisita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirFormulario(null); // Abrir formulario para agregar una nueva visita
			}
		});
		botonAgregarVisita.setForeground(Color.BLACK);
		botonAgregarVisita.setFont(new Font("Arial", Font.PLAIN, 16));
		botonAgregarVisita.setBackground(SystemColor.menu);
		botonAgregarVisita.setBounds(507, 585, 203, 33);
		add(botonAgregarVisita);

		JLabel cartelListadoVisitas = new JLabel("Listado de visitas de hoy:");
		cartelListadoVisitas.setFont(new Font("Arial", Font.BOLD, 18));
		cartelListadoVisitas.setBounds(80, 104, 241, 20);
		add(cartelListadoVisitas);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Double-click detected
					int viewRow = table.getSelectedRow();
					if (viewRow != -1) {
						int modelRow = table.convertRowIndexToModel(viewRow);
						int id = (int) model.getValueAt(modelRow, model.findColumn("Historia Clinica"));
						Visita visita = cmf.obtenerVisitaPorId(id);
						abrirFormulario(visita); // Abrir formulario para editar la visita
					}
				}
			}
		});
	}
}
