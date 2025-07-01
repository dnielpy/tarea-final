package frontend.tablas;

import javax.swing.table.AbstractTableModel;

import entidades.CMF;
import entidades.personal.Paciente;
import java.util.List;

public class PacienteTableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Paciente> pacientes;
    private String[] columnNames = { "Nombre", "Género", "CI", "H. Clínica", "Edad" };

    public PacienteTableModel(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
        fireTableDataChanged();
    }

    public int getRowCount() {
        return (pacientes != null) ? pacientes.size() : 0;
    }

    public int getColumnCount() {
        return (columnNames != null) ? columnNames.length : 0;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Paciente paciente = pacientes.get(rowIndex);
        Object value = null;

        if (columnIndex == 0)
            value = paciente.getNombreYApellidos();
        if (columnIndex == 1)
            value = paciente.getGenero();
        if (columnIndex == 2)
            value = paciente.getCI();
        if (columnIndex == 3)
            value = paciente.getHistoriaClinica().getId();
        if (columnIndex == 4)
            value = paciente.getEdad();

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

}