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
	private PacienteTableModel model;

	public VentanaAnalisis() {
		this.cmf = CMF.getInstance();
		initComponents();
	}

	public void borrarSeleccion() {
		int[] selectedRows = table.getSelectedRows();

		if (selectedRows.length == 0) {
			new InfoDialog(
					null,
					"Advertencia",
					"No se ha seleccionado ninguna fila.").setVisible(true);
		} else {
			QuestionDialog confirmDialog = new QuestionDialog(
					null,
					"Confirmar eliminación",
					"¿Está seguro de que desea eliminar la(s) fila(s) seleccionada(s)?");
			confirmDialog.setVisible(true);

			if (confirmDialog.esConfirmado()) {
				List<Integer> idsAEliminar = new ArrayList<>();

				for (int i = 0; i < selectedRows.length; i++) {
					int viewRow = selectedRows[i];
					int modelRow = table.convertRowIndexToModel(viewRow);
					int id = (int) model.getValueAt(modelRow, model.findColumn("H. Clínica"));
					idsAEliminar.add(id);
				}

				for (int i = 0; i < idsAEliminar.size(); i++) {
					model.eliminarPacientePorId(idsAEliminar.get(i));
				}

				new InfoDialog(
						null,
						"Eliminación exitosa",
						"La selección fue eliminada correctamente.").setVisible(true);
			} else {
				new InfoDialog(
						null,
						"Cancelado",
						"La eliminación fue cancelada.").setVisible(true);
			}
		}
	}

	private void abrirFormulario(Paciente paciente) {
		Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
		FormularioPaciente formularioPaciente = new FormularioPaciente(ventanaPrincipal, paciente,
				ModoFormulario.VISUALIZACION);
		formularioPaciente.setLocationRelativeTo(ventanaPrincipal);
		formularioPaciente.setVisible(true);
	}

	private void abrirFormulario() {
		Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
		FormularioPaciente formularioPaciente = new FormularioPaciente(ventanaPrincipal);
		formularioPaciente.setLocationRelativeTo(ventanaPrincipal);
		formularioPaciente.setVisible(true);
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

		JLabel cartelPestanna = new JLabel("PACIENTES");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);

		// Crear el modelo de la tabla con edición desactivada

		model = new PacienteTableModel(obtenerPacientesConAnalisisPendientes()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = TablaPersonalizada.crearTablaPersonalizada(model);

		// Configuración de columnas para mostrar "Nombre", "Edad", y "CI"
		table.getColumnModel().getColumn(0).setHeaderValue("Nombre");
		table.getColumnModel().getColumn(1).setHeaderValue("Edad");
		table.getColumnModel().getColumn(2).setHeaderValue("CI");

		// Ajustar el ancho de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(200); // Nombre
		table.getColumnModel().getColumn(1).setPreferredWidth(50); // Edad
		table.getColumnModel().getColumn(2).setPreferredWidth(100); // CI

		// Filtro (si aplica)
		TableRowSorter<PacienteTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		// ScrollPane con tabla
		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 630, 406);

		// Panel contenedor
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(80, 141, 630, 436);
		add(panelTabla);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setLayout(null);
		panelTabla.add(scrollPane);

		// Agregar a tu contenedor principal
		add(panelTabla);

		JLabel cartelListadoPacientes = new JLabel("Listado de pacientes:");
		cartelListadoPacientes.setFont(new Font("Arial", Font.BOLD, 18));
		cartelListadoPacientes.setBounds(80, 104, 203, 20);
		add(cartelListadoPacientes);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // Double-click detected
					int viewRow = table.getSelectedRow();
					if (viewRow != -1) {
						int modelRow = table.convertRowIndexToModel(viewRow);
						int id = (int) model.getValueAt(modelRow, model.findColumn("H. Clínica"));
						Paciente paciente = cmf.getPacientePorId(id); // Fetch patient data by ID
						abrirFormulario(paciente); // Open form with patient data
					}
				}
			}
		});
	}

	public ArrayList<Paciente> obtenerPacientesConAnalisisPendientes() {
		ArrayList<Paciente> pacientesConAnalisisPendientes = new ArrayList<>();
		for (Paciente paciente : cmf.getPacientes()) {
			ArrayList<Analisis> analisis = paciente.getHistoriaClinica().getAnalisis();
			for (Analisis a : analisis) {
				if (a.getResultados() == null) {
					pacientesConAnalisisPendientes.add(paciente);
				}
			}
		}
		return pacientesConAnalisisPendientes;
	}

}
