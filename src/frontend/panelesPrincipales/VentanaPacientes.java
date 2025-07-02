package frontend.panelesPrincipales;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import util.ConstantesFrontend;
import entidades.CMF;
import entidades.personal.Paciente;
import frontend.formularios.FormularioPaciente;
import frontend.formularios.FormularioPaciente.ModoFormulario;
import frontend.tablas.PacienteTableModel;
import frontend.ui.TablaPersonalizada;
import frontend.ui.botones.BotonBlanco;
import frontend.ui.botones.ImageButtonLabel;
import frontend.ui.dialogs.InfoDialog;
import frontend.ui.dialogs.InfoDialog.Estado;
import frontend.ui.dialogs.QuestionDialog;
import frontend.ui.placeholders.BuscadorTabla;

public class VentanaPacientes extends JPanel implements ConstantesFrontend {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CMF cmf;
    private JTable table;
    private PacienteTableModel model;
    private TableRowSorter<PacienteTableModel> sorter;
    private BotonBlanco botonAgregarPaciente;
    private JLabel botonEliminar;

    public VentanaPacientes() {
        this.cmf = CMF.getInstance();
        initComponents();
    }

    public void borrarSeleccion() {
        boolean seElimino = false;

        int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            QuestionDialog confirmDialog = new QuestionDialog(null, "Confirmar eliminación",
                    "¿Está seguro de que desea eliminar la(s) fila(s) seleccionada(s)?");
            confirmDialog.setVisible(true);

            if (confirmDialog.esConfirmado()) {
                List<Integer> idsAEliminar = new ArrayList<>();

                // PRIMER PASO: recoger todos los IDs sin eliminar aún
                for (int i = 0; i < selectedRows.length; i++) {
                    int viewRow = selectedRows[i];
                    if (viewRow >= 0 && viewRow < table.getRowCount()) {
                        int modelRow = table.convertRowIndexToModel(viewRow);
                        int id = (int) model.getValueAt(modelRow, model.findColumn("H. Clínica"));
                        idsAEliminar.add(id);
                    }
                }

                // eliminar todos los pacientes
                for (Integer id : idsAEliminar) {
                    cmf.eliminarPaciente(id);
                }

                // recargar el modelo
                model.setPacientes(cmf.getPacientes());

                seElimino = true;
            } else {
                new InfoDialog(null, "Cancelado", "La eliminación fue cancelada.", Estado.INFORMACION).setVisible(true);
            }
        } else {
            new InfoDialog(null, "Advertencia", "No se ha seleccionado ninguna fila.", Estado.WARNING).setVisible(true);
        }

        if (seElimino) {
            new InfoDialog(null, "Eliminación exitosa", "La selección fue eliminada correctamente.", Estado.EXITO).setVisible(true);
        }
    }

    private void abrirFormulario(Paciente paciente) {
        Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
        FormularioPaciente formulario = new FormularioPaciente(ventanaPrincipal, paciente,
                ModoFormulario.VISUALIZACION);
        formulario.setLocationRelativeTo(ventanaPrincipal);
        formulario.setVisible(true);
    }

    private void abrirFormulario() {
        Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
        FormularioPaciente formulario = new FormularioPaciente(ventanaPrincipal);
        formulario.setLocationRelativeTo(ventanaPrincipal);
        formulario.setVisible(true);
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(null);
        setBounds(305, 0, 796, 673);

        JPanel panelSuperior = new JPanel(null);
        panelSuperior.setBounds(0, 0, 874, 51);
        panelSuperior.setBackground(COLOR_AZUL);
        add(panelSuperior);

        JLabel cartelPestanna = new JLabel("PACIENTES");
        cartelPestanna.setBounds(25, 0, 107, 51);
        cartelPestanna.setForeground(Color.WHITE);
        cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
        panelSuperior.add(cartelPestanna);

        model = new PacienteTableModel(cmf.getPacientes()) {
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
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);

        JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 700, 405);

        JPanel panelTabla = new JPanel(null);
        panelTabla.setBounds(50, 140, 700, 435);
        panelTabla.setBackground(Color.WHITE);
        panelTabla.add(scrollPane);
        add(panelTabla);

        BuscadorTabla buscador = new BuscadorTabla(sorter, "Buscar en la tabla...");
        buscador.setBounds(0, 0, 700, 25);
        panelTabla.add(buscador);

        JLabel cartelListadoPacientes = new JLabel("Listado de pacientes:");
        cartelListadoPacientes.setFont(new Font("Arial", Font.BOLD, 18));
        cartelListadoPacientes.setBounds(50, 105, 203, 20);
        add(cartelListadoPacientes);

        botonAgregarPaciente = new BotonBlanco("AGREGAR PACIENTE");
        botonAgregarPaciente.setBounds(547, 582, 203, 33);
        botonAgregarPaciente.setFont(new Font("Arial", Font.PLAIN, 16));
        botonAgregarPaciente.setForeground(Color.BLACK);
        botonAgregarPaciente.setBackground(SystemColor.menu);
        botonAgregarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario();
            }
        });
        add(botonAgregarPaciente);

        botonEliminar = new ImageButtonLabel(new ImageIcon(VentanaPacientes.class.getResource("/fotos/trash.png")));
        botonEliminar.setBounds(502, 582, 33, 33);
        botonEliminar.setToolTipText("Eliminar selección");
        botonEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrarSeleccion();
            }
        });
        add(botonEliminar);

        String rolUsuario = cmf.getUsuario().getRole().toString();
        boolean esMedico = "MÉDICO".equalsIgnoreCase(rolUsuario);

        botonAgregarPaciente.setVisible(esMedico);
        botonEliminar.setVisible(esMedico);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int viewRow = table.getSelectedRow();
                    if (viewRow != -1) {
                        int modelRow = table.convertRowIndexToModel(viewRow);
                        int id = (int) model.getValueAt(modelRow, model.findColumn("H. Clínica"));
                        Paciente paciente = cmf.getPacientePorId(id);
                        abrirFormulario(paciente);
                    }
                }
            }
        });
    }
}
