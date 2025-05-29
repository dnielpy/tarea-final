package frontend;

import javax.swing.table.AbstractTableModel;

import entidades.Paciente;
import entidades.Persona;

import java.util.List;

public class PersonaTableModel extends AbstractTableModel {
    private final List<Paciente> pacientes;
    private final String[] columnNames = {"Nombre", "Historio Clinica", "Edad"};

    public PersonaTableModel(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public int getRowCount() {
        return pacientes.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona paciente = pacientes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ((Persona) paciente).getNombreYApellidos();
            case 1:
                return ((Paciente) paciente).getHistoriaClinicaID();
            case 2:
                return ((Paciente) paciente).getEdad();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
