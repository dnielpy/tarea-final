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
    private boolean mostrarIdVisita;
    private String[] columnNames;

    public VisitaTableModel(List<Visita> listaInicial) {
        if (listaInicial == null) {
            this.visitas = new ArrayList<>();
        } else {
            this.visitas = listaInicial;
        }

        this.mostrarFecha = false;
        this.mostrarHistoriaClinica = true;
        this.mostrarIdVisita = true;

        actualizarColumnNames();
    }

    public void setVisitas(List<Visita> nuevasVisitas) {
        if (nuevasVisitas == null) {
            this.visitas = new ArrayList<>();
        } else {
            this.visitas = nuevasVisitas;
        }

        fireTableDataChanged();
    }

    public void setMostrarFecha(boolean mostrar) {
        this.mostrarFecha = mostrar;
        actualizarEstructura();
    }

    public void setMostrarHistoriaClinica(boolean mostrar) {
        this.mostrarHistoriaClinica = mostrar;
        actualizarEstructura();
    }

    public void setMostrarIdVisita(boolean mostrar) {
        this.mostrarIdVisita = mostrar;
        actualizarEstructura();
    }

    private void actualizarEstructura() {
        actualizarColumnNames();
        fireTableStructureChanged();
    }

    private void actualizarColumnNames() {
        List<String> columnas = new ArrayList<>();

        if (mostrarIdVisita) {
            columnas.add("ID Visita");
        }

        if (mostrarHistoriaClinica) {
            columnas.add("H. Clínica");
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
        int total = 0;
        if (visitas != null) {
            total = visitas.size();
        }
        return total;
    }

    @Override
    public int getColumnCount() {
        int total = 0;
        if (columnNames != null) {
            total = columnNames.length;
        }
        return total;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object valor = null;
        boolean datosValidos = visitas != null && rowIndex >= 0 && rowIndex < visitas.size();

        if (datosValidos) {
            Visita visita = visitas.get(rowIndex);
            int col = 0;

            boolean colAsignado = false;

            if (!colAsignado && mostrarIdVisita) {
                if (columnIndex == col) {
                    valor = visita.getId();
                    colAsignado = true;
                }
                col++;
            }

            if (!colAsignado && mostrarHistoriaClinica) {
                if (columnIndex == col) {
                    valor = visita.getPacienteHistoriaClinicaID();
                    colAsignado = true;
                }
                col++;
            }

            if (!colAsignado) {
                if (columnIndex == col) {
                    valor = visita.getResumenEspecialidadesRemitidas();
                    colAsignado = true;
                }
                col++;
            }

            if (!colAsignado) {
                if (columnIndex == col) {
                    valor = visita.getResumenAnalisis();
                    colAsignado = true;
                }
                col++;
            }

            if (!colAsignado && mostrarFecha) {
                if (columnIndex == col) {
                    valor = visita.getFechaFormateada();
                    colAsignado = true;
                }
            }
        }

        return valor;
    }

    @Override
    public String getColumnName(int column) {
        String nombre = "";
        boolean valido = column >= 0 && columnNames != null && column < columnNames.length;

        if (valido) {
            nombre = columnNames[column];
        }

        return nombre;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clase = Object.class;
        int filas = getRowCount();

        if (filas > 0) {
            Object valor = getValueAt(0, columnIndex);
            if (valor != null) {
                clase = valor.getClass();
            }
        }

        return clase;
    }

    public void eliminarVisitaPorId(int id) {
        int indice = -1;
        int total = visitas.size();
        int i = 0;

        while (i < total) {
            boolean coincide = visitas.get(i).getId() == id;

            if (indice == -1 && coincide) {
                indice = i;
            }

            i++;
        }

        boolean valido = indice >= 0 && indice < visitas.size();

        if (valido) {
            visitas.remove(indice);
            fireTableRowsDeleted(indice, indice);
        }
    }

    public void aplicarCentrado(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setVerticalAlignment(SwingConstants.CENTER);

        int total = getColumnCount();
        int i = 0;

        while (i < total) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
            i++;
        }
    }
}
