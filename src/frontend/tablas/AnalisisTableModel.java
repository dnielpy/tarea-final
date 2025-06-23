package frontend.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import entidades.registros.Analisis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnalisisTableModel extends AbstractTableModel {

	private List<Analisis> analisisList;

	private boolean mostrarFechaOrientado;
	private boolean mostrarFechaResultado;
	private boolean mostrarResultados;
	private boolean mostrarEstado;

	private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

	public AnalisisTableModel(List<Analisis> lista) {
		if (lista != null) {
			this.analisisList = lista;
		} else {
			this.analisisList = new ArrayList<>();
		}

		// Por defecto solo mostrar base
		mostrarFechaOrientado = true;
		mostrarFechaResultado = false;
		mostrarResultados = false;
		mostrarEstado = false;
	}

	// Setters individuales
	public void setMostrarFechaOrientado(boolean mostrar) {
		this.mostrarFechaOrientado = mostrar;
		fireTableStructureChanged();
	}

	public void setMostrarFechaResultado(boolean mostrar) {
		this.mostrarFechaResultado = mostrar;
		fireTableStructureChanged();
	}

	public void setMostrarResultados(boolean mostrar) {
		this.mostrarResultados = mostrar;
		fireTableStructureChanged();
	}

	public void setMostrarEstado(boolean mostrar) {
		this.mostrarEstado = mostrar;
		fireTableStructureChanged();
	}

	public void setAnalisisList(List<Analisis> lista) {
		if (lista != null) {
			this.analisisList = lista;
		} else {
			this.analisisList = new ArrayList<>();
		}
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		int count = 0;
		if (analisisList != null) {
			count = analisisList.size();
		}
		return count;
	}

	@Override
	public int getColumnCount() {
		int columnas = 2; // ID Visita y Tipo de Análisis
		if (mostrarFechaOrientado) columnas++;
		if (mostrarFechaResultado) columnas++;
		if (mostrarResultados) columnas++;
		if (mostrarEstado) columnas++;
		return columnas;
	}

	@Override
	public String getColumnName(int column) {
		String nombre = "";

		int index = 0;

		if (column == index) {
			nombre = "ID Visita";
		}

		index++;
		if (column == index) {
			nombre = "Tipo";
		}

		index++;
		if (mostrarFechaOrientado && column == index) {
			nombre = "Orientado";
		}

		if (mostrarFechaOrientado) index++;
		if (mostrarFechaResultado && column == index) {
			nombre = "Actualizado";
		}

		if (mostrarFechaResultado) index++;
		if (mostrarResultados && column == index) {
			nombre = "Resultados";
		}

		if (mostrarResultados) index++;
		if (mostrarEstado && column == index) {
			nombre = "Estado";
		}

		return nombre;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Integer.class;
		}
		return String.class;
	}

	public Analisis getAnalisisAt(int rowIndex) {
		Analisis resultado = null;

		if (analisisList != null && rowIndex >= 0 && rowIndex < analisisList.size()) {
			resultado = analisisList.get(rowIndex);
		}
		return resultado;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = "";

		if (analisisList != null && rowIndex >= 0 && rowIndex < analisisList.size()) {
			Analisis a = analisisList.get(rowIndex);

			int index = 0;

			if (columnIndex == index) {
				valor = a.getVisitaId();
			}

			index++;
			if (columnIndex == index) {
				valor = a.getTipoDeAnalisis();
			}

			index++;
			if (mostrarFechaOrientado && columnIndex == index) {
				valor = formatearFecha(a.getFechaOrientado());
			}

			if (mostrarFechaOrientado) index++;
			if (mostrarFechaResultado && columnIndex == index) {
				valor = formatearFecha(a.getFechaResultado());
			}

			if (mostrarFechaResultado) index++;
			if (mostrarResultados && columnIndex == index) {
				valor = a.getResultados() != null ? a.getResultados() : "";
			}

			if (mostrarResultados) index++;
			if (mostrarEstado && columnIndex == index) {
				valor = a.estaPendienteDeResultado() ? "Pendiente" : "Completado";
			}
		}

		return valor;
	}

	private String formatearFecha(LocalDate fecha) {
		String resultado = "";
		if (fecha != null) {
			resultado = fecha.format(formatoFecha);
		}
		return resultado;
	}

	public void eliminarAnalisisPorId(int id) {
		if (analisisList != null) {
			int index = -1;
			int i = 0;

			while (i < analisisList.size() && index == -1) {
				Analisis actual = analisisList.get(i);
				if (actual != null && actual.getVisitaId() == id) {
					index = i;
				}
				i = i + 1;
			}

			if (index != -1) {
				analisisList.remove(index);
				fireTableRowsDeleted(index, index);
			}
		}
	}

	public void aplicarCentrado(javax.swing.JTable table) {
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		renderer.setVerticalAlignment(SwingConstants.CENTER);

		int total = getColumnCount();
		int i = 0;
		while (i < total) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
			i = i + 1;
		}
	}
}
