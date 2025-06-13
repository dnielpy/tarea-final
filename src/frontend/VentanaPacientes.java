package frontend;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

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

import entidades.CMF;
import entidades.Paciente;

import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.ImageIcon;

import componentesPropios.InfoDialog;
import componentesPropios.QuestionDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPacientes extends JPanel implements ConstantesFrontend {

    private CMF cmf;
    private JTable table;
    private PacienteTableModel model;

    public VentanaPacientes() {
        this.cmf = CMF.getInstance();
        initComponents();
    }

    private static void customizeTable(JTable table) {
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        table.setGridColor(SystemColor.controlHighlight);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BORDE_COMPONENTE);
        table.setDefaultRenderer(Object.class, renderer);
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
    }

    public void borrarSeleccion() {
        int[] selectedRows = table.getSelectedRows();

        if (selectedRows.length > 0) {
            QuestionDialog confirmDialog = new QuestionDialog(
                    null,
                    "Confirmar eliminación",
                    "¿Está seguro de que desea eliminar la(s) fila(s) seleccionada(s)?"
            );
            confirmDialog.setVisible(true);

            if (confirmDialog.esConfirmado()) {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int viewRow = selectedRows[i];
                    int modelRow = table.convertRowIndexToModel(viewRow);
                    int id = (int) model.getValueAt(modelRow, model.findColumn("H. Clínica"));
                    model.eliminarPacientePorId(id, modelRow);
                }

                new InfoDialog(
                        null,
                        "Eliminación exitosa",
                        "La selección fue eliminada correctamente."
                ).setVisible(true);
            } else {
                new InfoDialog(
                        null,
                        "Cancelado",
                        "La eliminación fue cancelada."
                ).setVisible(true);
            }
        } else {
            new InfoDialog(
                    null,
                    "Advertencia",
                    "No se ha seleccionado ninguna fila."
            ).setVisible(true);
        }
    }

    private void abrirFormulario(Paciente paciente) {
        Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
        FormularioPaciente formularioPaciente = new FormularioPaciente(ventanaPrincipal, paciente, false);
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

        model = new PacienteTableModel(cmf.getPacientes()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);

        TableRowSorter<PacienteTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 30, 630, 406);

        JPanel panelTabla = new JPanel();
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBounds(80, 130, 630, 439);
        panelTabla.setLayout(null);
        panelTabla.add(scrollPane);

        customizeTable(table);
        add(panelTabla);

        JButton botonAgregarPaciente = new JButton("AGREGAR PACIENTE");
        botonAgregarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                abrirFormulario();
            }
        });
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
        botonEliminar.setToolTipText("Eliminar selección");
        botonEliminar.setIcon(new ImageIcon(VentanaPacientes.class.getResource("/fotos/trash.png")));
        botonEliminar.setBounds(462, 585, 33, 33);
        botonEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(botonEliminar);

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
}