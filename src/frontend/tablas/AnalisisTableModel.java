package frontend.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import entidades.registros.Analisis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class AnalisisTableModel extends AbstractTableModel {

    private List<Analisis> analisisList;
    private boolean mostrarFechaOrientado;
    private boolean mostrarFechaResultado;
    private boolean mostrarResultados;
    private boolean mostrarEstado;
    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

    public AnalisisTableModel(List<Analisis> lista) {
        if (lista == null) {
            this.analisisList = new ArrayList<>();
        } else {
            this.analisisList = lista;
        }

        mostrarFechaOrientado = true;
        mostrarFechaResultado = false;
        mostrarResultados = false;
        mostrarEstado = false;
    }

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
        if (lista == null) {
            this.analisisList = new ArrayList<>();
        } else {
            this.analisisList = lista;
        }

        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        int cantidad = 0;
        if (analisisList != null) {
            cantidad = analisisList.size();
        }
        return cantidad;
    }

    @Override
    public int getColumnCount() {   
        int columnas = 1 + 2; 
        if (mostrarFechaOrientado) columnas++;
        if (mostrarEstado) columnas++;
        if (mostrarFechaResultado) columnas++;
        if (mostrarResultados) columnas++;
        return columnas;
    }

    @Override
    public String getColumnName(int column) {
        String nombre = "";
        int indice = 0;

        if (column == indice) {
            nombre = "ID Análisis";
        }
        indice++;

        if (column == indice) {
            nombre = "ID Visita";
        }
        indice++;

        if (column == indice) {
            nombre = "Tipo";
        }
        indice++;

        if (mostrarFechaOrientado) {
            if (column == indice) {
                nombre = "Orientado";
            }
            indice++;
        }

        if (mostrarEstado) {
            if (column == indice) {
                nombre = "Estado";
            }
            indice++;
        }

        if (mostrarFechaResultado) {
            if (column == indice) {
                nombre = "Actualizado";
            }
            indice++;
        }

        if (mostrarResultados) {
            if (column == indice) {
                nombre = "Resultados";
            }
        }

        return nombre;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> tipo = String.class;

        if (columnIndex == 0) {
            tipo = Integer.class;
        }

        return tipo;
    }

    public Analisis getAnalisisAt(int rowIndex) {
        Analisis analisis = null;
        boolean dentroDeRango = analisisList != null && rowIndex >= 0 && rowIndex < analisisList.size();

        if (dentroDeRango) {
            analisis = analisisList.get(rowIndex);
        }

        return analisis;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object valor = "";
        if (analisisList != null && rowIndex >= 0 && rowIndex < analisisList.size()) {
            Analisis a = analisisList.get(rowIndex);
            int indice = 0;

            if (columnIndex == indice) {
                valor = a.getId();
            }
            indice++;

            if (columnIndex == indice) {
                valor = a.getVisitaId();
            }
            indice++;

            if (columnIndex == indice) {
                valor = a.getTipoDeAnalisis();
            }
            indice++;

            if (mostrarFechaOrientado) {
                if (columnIndex == indice) {
                    valor = formatearFecha(a.getFechaOrientado());
                }
                indice++;
            }

            if (mostrarEstado) {
                if (columnIndex == indice) {
                    valor = a.estaPendienteDeResultado() ? "Pendiente" : "Completado";
                }
                indice++;
            }

            if (mostrarFechaResultado) {
                if (columnIndex == indice) {
                    valor = (a.getFechaResultado() == null) ? "Pendiente" : formatearFecha(a.getFechaResultado());
                }
                indice++;
            }

            if (mostrarResultados) {
                if (columnIndex == indice) {
                    valor = (a.getResultados() != null) ? a.getResultados() : "";
                }
            }
        }
        return valor;
    }

    private String formatearFecha(LocalDate fecha) {
        String texto = "";
        if (fecha != null) {
            texto = fecha.format(formatoFecha);
        }
        return texto;
    }

    public void eliminarAnalisisPorId(int id) {
        int indice = -1;
        int total = 0;

        if (analisisList != null) {
            total = analisisList.size();
        }

        int i = 0;

        while (i < total) {
            Analisis actual = analisisList.get(i);
            boolean coincide = actual != null && actual.getVisitaId() == id;

            if (indice == -1 && coincide) {
                indice = i;
            }

            i++;
        }

        boolean valido = indice >= 0 && indice < total;

        if (valido) {
            analisisList.remove(indice);
            fireTableRowsDeleted(indice, indice);
        }
    }

    public void aplicarCentrado(JTable table) {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setVerticalAlignment(SwingConstants.CENTER);

        int columnas = getColumnCount();
        int i = 0;

        while (i < columnas) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
            i++;
        }
    }
}
