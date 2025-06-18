package frontend.ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TablaPersonalizada {

    public static JTable crearTablaPersonalizada(TableModel modelo) {
        JTable tabla = new JTable(modelo);

        // Personalización general
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(Color.BLACK);
        tabla.setFont(new Font("Arial", Font.PLAIN, 16));
        tabla.setGridColor(SystemColor.controlHighlight);
        tabla.setRowHeight(30);
        tabla.setFillsViewportHeight(true);

        // Cabecera
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 16));

        // Borde de celdas
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createLineBorder(SystemColor.controlHighlight));
        tabla.setDefaultRenderer(Object.class, renderer);

        return tabla;
    }

    public static JScrollPane envolverEnScroll(JTable tabla, int x, int y, int ancho, int alto) {
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(x, y, ancho, alto);
        return scrollPane;
    }
}
