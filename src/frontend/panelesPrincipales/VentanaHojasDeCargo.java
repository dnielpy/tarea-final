package frontend.panelesPrincipales;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import util.ConstantesFrontend;

import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;

import frontend.ui.BuscadorTabla;
import frontend.ui.TablaPersonalizada;
import entidades.CMF;
import entidades.registros.HojaCargosDiaria;
import entidades.registros.Visita;
import frontend.formularios.FormularioVisitas;
import frontend.formularios.FormularioVisitas.ModoFormulario;
import frontend.tablas.VisitaTableModel;

public class VentanaHojasDeCargo extends JPanel implements ConstantesFrontend {

    private CMF cmf;
    private JTable table;
    private VisitaTableModel model;
    private JDateChooser fechaHojaDeCargo;

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

        // Conversión a Date para el evaluator
        Date[] fechasValidasDate = new Date[fechasValidas.size()];
        for (int i = 0; i < fechasValidas.size(); i++) {
            fechasValidasDate[i] = convertirLocalDateASinDesfase(fechasValidas.get(i));
        }

        FechaPermitidaEvaluator evaluador = new FechaPermitidaEvaluator(fechasValidasDate);
        fechaHojaDeCargo.getJCalendar().getDayChooser().addDateEvaluator(evaluador);

        if (!fechasValidas.isEmpty()) {   
            fechaHojaDeCargo.setDate(convertirLocalDateASinDesfase(LocalDate.now()));
        }

        add(fechaHojaDeCargo);
    }

    private Date convertirLocalDateASinDesfase(LocalDate localDate) {
        // Ponemos la hora a mediodía para evitar desfases
        ZonedDateTime zonedDateTime = localDate.atTime(12, 0).atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    private void agregarPanelTabla() {
        LocalDate hoy = LocalDate.now();
        HojaCargosDiaria hojaDeCargos = cmf.obtenerHojaDeCargosPorFecha(hoy);
        List<Visita> visitasIniciales = new ArrayList<>();
        if (hojaDeCargos != null && hojaDeCargos.getVisitas() != null) {
            visitasIniciales = hojaDeCargos.getVisitas();
        }

        model = new VisitaTableModel(visitasIniciales);
        model.setMostrarFecha(false);

        table = TablaPersonalizada.crearTablaPersonalizada(model);
        TableRowSorter<VisitaTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 700, 405);

        // Panel contenedor
        JPanel panelTabla = new JPanel();
        panelTabla.setBounds(50, 140, 700, 435);
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setLayout(null);

        // Buscador
        BuscadorTabla buscador = new BuscadorTabla(sorter, "Buscar en la tabla...");
        buscador.setBounds(0, 0, 700, 25);
        panelTabla.add(buscador);

        // Tabla con scroll
        panelTabla.add(scrollPane);
        add(panelTabla);
    }

    private void configurarEventos() {
        configurarCambioFecha();
        configurarDobleClickTabla();
    }

    private void configurarCambioFecha() {
        fechaHojaDeCargo.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                actualizarVisitasPorFecha();
            }
        });
    }

    private void actualizarVisitasPorFecha() {
        Date fechaSeleccionada = fechaHojaDeCargo.getDate();
        if (fechaSeleccionada == null) {
            return;
        }

        LocalDate fechaLocal = fechaSeleccionada.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        HojaCargosDiaria hoja = cmf.obtenerHojaDeCargosPorFecha(fechaLocal);
        List<Visita> visitas = new ArrayList<>();
        if (hoja != null && hoja.getVisitas() != null) {
            visitas = hoja.getVisitas();
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
        if (e.getClickCount() != 2) {
            return;
        }

        int viewRow = table.getSelectedRow();
        if (viewRow == -1) {
            return;
        }

        int modelRow = table.convertRowIndexToModel(viewRow);
        Object valorId = model.getValueAt(modelRow, model.findColumn("Historia Clinica"));

        if (valorId instanceof Integer) {
            int id = (Integer) valorId;
            Visita visita = cmf.obtenerVisitaPorId(id);
            abrirFormulario(visita);
        }
    }

    private void abrirFormulario(Visita visita) {
        Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
        FormularioVisitas formularioVisitas = new FormularioVisitas(ventanaPrincipal, visita, ModoFormulario.VISUALIZACION);
        formularioVisitas.setLocationRelativeTo(ventanaPrincipal);
        formularioVisitas.setVisible(true);
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
        if (date == null) {
            return true;
        }
        String clave = formato.format(date);
        return !fechasPermitidas.contains(clave);
    }

    @Override public boolean isSpecial(Date date) { return false; }
    @Override public String getInvalidTooltip() { return "Fecha no válida"; }
    @Override public String getSpecialTooltip() { return null; }
    @Override public Color getInvalidForegroundColor() { return Color.LIGHT_GRAY; }
    @Override public Color getInvalidBackroundColor() { return Color.WHITE; }
    @Override public Color getSpecialForegroundColor() { return null; }
    @Override public Color getSpecialBackroundColor() { return null; }
}
