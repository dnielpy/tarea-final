package componentesPropios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class CustomTextField extends JPanel {
    private static final int ALTURA_FIJA = 40;
    private JTextField campoDeTexto;

    public CustomTextField() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        campoDeTexto = new JTextField();
        campoDeTexto.setBackground(Color.WHITE);
        campoDeTexto.setForeground(Color.BLACK);
        campoDeTexto.setFont(new Font("Arial", Font.PLAIN, 16));
        campoDeTexto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(campoDeTexto, BorderLayout.CENTER);

        JSeparator separador = new JSeparator();
        separador.setForeground(Color.BLACK);
        add(separador, BorderLayout.SOUTH);
    }

    // Esto fija la altura pero permite controlar el ancho desde fuera
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, ALTURA_FIJA);
    }

    public String getText() {
        return campoDeTexto.getText();
    }

    public void setText(String text) {
        campoDeTexto.setText(text);
    }

    public JTextField getTextField() {
        return campoDeTexto;
    }
}
