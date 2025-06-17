package frontend;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import entidades.Analisis;
import entidades.CMF;
import entidades.Paciente;
import entidades.Visita;
import frontend.FormularioPaciente.ModoFormulario;

import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.ImageIcon;

import componentesPropios.CopyDialog;
import componentesPropios.InfoDialog;
import componentesPropios.QuestionDialog;
import componentesPropios.TablaPersonalizada;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class VentanaAnalisis extends JPanel implements ConstantesFrontend {

	private CMF cmf;
	private JTable table;
	private VisitaTableModel model;

	public VentanaAnalisis() {
		this.cmf = CMF.getInstance();
		model = new VisitaTableModel(cmf.obtenerListaVisitas());
		initComponents();
	}

	private void abrirFormulario(Visita visita) {
		Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
		FormularioAnalisis formularioAnalisis = new FormularioAnalisis(ventanaPrincipal, visita);
		formularioAnalisis.setLocationRelativeTo(ventanaPrincipal);
		formularioAnalisis.setVisible(true);

		// Actualizar la tabla después de guardar o editar un análisis
		model.setVisitas(filtrarVisitasSinResultados());
		model.fireTableDataChanged(); // Redibujar la tabla
	}

	private ArrayList<Visita> filtrarVisitasSinResultados() {
		ArrayList<Visita> todasLasVisitas = cmf.obtenerListaVisitas();
		ArrayList<Visita> visitasFiltradas = new ArrayList<>();

		for (Visita visita : todasLasVisitas) {
			if (visita.getAnalisis() != null && visita.getAnalisis().getResultados() == null) {
				visitasFiltradas.add(visita);
			}
		}

		return visitasFiltradas;
	}

	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(null);

		// Banner superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 796, 51);
		panelSuperior.setBackground(COLOR_AZUL);
		panelSuperior.setLayout(null);
		add(panelSuperior);

		JLabel cartelPestanna = new JLabel("ANÁLISIS");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 150, 51);
		panelSuperior.add(cartelPestanna);

		table = TablaPersonalizada.crearTablaPersonalizada(model);
		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 630, 406);

		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(80, 141, 630, 436);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setLayout(null);
		panelTabla.add(scrollPane);

		add(panelTabla);

		JLabel cartelListadoVisitas = new JLabel("Listado de análisis pendientes:");
		cartelListadoVisitas.setFont(new Font("Arial", Font.BOLD, 18));
		cartelListadoVisitas.setBounds(80, 104, 300, 20);
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
						abrirFormulario(visita); // Open FormularioAnalisis
					}
				}
			}
		});
	}

}
