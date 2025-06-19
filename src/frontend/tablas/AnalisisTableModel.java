package frontend.tablas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import entidades.registros.Analisis;
import entidades.registros.Visita;

import java.time.format.DateTimeFormatter;

public class AnalisisTableModel extends AbstractTableModel {

    private List<Analisis> analisisList;
    private boolean mostrarResultados;

    private final String[] columnNamesBase = { "ID Visita", "Tipo de Análisis", "Fecha Orientado" };
    private final String[] columnNamesConResultados = { "ID Visita", "Tipo de Análisis", "Fecha Orientado", "Fecha Resultado", "Resultados", "Estado" };

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AnalisisTableModel(List<Analisis> lista) {
        this.analisisList = lista;
        this.mostrarResultados = false; // Por defecto no mostrar columnas de resultados
    }

    public void setMostrarResultados(boolean mostrar) {
        this.mostrarResultados = mostrar;
        fireTableStructureChanged();
    }

    public void setAnalisisList(List<Analisis> lista) {
        this.analisisList = lista;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        int count;
        if (analisisList != null) {
            count = analisisList.size();
        } else {
            count = 0;
        }
        return count;
    }

    @Override
    public int getColumnCount() {
        int count;
        if (mostrarResultados) {
            count = columnNamesConResultados.length;
        } else {
            count = columnNamesBase.length;
        }
        return count;
    }

    @Override
    public String getColumnName(int column) {
        String name;
        if (mostrarResultados) {
            name = columnNamesConResultados[column];
        } else {
            name = columnNamesBase[column];
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clase;
        if (columnIndex == 0) {
            clase = Integer.class;
        } else if (columnIndex == 2 || (mostrarResultados && columnIndex == 3)) {
            clase = String.class;
        } else {
            clase = String.class;
        }
        return clase;
    }

    public Analisis getAnalisisAt(int rowIndex) {
        Analisis analisis;
        if (rowIndex >= 0 && rowIndex < analisisList.size()) {
            analisis = analisisList.get(rowIndex);
        } else {
            analisis = null;
        }
        return analisis;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object valor = null;

        if (analisisList != null && rowIndex >= 0 && rowIndex < analisisList.size()) {
            Analisis analisis = analisisList.get(rowIndex);

            if (!mostrarResultados) {
                if (columnIndex == 0) {
                    valor = analisis.getVisitaId();
                } else if (columnIndex == 1) {
                    valor = analisis.getTipoDeAnalisis();
                } else if (columnIndex == 2) {
                    if (analisis.getFechaOrientado() != null) {
                        valor = analisis.getFechaOrientado().format(formatoFecha);
                    } else {
                        valor = "";
                    }
                } else {
                    valor = null;
                }
            } else {
                if (columnIndex == 0) {
                    valor = analisis.getVisitaId();
                } else if (columnIndex == 1) {
                    valor = analisis.getTipoDeAnalisis();
                } else if (columnIndex == 2) {
                    if (analisis.getFechaOrientado() != null) {
                        valor = analisis.getFechaOrientado().format(formatoFecha);
                    } else {
                        valor = "";
                    }
                } else if (columnIndex == 3) {
                    if (analisis.getFechaResultado() != null) {
                        valor = analisis.getFechaResultado().format(formatoFecha);
                    } else {
                        valor = "";
                    }
                } else if (columnIndex == 4) {
                    if (analisis.getResultados() != null) {
                        valor = analisis.getResultados();
                    } else {
                        valor = "";
                    }
                } else if (columnIndex == 5) {
                    valor = analisis.estaPendienteDeResultado() ? "Pendiente" : "Completado";
                } else {
                    valor = null;
                }
            }
        }
        return valor;
    }

    public void eliminarAnalisisPorIdSinBreak(int id) {
        if (analisisList != null) {
            int index = -1;
            int i = 0;
            while (i < analisisList.size() && index == -1) {
                if (analisisList.get(i).getVisitaId() == id) {
                    index = i;
                }
                i++;
            }
            if (index != -1) {
                analisisList.remove(index);
                fireTableRowsDeleted(index, index);
            }
        }
    }

    public void aplicarCentrado(javax.swing.JTable table) {
        javax.swing.table.DefaultTableCellRenderer renderer = new javax.swing.table.DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        renderer.setVerticalAlignment(javax.swing.SwingConstants.CENTER);

        int columnas = getColumnCount();
        for (int i = 0; i < columnas; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
}
