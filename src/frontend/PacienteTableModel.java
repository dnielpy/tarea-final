package frontend;

import javax.swing.table.AbstractTableModel;

import entidades.Paciente;
import entidades.Persona;

import java.util.List;

public class PacienteTableModel extends AbstractTableModel {
    private List<Paciente> pacientes;
    private String[] columnNames = {"Nombre", "Género", "CI", "H. Clínica", "Edad"};

    public PacienteTableModel(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
    public void setPacientes(List<Paciente> pacientes) {
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
                return paciente.getNombreYApellidos();
            case 1:
                return ((Paciente)paciente).getGenero();
            case 2:
                return ((Paciente)paciente).getCI();
            case 3:
                return ((Paciente)paciente).getHistoriaClinica().getId();
            case 4:
                return ((Paciente)paciente).getEdad();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    // MÉTODO PERSONALIZADO PARA ELIMINAR FILAS
    public void eliminarPacientePorId(int id, int indice) {
    	boolean ciclar = true;
        for (int i = 0; i < pacientes.size() && ciclar; i++) {
            if (pacientes.get(i).getHistoriaClinica().getId() == id) {
                pacientes.remove(i);
                fireTableRowsDeleted(indice, indice);  // Notificar a la tabla que una fila fue eliminada
                ciclar = false;
            }
        }
    }

}
