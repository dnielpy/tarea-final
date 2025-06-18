package frontend.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import frontend.ui.botones.BotonBlanco;

public class CopyDialog extends JDialog {

    public CopyDialog(Window parent, String titulo, String mensaje) {
        super(parent, titulo, DEFAULT_MODALITY_TYPE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(CopyDialog.class.getResource("/fotos/Logo peque.png")));
        initUI(mensaje);
    }

    private void initUI(String mensajeTexto) {
        setSize(500, 250);
        setLocationRelativeTo(getParent());
        getContentPane().setLayout(new BorderLayout(10, 10));
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        JTextPane mensaje = new JTextPane();
        mensaje.setContentType("text/html");
        mensaje.setText("<html><div style='text-align: center; font-family: Arial; font-size: 16pt; color: black;'>"
                + mensajeTexto.replaceAll("\n", "<br>")
                + "</div></html>");
        mensaje.setEditable(false);
        mensaje.setOpaque(false);
        mensaje.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        mensaje.setCaretPosition(0); // empieza desde el principio

        JScrollPane scroll = new JScrollPane(mensaje);
        scroll.setBorder(null);
        scroll.setBackground(Color.WHITE);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        getContentPane().add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.setBackground(Color.WHITE);
        botones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        BotonBlanco btnAceptar = new BotonBlanco("ACEPTAR");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        botones.add(btnAceptar);
        getContentPane().add(botones, BorderLayout.SOUTH);
    }
}