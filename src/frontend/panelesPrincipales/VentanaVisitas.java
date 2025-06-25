package frontend.panelesPrincipales;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import util.ConstantesFrontend;
import entidades.CMF;
import entidades.registros.Visita;
import frontend.formularios.FormularioVisitas;
import frontend.formularios.FormularioVisitas.ModoFormulario;
import frontend.tablas.VisitaTableModel;
import frontend.ui.TablaPersonalizada;
import frontend.ui.placeholders.BuscadorTabla;

public class VentanaVisitas extends JPanel implements ConstantesFrontend {
	private CMF cmf;
	private JTable table;
	private VisitaTableModel model;
	private TableRowSorter<VisitaTableModel> sorter;

	public VentanaVisitas() {
		this.cmf = CMF.getInstance();
		this.model = new VisitaTableModel(cmf.obtenerVisitasDeUnDia(LocalDate.now()));
		this.model.setMostrarFecha(false);
		initComponents();
	}

	private void abrirFormulario(FormularioVisitas formulario) {
		Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
		formulario.setLocationRelativeTo(ventanaPrincipal);
		formulario.setVisible(true);

		model.setVisitas(cmf.obtenerVisitasDeUnDia(LocalDate.now()));
		model.fireTableDataChanged();
	}

	private void abrirFormularioNuevaVisita() {
		abrirFormulario(new FormularioVisitas(SwingUtilities.getWindowAncestor(this)));
	}

	// Abre el formulario para una visita existente
	private void abrirFormularioVisitaExistente(Visita visita) {
		abrirFormulario(
				new FormularioVisitas(SwingUtilities.getWindowAncestor(this), visita, ModoFormulario.VISUALIZACION));
	}

	private void initComponents() {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(305, 0, 796, 673);

		add(crearPanelSuperior());
		add(crearPanelTabla());
		add(crearBotonAgregarVisita());
		add(crearCartelListadoVisitas());
	}

	private JPanel crearPanelSuperior() {
		JPanel panelSuperior = new JPanel(null);
		panelSuperior.setBounds(0, 0, 874, 51);
		panelSuperior.setBackground(COLOR_AZUL);

		JLabel cartelPestanna = new JLabel("VISITAS");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);

		return panelSuperior;
	}

	// Crea un panel de tabla con una barra de búsqueda y un panel de desplazamiento
	private JPanel crearPanelTabla() {
		table = TablaPersonalizada.crearTablaPersonalizada(model);
		sorter = new TableRowSorter<VisitaTableModel>(model);
		table.setRowSorter(sorter);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if (row != -1) {
						int modelRow = table.convertRowIndexToModel(row);
						int id = (int) model.getValueAt(modelRow, model.findColumn("ID Visita"));
						Visita visita = cmf.obtenerVisitaPorId(id);
						abrirFormularioVisitaExistente(visita);
					}
				}
			}
		});

		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 700, 405);

		JPanel panelTabla = new JPanel(null);
		panelTabla.setBounds(50, 140, 700, 435);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.add(scrollPane);
		panelTabla.add(crearBuscadorTabla());

		return panelTabla;
	}

	// Crea un cartel para el listado de visitas
	private JLabel crearCartelListadoVisitas() {
		JLabel cartel = new JLabel("Listado de visitas de hoy:");
		cartel.setFont(new Font("Arial", Font.BOLD, 18));
		cartel.setBounds(50, 105, 241, 20);
		return cartel;
	}

	// Crea un campo de texto para buscar en la tabla
	private JTextField crearBuscadorTabla() {
		BuscadorTabla buscador = new BuscadorTabla(sorter, "Buscar en la tabla...");
		buscador.setBounds(0, 0, 700, 25);
		return buscador;
	}

	private JButton crearBotonAgregarVisita() {
		JButton boton = new JButton("AGREGAR VISITA");
		boton.setBounds(547, 581, 203, 33); // ajustado por el desplazamiento del panelTabla
		boton.setFont(new Font("Arial", Font.PLAIN, 16));
		boton.setForeground(Color.BLACK);
		boton.setBackground(SystemColor.menu);

		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioNuevaVisita();
			}
		});

		return boton;
	}

	// Actualiza los datos de la tabla
	public void actualizarDatos() {
		model.setVisitas(cmf.obtenerVisitasDeUnDia(LocalDate.now()));
		model.fireTableDataChanged(); 
	}

	@Override
	public void show() {
		super.show();
		actualizarDatos();
	}
}