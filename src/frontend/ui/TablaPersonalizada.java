package frontend.ui;

import javax.swing.*;
import javax.swing.table.*;

import util.ConstantesFrontend;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TablaPersonalizada implements ConstantesFrontend {

    private static final Color COLOR_FILA_PAR = Color.WHITE;
    private static final Color COLOR_FILA_IMPAR = new Color(245, 245, 245); // gris claro
    private static final Color COLOR_HOVER = new Color(230, 230, 230); // gris más oscuro para hover

    private static int filaHover = -1; // fila que se está señalando con el mouse

    public static JTable crearTablaPersonalizada(TableModel modelo) {
        final JTable tabla = new JTable(modelo) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                if (isRowSelected(row)) {
                    c.setBackground(getSelectionBackground());
                } else if (row == filaHover) {
                    c.setBackground(COLOR_HOVER);
                } else {
                    Color colorFondo = (row % 2 == 0) ? COLOR_FILA_PAR : COLOR_FILA_IMPAR;
                    c.setBackground(colorFondo);
                }

                return c;
            }
        };

        // --- Personalización general ---
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(Color.BLACK);
        tabla.setFont(new Font("Arial", Font.PLAIN, 16));
        tabla.setGridColor(SystemColor.controlHighlight);
        tabla.setRowHeight(30);
        tabla.setFillsViewportHeight(true);
        tabla.setSelectionBackground(new Color(200, 200, 200)); // color selección

        // --- Cabecera ---
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 16));

        // --- Borde de celdas ---
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createLineBorder(SystemColor.controlHighlight));
        tabla.setDefaultRenderer(Object.class, renderer);

        // --- Efecto hover ---
        tabla.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int fila = tabla.rowAtPoint(e.getPoint());
                if (fila != filaHover) {
                    filaHover = fila;
                    tabla.repaint();
                }
                tabla.setCursor(fila >= 0 ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getDefaultCursor());
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                filaHover = -1;
                tabla.repaint();
                tabla.setCursor(Cursor.getDefaultCursor());
            }
        });

        return tabla;
    }

    public static JScrollPane envolverEnScroll(JTable tabla, int x, int y, int ancho, int alto) {
        ScrollPaneModerno scrollPane = new ScrollPaneModerno(tabla);
        scrollPane.setBounds(x, y, ancho, alto);
        return scrollPane;
    }
}
