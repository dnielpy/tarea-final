package frontend;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class VentanaPacientes extends JPanel implements MouseListener{

	private JTable table;
	
	public VentanaPacientes(CMF cmf) {
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(305, 0, 796, 673);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 874, 51);
		add(panelSuperior);
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
        scrollPane.setBounds(0, 30, 600, 406);
        
        // Configurar el tamaño preferido de la tabla
        table.setPreferredScrollableViewportSize(new Dimension(350, 150));
        
        // Campo de texto para filtrar
        final JTextField filterText = new JTextField();
        filterText.setFont(new Font("Arial", Font.PLAIN, 16));
        filterText.setBounds(0, 0, 600, 22);
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
        JPanel panelTabla = new JPanel();
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBounds(100, 130, 600, 439);
        panelTabla.setLayout(null);
        panelTabla.add(filterText);
        panelTabla.add(scrollPane);
        
        // Personalización de la tabla
        customizeTable(table);
        
        // Agregar el panel al marco
        add(panelTabla);
        
        JButton btnNewButton = new JButton("AGREGAR PACIENTE");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
        btnNewButton.setBackground(SystemColor.menu);
        btnNewButton.setBounds(497, 585, 203, 33);
        add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("Listado de pacientes:");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(100, 104, 203, 20);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(VentanaPacientes.class.getResource("/fotos/trash.png")));
        lblNewLabel_1.setBounds(449, 585, 33, 33);
        add(lblNewLabel_1);
		
	}
	
	private static void customizeTable(JTable table) {
        // Cambiar color y fuente de las celdas
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Arial", Font.PLAIN, 16));

        // Cambiar color y fuente de los encabezados
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 16));

        // Cambiar color de las líneas de la tabla
        table.setGridColor(SystemColor.controlHighlight);
        
        // Establecer el borde de las celdas (opcional)
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createLineBorder(SystemColor.controlHighlight));
        table.setDefaultRenderer(Object.class, renderer);

        // Cambiar altura de las filas
        table.setRowHeight(30); // Cambia 30 por la altura deseada
        
        // Alternar filas de colores (opcional)
        table.setFillsViewportHeight(true);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
