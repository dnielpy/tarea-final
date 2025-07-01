package frontend.panelesPrincipales;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import util.ConstantesFrontend;
import util.UtilFecha;

import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;

import entidades.CMF;
import entidades.registros.HojaCargosDiaria;
import entidades.registros.Visita;
import frontend.formularios.FormularioVisitas;
import frontend.formularios.FormularioVisitas.ModoFormulario;
import frontend.tablas.VisitaTableModel;
import frontend.ui.TablaPersonalizada;
import frontend.ui.placeholders.BuscadorTabla;

public class VentanaHojasDeCargo extends JPanel implements ConstantesFrontend {

    private CMF cmf;
    private JTable table;
    private VisitaTableModel model;
    private JDateChooser fechaHojaDeCargo;
    private FechaPermitidaEvaluator evaluadorFechas;

    public VentanaHojasDeCargo() {
        this.cmf = CMF.getInstance();
        configurarPanel();
        inicializarComponentes();
        configurarEventos();
    }

    private void configurarPanel() {
        setBackground(Color.WHITE);
        setLayout(null);
        setBounds(305, 0, 796, 673);
    }

    private void inicializarComponentes() {
        agregarPanelSuperior();
        agregarEtiquetaFecha();
        agregarSelectorFecha();
        agregarPanelTabla();
    }

    private void agregarPanelSuperior() {
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBounds(0, 0, 796, 51);
        panelSuperior.setBackground(COLOR_AZUL);
        panelSuperior.setLayout(null);

        JLabel cartelPestanna = new JLabel("HOJAS DE CARGO");
        cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
        cartelPestanna.setForeground(Color.WHITE);
        cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
        cartelPestanna.setBounds(25, 0, 200, 51);
        panelSuperior.add(cartelPestanna);

        add(panelSuperior);
    }

    private void agregarEtiquetaFecha() {
        JLabel cartelListadoVisitas = new JLabel("Hoja de cargo del día:");
        cartelListadoVisitas.setFont(new Font("Arial", Font.BOLD, 18));
        cartelListadoVisitas.setBounds(50, 105, 203, 20);
        add(cartelListadoVisitas);
    }

    private void agregarSelectorFecha() {
        fechaHojaDeCargo = new JDateChooser();
        fechaHojaDeCargo.setToolTipText("Introduzca la fecha o seleccione por el calendario");
        fechaHojaDeCargo.setFont(new Font("Arial", Font.PLAIN, 16));
        fechaHojaDeCargo.setDateFormatString("d/MMM/yyyy");
        fechaHojaDeCargo.setBounds(244, 105, 140, 22);
        fechaHojaDeCargo.setMaxSelectableDate(new Date());

        List<LocalDate> fechasValidas = cmf.obtenerFechasDeHojasDeCargo();
        Date[] fechasValidasDate = new Date[fechasValidas.size()];
        for (int i = 0; i < fechasValidas.size(); i++) {
            fechasValidasDate[i] = UtilFecha.convertirALegacyDate(fechasValidas.get(i));
        }

        evaluadorFechas = new FechaPermitidaEvaluator(fechasValidasDate);
        fechaHojaDeCargo.getJCalendar().getDayChooser().addDateEvaluator(evaluadorFechas);

        if (!fechasValidas.isEmpty()) {
            fechaHojaDeCargo.setDate(UtilFecha.convertirALegacyDate(LocalDate.now()));
        }

        add(fechaHojaDeCargo);
    }

    private void agregarPanelTabla() {
        LocalDate hoy = LocalDate.now();
        List<Visita> visitasIniciales = obtenerVisitasPorFecha(hoy);

        model = new VisitaTableModel(visitasIniciales);
        model.setMostrarFecha(false);

        table = TablaPersonalizada.crearTablaPersonalizada(model);
        TableRowSorter<VisitaTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 700, 405);

        JPanel panelTabla = new JPanel();
        panelTabla.setBounds(50, 140, 700, 435);
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setLayout(null);

        BuscadorTabla buscador = new BuscadorTabla(sorter, "Buscar en la tabla...");
        buscador.setBounds(0, 0, 700, 25);
        panelTabla.add(buscador);
        panelTabla.add(scrollPane);

