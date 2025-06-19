package frontend.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import entidades.registros.Visita;

public class VisitaTableModel extends AbstractTableModel {
    private List<Visita> visitas;
    private boolean mostrarFecha;
    private boolean mostrarHistoriaClinica;
    private String[] columnNames;

    public VisitaTableModel(List<Visita> list) {
        this.visitas = list;
        this.mostrarFecha = false;
        this.mostrarHistoriaClinica = true;
        actualizarColumnNames();
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
        fireTableDataChanged();
    }

    public void setMostrarFecha(boolean mostrarFecha) {
        this.mostrarFecha = mostrarFecha;
        actualizarColumnNames();
        fireTableStructureChanged();
    }

    public void setMostrarHistoriaClinica(boolean mostrarHistoriaClinica) {
        this.mostrarHistoriaClinica = mostrarHistoriaClinica;
        actualizarColumnNames();
        fireTableStructureChanged();
    }

    private void actualizarColumnNames() {
        List<String> columnas = new ArrayList<>();

        if (mostrarHistoriaClinica) {
            columnas.add("Historia Clinica");
        }

        columnas.add("Remitido a");
        columnas.add("Analisis Orientado");

        if (mostrarFecha) {
            columnas.add("Fecha");
        }

        columnNames = columnas.toArray(new String[0]);
    }

    @Override
    public int getRowCount() {
        int filas = 0;
        if (visitas != null) {
            filas = visitas.size();
        }
        return filas;
    }

    @Override
    public int getColumnCount() {
        int columnas = 0;
        if (columnNames != null) {
            columnas = columnNames.length;
        }
        return columnas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object valor = null;
        Visita visita = visitas.get(rowIndex);
        int offset = 0;

        if (mostrarHistoriaClinica) {
            if (columnIndex == 0) {
                valor = visita.getPacienteHistoriaClinicaID();
            }
            offset = 1;
        }

        if (columnIndex == offset) {
            valor = visita.getResumenEspecialidadesRemitidas();
        } else if (columnIndex == offset + 1) {
            valor = visita.getResumenAnalisis();
        } else if (mostrarFecha && columnIndex == offset + 2) {
            valor = visita.getFechaFormateada(); // Se asume formato String
        }

        return valor;
    }

    @Override
    public String getColumnName(int column) {
        String nombre = "";
        if (column >= 0 && column < columnNames.length) {
            nombre = columnNames[column];
        }
        return nombre;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clase = Object.class;

        if (getRowCount() > 0) {
            Object valor = getValueAt(0, columnIndex);
            if (valor != null) {
                clase = valor.getClass();
            }
        }

        return clase;
    }

    public void eliminarVisitaPorId(int id) {
        int index = -1;

        for (int i = 0; i < visitas.size(); i++) {
            if (visitas.get(i).getId() == id && index == -1) {
                index = i;
            }
        }

        if (index != -1) {
            fireTableRowsDeleted(index, index);
            visitas.remove(index);
        }
    }

    public void aplicarCentrado(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setVerticalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}
