package frontend;
import javafx.scene.Cursor;

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
import javax.swing.JOptionPane;
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

import componentesPropios.CustomDialog;

import java.awt.event.MouseAdapter;

public class VentanaPacientes extends JPanel{

	private CMF cmf;
	private JTable table;
	private PersonaTableModel model;
	
	public VentanaPacientes(CMF cmf) {
		this.cmf = cmf;
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
        model = new PersonaTableModel(this.cmf.getPacientes()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer la tabla no editable
            }
        };
        
        // Crear la tabla
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(10); 
        
        // Habilitar el ordenamiento por columnas
        final TableRowSorter<PersonaTableModel> sorter = new TableRowSorter<PersonaTableModel>(model);
        table.setRowSorter(sorter);
        
        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 30, 630, 406);
        
        // Configurar el tamaño preferido de la tabla
        table.setPreferredScrollableViewportSize(new Dimension(350, 150));
        
        // Campo de texto para filtrar
        final JTextField filterText = new JTextField();
        filterText.setFont(new Font("Arial", Font.PLAIN, 16));
        filterText.setBounds(0, 0, 630, 22);
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
        panelTabla.setBounds(80, 130, 630, 439);
        panelTabla.setLayout(null);
        panelTabla.add(filterText);
        panelTabla.add(scrollPane);
        
        // Personalización de la tabla
        customizeTable(table);
        
        // Agregar el panel al marco
        add(panelTabla);
        
        JButton botonAgregarPaciente = new JButton("AGREGAR PACIENTE");
        botonAgregarPaciente.setForeground(Color.BLACK);
        botonAgregarPaciente.setFont(new Font("Arial", Font.PLAIN, 16));
        botonAgregarPaciente.setBackground(SystemColor.menu);
        botonAgregarPaciente.setBounds(507, 585, 203, 33);
        add(botonAgregarPaciente);
        
        JLabel cartelListadoPacientes = new JLabel("Listado de pacientes:");
        cartelListadoPacientes.setFont(new Font("Arial", Font.BOLD, 18));
        cartelListadoPacientes.setBounds(80, 104, 203, 20);
        add(cartelListadoPacientes);
        
        JLabel botonEliminar = new JLabel("");
        botonEliminar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		borrarSeleccion();
        	}
        });
        botonEliminar.setToolTipText("Eliminar selecci\u00F3n");
        botonEliminar.setIcon(new ImageIcon(VentanaPacientes.class.getResource("/fotos/trash.png")));
        botonEliminar.setBounds(462, 585, 33, 33);
        botonEliminar.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        add(botonEliminar);
		
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
	
	public void borrarSeleccion() {
	    int[] selectedRows = table.getSelectedRows();

	    if (selectedRows.length > 0) {
	        // Confirmación personalizada
	        CustomDialog confirmDialog = new CustomDialog(
	            null,
	            "Confirmar eliminación",
	            "¿Está seguro de que desea eliminar la(s) fila(s) seleccionada(s)?",
	            true
	        );
	        confirmDialog.setVisible(true);

	        if (confirmDialog.esConfirmado()) {
	            for (int i = selectedRows.length - 1; i >= 0; i--) {
	                int row = selectedRows[i];
	                int id = (int) model.getValueAt(row, 3);
	                model.eliminarPacientePorId(id);
	            }
	            
	            new CustomDialog(
	                null,
	                "Eliminación exitosa",
	                "La selección fue eliminada correctamente.",
	                false
	            ).setVisible(true);
	        } else {
	            new CustomDialog(
	                null,
	                "Cancelado",
	                "La eliminación fue cancelada.",
	                false
	            ).setVisible(true);
	        }
	    } else {
	        new CustomDialog(
	            null,
	            "Advertencia",
	            "No se ha seleccionado ninguna fila.",
	            false
	        ).setVisible(true);
	    }
	}


}