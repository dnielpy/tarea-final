package frontend.ui;

import java.text.Normalizer;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

public class BuscadorTabla extends PlaceholderTextField {

    public BuscadorTabla(final TableRowSorter<? extends TableModel> sorter, final String placeholder) {
        super(placeholder);

        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltro(sorter);
            }

            public void removeUpdate(DocumentEvent e) {
                aplicarFiltro(sorter);
            }

            public void changedUpdate(DocumentEvent e) {
                aplicarFiltro(sorter);
            }
        });
    }

    private void aplicarFiltro(final TableRowSorter<? extends TableModel> sorter) {
        String texto = getText().trim();
        String textoNormalizado = normalizarTexto(texto);
        RowFilter<TableModel, Integer> filtro = crearFiltro(textoNormalizado);
        sorter.setRowFilter(filtro);
    }

    private RowFilter<TableModel, Integer> crearFiltro(final String texto) {
        RowFilter<TableModel, Integer> filtro;

        if (texto.isEmpty()) {
            filtro = null;
        } else {
            filtro = new RowFilter<TableModel, Integer>() {
                public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                    boolean encontrado = false;
                    int i = 0;
                    int totalColumnas = entry.getValueCount();

                    while (!encontrado && i < totalColumnas) {
                        Object valor = entry.getValue(i);
                        if (valor != null) {
                            String contenido = normalizarTexto(valor.toString());
                            if (contenido.contains(texto)) {
                                encontrado = true;
                            }
                        }
                        i = i + 1;
                    }

                    return encontrado;
                }
            };
        }

        return filtro;
    }

    private String normalizarTexto(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        normalizado = normalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        normalizado = normalizado.toLowerCase();
        return normalizado;
    }
}