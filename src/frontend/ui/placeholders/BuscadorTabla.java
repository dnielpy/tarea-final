package frontend.ui.placeholders;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.text.Normalizer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

public class BuscadorTabla extends PlaceholderTextField {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon iconoLupa;
    private final int espacioIcono = 20;

    public BuscadorTabla(final TableRowSorter<? extends TableModel> sorter, final String placeholder) {
        super(placeholder);

        iconoLupa = new ImageIcon(getClass().getResource("/fotos/lupa.png"));
        setMargin(new Insets(2, espacioIcono + 4, 2, 4));

        DocumentListener listener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltro(sorter);
            }

            public void removeUpdate(DocumentEvent e) {
                aplicarFiltro(sorter);
            }

            public void changedUpdate(DocumentEvent e) {
                aplicarFiltro(sorter);
            }
        };

        getDocument().addDocumentListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (iconoLupa != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            boolean mostrarComoPlaceholder = getText().isEmpty();
            float opacidad = mostrarComoPlaceholder ? 0.5f : 1.0f;
            g2.setComposite(AlphaComposite.SrcOver.derive(opacidad));
            int y = (getHeight() - iconoLupa.getIconHeight()) / 2;
            iconoLupa.paintIcon(this, g2, 4, y);
            g2.dispose();
        }
    }

    private void aplicarFiltro(final TableRowSorter<? extends TableModel> sorter) {
        String texto = getText().trim();
        String textoNormalizado = normalizarTexto(texto);
        RowFilter<TableModel, Integer> filtro = crearFiltro(textoNormalizado);
        sorter.setRowFilter(filtro);
    }

    private RowFilter<TableModel, Integer> crearFiltro(final String texto) {
        RowFilter<TableModel, Integer> filtroFinal = new RowFilter<TableModel, Integer>() {
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                boolean coincide = false;
                int total = entry.getValueCount();
                int i = 0;

                while (i < total) {
                    Object valor = entry.getValue(i);
                    String contenido = "";

                    if (valor != null) {
                        contenido = normalizarTexto(valor.toString());
                    }

                    if (!contenido.isEmpty() && contenido.contains(texto)) {
                        coincide = true;
                    }

                    i++;
                }

                return coincide;
            }
        };

        RowFilter<TableModel, Integer> resultado = filtroFinal;
        if (texto.isEmpty()) {
            resultado = null;
        }

        return resultado;
    }

    private String normalizarTexto(String texto) {
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        normalizado = normalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        normalizado = normalizado.toLowerCase();
        return normalizado;
    }
}
