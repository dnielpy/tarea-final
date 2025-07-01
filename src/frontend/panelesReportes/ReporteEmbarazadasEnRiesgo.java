package frontend.panelesReportes;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import entidades.CMF;
import frontend.tablas.PacienteTableModel;
import frontend.ui.TablaPersonalizada;
import frontend.ui.placeholders.BuscadorTabla;

public class ReporteEmbarazadasEnRiesgo extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CMF cmf;
    private JTable table;
    private PacienteTableModel model;
    private TableRowSorter<PacienteTableModel> sorter;

    public ReporteEmbarazadasEnRiesgo() {
        cmf = CMF.getInstance();

        setBounds(0, 0, 796, 578);
        setBackground(Color.WHITE);
        setLayout(null);

        model = new PacienteTableModel(cmf.obtenerEmbarazadasEnRiesgo()) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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

        // Filtro
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // ScrollPane con tabla
        JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 700, 405);

        // Panel contenedor para tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setBounds(50, 50, 700, 435); // bajamos un poco para dejar espacio al buscador
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setLayout(null);
        panelTabla.add(scrollPane);

        add(panelTabla);

        // Agregar buscador
        BuscadorTabla buscador = new BuscadorTabla(sorter, "Buscar en la tabla...");
        buscador.setBounds(0, 0, 700, 25);
        panelTabla.add(buscador);
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
