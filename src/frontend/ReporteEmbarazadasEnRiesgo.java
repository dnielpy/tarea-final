package frontend;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import componentesPropios.TablaPersonalizada;
import entidades.CMF;

public class ReporteEmbarazadasEnRiesgo extends JPanel {

	private CMF cmf;
	private JTable table;
	private PacienteTableModel model;

	public ReporteEmbarazadasEnRiesgo() {
		cmf = CMF.getInstance();

		setBounds(0, 0, 796, 578);
		setBackground(Color.WHITE);
		setLayout(null);
		
		model = new PacienteTableModel(cmf.obtenerEmbarazadasEnRiesgo()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = TablaPersonalizada.crearTablaPersonalizada(model);

		// Configuración de columnas (si quieres hacerla personalizada por tabla)
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);

		// Filtro (si aplica)
		TableRowSorter<PacienteTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		// ScrollPane con tabla
		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 630, 406);

		// Panel contenedor
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(79, 60, 630, 437);
		add(panelTabla);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setLayout(null);
		panelTabla.add(scrollPane);
		
	}
	
	public void actualizarDatos() {
	    model.setPacientes(cmf.obtenerEmbarazadasEnRiesgo());
	    model.fireTableDataChanged(); // Notifica que los datos han cambiado
	}
	
	@Override
	public void show() {
		super.show();
		actualizarDatos();
	}

}
