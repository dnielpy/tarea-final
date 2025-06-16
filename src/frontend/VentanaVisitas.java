package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import org.w3c.dom.events.MouseEvent;

import componentesPropios.InfoDialog;
import componentesPropios.QuestionDialog;
import componentesPropios.TablaPersonalizada;
import entidades.Analisis;
import entidades.CMF;
import entidades.Paciente;
import entidades.Visita;
import frontend.FormularioPaciente.ModoFormulario;

public class VentanaVisitas extends JPanel implements ConstantesFrontend {

	private CMF cmf;
	private JTable table;
	private VisitaTableModel model;

	public VentanaVisitas() {
		this.cmf = CMF.getInstance();
		initComponents();
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

		// Create the table model
		model = new VisitaTableModel(cmf.obtenerListaVisitas());

		// Create the table
		table = TablaPersonalizada.crearTablaPersonalizada(model);

		// Configure columns
		table.getColumnModel().getColumn(0).setHeaderValue("Historia Clinica");
		table.getColumnModel().getColumn(1).setHeaderValue("Fecha");
		table.getColumnModel().getColumn(2).setHeaderValue("Analisis");
		table.getColumnModel().getColumn(3).setHeaderValue("ID");

		// Adjust column widths
		table.getColumnModel().getColumn(0).setPreferredWidth(150); // Fecha
		table.getColumnModel().getColumn(1).setPreferredWidth(200); // Paciente
		table.getColumnModel().getColumn(2).setPreferredWidth(300); // Motivo
		table.getColumnModel().getColumn(3).setPreferredWidth(50); // ID

		// Add sorting functionality
		TableRowSorter<VisitaTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		// Wrap table in a scroll pane
		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 630, 406);

		// Panel container
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(80, 141, 630, 436);
		add(panelTabla);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setLayout(null);
		panelTabla.add(scrollPane);

		// Add to main container
		add(panelTabla);

		JLabel cartelListadoVisitas = new JLabel("Listado de visitas:");
		cartelListadoVisitas.setFont(new Font("Arial", Font.BOLD, 18));
		cartelListadoVisitas.setBounds(80, 104, 203, 20);
		add(cartelListadoVisitas);

	}
}
