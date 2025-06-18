package frontend;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import componentesPropios.TablaPersonalizada;
import entidades.CMF;
import entidades.HojaCargosDiaria;
import entidades.Visita;

public class VentanaHojasDeCargo extends JPanel implements ConstantesFrontend {

	private CMF cmf;
	private JTable table;
	private VisitaTableModel model;
	private JDateChooser fechaHojaDeCargo;

	public VentanaHojasDeCargo() {
		cmf = CMF.getInstance();

		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(305, 0, 796, 673);

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 796, 51);
		add(panelSuperior);
		panelSuperior.setBackground(COLOR_AZUL);
		panelSuperior.setLayout(null);

		JLabel cartelPestanna = new JLabel("HOJAS DE CARGO");
		cartelPestanna.setHorizontalAlignment(SwingConstants.LEFT);
		cartelPestanna.setForeground(Color.WHITE);
		cartelPestanna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPestanna.setBounds(25, 0, 200, 51);
		panelSuperior.add(cartelPestanna);

		// ---------- FECHA ----------
		fechaHojaDeCargo = new JDateChooser();
		fechaHojaDeCargo.setToolTipText("Introduzca la fecha o seleccione por el calendario");
		fechaHojaDeCargo.setFont(new Font("Arial", Font.PLAIN, 16));
		fechaHojaDeCargo.setDateFormatString("d/MMM/yyyy");
		fechaHojaDeCargo.setBounds(278, 103, 140, 22);
		fechaHojaDeCargo.setDate(new Date());
		fechaHojaDeCargo.setMaxSelectableDate(new Date());
		add(fechaHojaDeCargo);

		// ---------- MODELO Y TABLA ----------
		HojaCargosDiaria hojaDeCargos = cmf.obtenerHojaDeCargosPorFecha(LocalDate.now());
		List<Visita> visitasIniciales = (hojaDeCargos != null) ? hojaDeCargos.getVisitas() : null;
		model = new VisitaTableModel(visitasIniciales);
		model.setMostrarFecha(false);

		table = TablaPersonalizada.crearTablaPersonalizada(model);
		JScrollPane scrollPane = TablaPersonalizada.envolverEnScroll(table, 0, 30, 630, 406);

		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(80, 141, 630, 436);
		add(panelTabla);
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setLayout(null);
		panelTabla.add(scrollPane);

		// ---------- ETIQUETA ----------
		JLabel cartelListadoVisitas = new JLabel("Hoja de cargo del d\u00EDa:");
		cartelListadoVisitas.setFont(new Font("Arial", Font.BOLD, 18));
		cartelListadoVisitas.setBounds(80, 104, 203, 20);
		add(cartelListadoVisitas);

		// ---------- LISTENER FECHA ----------
		fechaHojaDeCargo.addPropertyChangeListener("date", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		    	Date fechaSeleccionada = fechaHojaDeCargo.getDate();
		    	if (fechaSeleccionada != null) {
		    	    LocalDate fechaLocal = fechaSeleccionada.toInstant()
		    	                                .atZone(ZoneId.systemDefault())
		    	                                .toLocalDate();
		    	    HojaCargosDiaria hoja = cmf.obtenerHojaDeCargosPorFecha(fechaLocal);
		    	    if (hoja != null) {
		    	        model.setVisitas(hoja.getVisitas());
		    	    } else {
		    	        model.setVisitas(new ArrayList<Visita>());
		    	    }
		    	}
		    }
		});

		// ---------- DOBLE CLIC EN TABLA ----------
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int viewRow = table.getSelectedRow();
					if (viewRow != -1) {
						int modelRow = table.convertRowIndexToModel(viewRow);
						int id = (int) model.getValueAt(modelRow, model.findColumn("Historia Clinica"));
						Visita visita = cmf.obtenerVisitaPorId(id);
						abrirFormulario(visita); // Aunque sea solo lectura, puedes reutilizarlo
					}
				}
			}
		});
	}

	private void abrirFormulario(Visita visita) {
		Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);
		FormularioVisitas formularioVisitas = new FormularioVisitas(ventanaPrincipal, visita);
		formularioVisitas.setLocationRelativeTo(ventanaPrincipal);
		formularioVisitas.setVisible(true);
	}
}