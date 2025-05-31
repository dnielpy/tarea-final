package frontend;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableRowSorter;

import entidades.CMF;

public class VentanaPacientes extends JPanel {

	private JTable table;
	
	public VentanaPacientes(CMF cmf) {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(0, 0, 832, 689);
		add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		panelPrincipal.add(panelSuperior);
		panelSuperior.setBackground(new Color(0, 171, 227));
		panelSuperior.setLayout(null);
		
		JLabel cartelPestanna = new JLabel("PACIENTES");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 107, 51);
		panelSuperior.add(cartelPestanna);
		

		// Crear el modelo de tabla
        PersonaTableModel model = new PersonaTableModel(cmf.getPacientes()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        // Crear la tabla
        table = new JTable(model);
        
        // Habilitar el ordenamiento por columnas
        final TableRowSorter<PersonaTableModel> sorter = new TableRowSorter<PersonaTableModel>(model);
        table.setRowSorter(sorter);
        
        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 30, 322, 200);
        
        // Configurar el tamaño preferido de la tabla
        table.setPreferredScrollableViewportSize(new Dimension(350, 150));
        
        // Campo de texto para filtrar
        final JTextField filterText = new JTextField();
        filterText.setBounds(0, 0, 322, 22);
        filterText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = filterText.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null); // Sin filtro
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text)); // Filtro con expresión regular (case insensitive)
                }
            }
        });
        
        // Panel para el filtro y la tabla
        JPanel panel = new JPanel();
        panel.setBounds(188, 130, 322, 200);
        panel.setLayout(null);
        panel.add(filterText);
        panel.add(scrollPane);
        
        // Personalización de la tabla
        customizeTable(table);
        
        // Agregar el panel al marco
        add(panel);
		
	}
	
	private static void customizeTable(JTable table) {
        // Cambiar color y fuente de las celdas
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        // Cambiar color y fuente de los encabezados
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Cambiar color de las líneas de la tabla
        table.setGridColor(Color.LIGHT_GRAY);
        
        // Establecer el borde de las celdas (opcional)
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        table.setDefaultRenderer(Object.class, renderer);

        // Cambiar altura de las filas
        table.setRowHeight(30); // Cambia 30 por la altura deseada
        
        // Alternar filas de colores (opcional)
        table.setFillsViewportHeight(true);
    }
}
