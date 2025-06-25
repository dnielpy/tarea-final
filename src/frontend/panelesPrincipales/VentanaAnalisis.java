package frontend.panelesPrincipales;

import java.awt.Color;
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

import util.ConstantesFrontend;
import entidades.CMF;
import entidades.registros.Analisis;
import frontend.formularios.FormularioAnalisis;
import frontend.formularios.FormularioAnalisis.ModoFormulario;
import frontend.tablas.AnalisisTableModel;
import frontend.ui.BuscadorTabla;
import frontend.ui.TablaPersonalizada;

import java.awt.event.MouseAdapter;
import java.util.List;

public class VentanaAnalisis extends JPanel implements ConstantesFrontend {

    private CMF cmf;
    private JTable table;
    private AnalisisTableModel model;
    private TableRowSorter<AnalisisTableModel> sorter; 

    public VentanaAnalisis() {
        this.cmf = CMF.getInstance();
        List<Analisis> analisisPendientes = filtrarAnalisisPendientes();
        model = new AnalisisTableModel(analisisPendientes);
        model.setMostrarResultados(false);
        initComponents();
    }

    private List<Analisis> filtrarAnalisisPendientes() {
        return cmf.obtenerAnalisisPendientesDeResultado();
    }

    private void abrirFormulario(Analisis analisis) {
        Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);

        FormularioAnalisis formularioAnalisis = new FormularioAnalisis(ventanaPrincipal, analisis, ModoFormulario.VISUALIZACION);
        formularioAnalisis.setLocationRelativeTo(ventanaPrincipal);
        formularioAnalisis.setVisible(true);

        actualizarDatos();
    }

    private void initComponents() {
        setBounds(0, 0, 796, 673);
        setBackground(Color.WHITE);
        setLayout(null);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBounds(0, 0, 796, 51);
        panelSuperior.setBackground(COLOR_AZUL);
        panelSuperior.setLayout(null);
        add(panelSuperior);

        JLabel cartelPestanna = new JLabel("ANÁLISIS");
        cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
        cartelPestanna.setForeground(Color.WHITE);
        cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
        cartelPestanna.setBounds(25, 0, 150, 51);
        panelSuperior.add(cartelPestanna);

        // Crear sorter y asociarlo al modelo y tabla
        sorter = new TableRowSorter<>(model);

        table = TablaPersonalizada.crearTablaPersonalizada(model);
        table.setRowSorter(sorter); 

        JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 700, 405);

        JPanel panelTabla = new JPanel();
        panelTabla.setBounds(50, 140, 700, 435);
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setLayout(null);
        panelTabla.add(scrollPane);

        add(panelTabla);

        BuscadorTabla buscador = new BuscadorTabla(sorter, "Buscar en análisis...");
        buscador.setBounds(0, 0, 700, 25);
        panelTabla.add(buscador);

        JLabel cartelListadoVisitas = new JLabel("Listado de análisis pendientes:");
        cartelListadoVisitas.setFont(new Font("Arial", Font.BOLD, 18));
        cartelListadoVisitas.setBounds(50, 105, 300, 20);
        add(cartelListadoVisitas);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int viewRow = table.getSelectedRow();
                    if (viewRow != -1) {
                        int modelRow = table.convertRowIndexToModel(viewRow);
                        Analisis analisis = model.getAnalisisAt(modelRow);
                        if (analisis != null) {
                            abrirFormulario(analisis);
                        }
                    }
                }
            }
        });
    }

    private void actualizarDatos() {
        List<Analisis> actualizados = filtrarAnalisisPendientes();
        model.setAnalisisList(actualizados);
        model.fireTableDataChanged();
    }

    @Override
    public void show() {
        super.show();
        actualizarDatos();
    }
}
