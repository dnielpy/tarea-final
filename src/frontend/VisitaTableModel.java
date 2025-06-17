package frontend;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import entidades.Visita;

public class VisitaTableModel extends AbstractTableModel {
    private List<Visita> visitas;
    private String[] columnNames = { "Historia Clinica", "Especialidad", "Analisis Orientado", "Fecha" };

    public VisitaTableModel(List<Visita> list) {
        visitas = list;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return (visitas != null) ? visitas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return (columnNames != null) ? columnNames.length : 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Visita visita = visitas.get(rowIndex);
        Object value = null;

        if (columnIndex == 0)
            value = visita.getPacienteHistoriaClinicaID();
        if (columnIndex == 1)
            value = visita.getEspecialidadRemitida();
        if (columnIndex == 2) 
            value = (visita.getAnalisis() != null) ? visita.getAnalisis().getTipoDeAnalisis() : "Sin análisis";     
        if (columnIndex == 3) 
            value = visita.getFechaFormateada();     
        return value;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object value = (getRowCount() > 0) ? getValueAt(0, columnIndex) : null;
        return (value != null) ? value.getClass() : Object.class;
    }

    public void eliminarVisitaPorId(int id) {
        int index = -1;

        for (int i = 0; i < visitas.size(); i++) {
            if (visitas.get(i).getId() == id && index == -1) {
                index = i;
            }
        }

        if (index != -1) {
            fireTableRowsDeleted(index, index); // Notify BEFORE removing
            visitas.remove(index);
        }
    }

    public void aplicarCentrado(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setVerticalAlignment(SwingConstants.CENTER); // Centrar también verticalmente

        for (int i = 0; i < getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}
