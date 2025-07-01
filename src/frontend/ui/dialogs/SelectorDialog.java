package frontend.ui.dialogs;

import javax.swing.*;

import util.ConstantesFrontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import frontend.ui.ScrollPaneModerno;
import frontend.ui.botones.BotonBlanco;

public class SelectorDialog extends JDialog implements ConstantesFrontend {

    private static final long serialVersionUID = 1L;
    private boolean confirmado = false;
    private String seleccionado;
    private JComboBox<String> comboBox;

    public SelectorDialog(Window parent, String titulo, String mensaje, List<String> opciones) {
        super(parent, titulo, DEFAULT_MODALITY_TYPE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(SelectorDialog.class.getResource("/fotos/Logo peque.png")));
        initUI(mensaje, opciones);
    }

    private void initUI(String mensajeTexto, List<String> opciones) {
        setSize(500, 200);
        setLocationRelativeTo(getParent());
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);

        JLabel mensaje = new JLabel("<html><div style='text-align: center;'>" + mensajeTexto.replaceAll("\n", "<br>") + "</div></html>");
        mensaje.setBounds(0, 0, 494, 40);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(mensaje);

        comboBox = new JComboBox<String>();
        comboBox.setBackground(SystemColor.menu);
        comboBox.setOpaque(true);
        comboBox.setBorder(BORDE_COMPONENTE);
        comboBox.setFocusable(false);
        comboBox.setBounds(68, 53, 351, 40);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        for (int i = 0; i < opciones.size(); i++) {
            comboBox.addItem(opciones.get(i));
        }

        getContentPane().add(comboBox);

        // Ejecuta la personalización después de la creación de UI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                personalizarScrollCombo(comboBox);
            }
        });

        JPanel botones = new JPanel();
        botones.setBounds(0, 124, 494, 40);
        botones.setBackground(Color.WHITE);
        botones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        BotonBlanco btnAceptar = new BotonBlanco("ACEPTAR");
        BotonBlanco btnCancelar = new BotonBlanco("CANCELAR");

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmado = true;
                seleccionado = (String) comboBox.getSelectedItem();
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmado = false;
                dispose();
            }
        });

        botones.add(btnAceptar);
        botones.add(btnCancelar);
        getContentPane().add(botones);
    }

    private void personalizarScrollCombo(JComboBox<String> comboBox) {
        Object comp = comboBox.getUI().getAccessibleChild(comboBox, 0);

        boolean scrollEncontrado = false;
        JScrollPane scrollOriginal = null;

        if (comp instanceof JPopupMenu) {
            JPopupMenu popup = (JPopupMenu) comp;

            // Fuerza el tamaño del popup al del comboBox
            popup.setPreferredSize(new Dimension(comboBox.getWidth(), popup.getPreferredSize().height));
            popup.setMaximumSize(new Dimension(comboBox.getWidth(), Integer.MAX_VALUE));

            Component[] hijos = popup.getComponents();

            for (int i = 0; i < hijos.length; i++) {
                if (!scrollEncontrado && hijos[i] instanceof JScrollPane) {
                    scrollOriginal = (JScrollPane) hijos[i];
                    scrollEncontrado = true;
                }
            }

            if (scrollOriginal != null) {
                Component vista = scrollOriginal.getViewport().getView();
                ScrollPaneModerno personalizado = new ScrollPaneModerno(vista);
                personalizado.setBorder(scrollOriginal.getBorder());
                popup.remove(scrollOriginal);
                popup.add(personalizado);
            }
        }
    }

    public boolean esConfirmado() {
        return confirmado;
    }

    public String getSeleccionado() {
        return seleccionado;
    }
}