        add(panelTabla);
    }

    private List<Visita> obtenerVisitasPorFecha(LocalDate fecha) {
        List<Visita> visitas = new ArrayList<>();
        HojaCargosDiaria hoja = cmf.obtenerHojaDeCargosPorFecha(fecha);
        if (hoja != null) {
            List<Visita> visitasHoja = hoja.getVisitas();
            if (visitasHoja != null) {
                visitas.addAll(visitasHoja);
            }
        }
        return visitas;
    }

    private void configurarEventos() {
        configurarCambioFecha();
        configurarDobleClickTabla();
    }

    private void configurarCambioFecha() {
        fechaHojaDeCargo.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                cargarDatos();
            }
        });
    }

    private void cargarDatos() {
        Date fechaSeleccionada = fechaHojaDeCargo.getDate();
        boolean tieneFecha = (fechaSeleccionada != null);

        if (tieneFecha) {
            LocalDate fecha = UtilFecha.convertirALocalDate(fechaSeleccionada);
            actualizarModeloVisitasPorFecha(fecha);
        } else {
            actualizarModeloVisitasPorFecha(null);
        }
    }

    private void actualizarModeloVisitasPorFecha(LocalDate fecha) {
        List<Visita> visitas = new ArrayList<>();
        if (fecha != null) {
            visitas = obtenerVisitasPorFecha(fecha);
        }
        model.setVisitas(visitas);
    }

    private void configurarDobleClickTabla() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarDobleClick(e);
            }
        });
    }

    private void manejarDobleClick(MouseEvent e) {
        boolean esDobleClick = (e.getClickCount() == 2);
        int viewRow = table.getSelectedRow();
        boolean filaValida = (viewRow != -1);

        if (esDobleClick && filaValida) {
            int modelRow = table.convertRowIndexToModel(viewRow);
            Object valorId = model.getValueAt(modelRow, model.findColumn("ID Visita"));
            boolean idValido = (valorId instanceof Integer);
            if (idValido) {
                int id = (Integer) valorId;
                Visita visita = cmf.obtenerVisitaPorId(id);
                abrirFormulario(visita);
            }
        }
    }

    private void abrirFormulario(Visita visita) {
        Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
        FormularioVisitas formularioVisitas = new FormularioVisitas(
                ventanaPrincipal, visita, ModoFormulario.VISUALIZACION);
        formularioVisitas.setLocationRelativeTo(ventanaPrincipal);
        formularioVisitas.setVisible(true);
    }

    private void actualizarFechasPermitidas() {
        List<LocalDate> fechasValidas = cmf.obtenerFechasDeHojasDeCargo();
        Date[] fechasValidasDate = new Date[fechasValidas.size()];
        for (int i = 0; i < fechasValidas.size(); i++) {
            fechasValidasDate[i] = UtilFecha.convertirALegacyDate(fechasValidas.get(i));
        }

        if (evaluadorFechas != null) {
            fechaHojaDeCargo.getJCalendar().getDayChooser().removeDateEvaluator(evaluadorFechas);
        }

        evaluadorFechas = new FechaPermitidaEvaluator(fechasValidasDate);
        fechaHojaDeCargo.getJCalendar().getDayChooser().addDateEvaluator(evaluadorFechas);

        fechaHojaDeCargo.setDate(UtilFecha.convertirALegacyDate(LocalDate.now()));
    }

    @Override
    public void show() {
        super.show();
        actualizarFechasPermitidas();
        cargarDatos();
    }
}

class FechaPermitidaEvaluator implements IDateEvaluator {
    private Set<String> fechasPermitidas;
    private SimpleDateFormat formato;

    public FechaPermitidaEvaluator(Date[] fechas) {
        fechasPermitidas = new HashSet<String>();
        formato = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < fechas.length; i++) {
            if (fechas[i] != null) {
                fechasPermitidas.add(formato.format(fechas[i]));
            }
        }
    }

    @Override
    public boolean isInvalid(Date date) {
        boolean esInvalida = true;

        if (date != null) {
            String clave = formato.format(date);
            if (fechasPermitidas.contains(clave)) {
                esInvalida = false;
            }
        }

        return esInvalida;
    }

    @Override public boolean isSpecial(Date date) { return false; }
    @Override public String getInvalidTooltip() { return "Fecha no válida"; }
    @Override public String getSpecialTooltip() { return null; }
    @Override public Color getInvalidForegroundColor() { return Color.LIGHT_GRAY; }
    @Override public Color getInvalidBackroundColor() { return Color.WHITE; }
    @Override public Color getSpecialForegroundColor() { return null; }
    @Override public Color getSpecialBackroundColor() { return null; }
}

