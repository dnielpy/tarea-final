package componentesPropios;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoDialog extends JDialog {

    public InfoDialog(Frame parent, String titulo, String mensaje) {
        super(parent, titulo, true);
        initUI(mensaje);
    }

    private void initUI(String mensajeTexto) {
    	setSize(500, 200);
    	setLocationRelativeTo(getParent());
    	setLayout(new BorderLayout(10, 10));
    	setResizable(false);
    	getContentPane().setBackground(Color.WHITE);

    	JLabel mensaje = new JLabel("<html><div style='text-align: center;'>" + mensajeTexto.replaceAll("\n", "<br>") + "</div></html>");
    	mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
    	mensaje.setForeground(Color.BLACK);
    	mensaje.setHorizontalAlignment(SwingConstants.CENTER);
    	mensaje.setOpaque(false);

    	add(mensaje, BorderLayout.CENTER);

    	JPanel botones = new JPanel();
    	botones.setBackground(Color.WHITE); // fondo blanco
    	botones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));


    	BotonBlanco btnAceptar = new BotonBlanco("ACEPTAR");
    	btnAceptar.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    		}
    	});
    	botones.add(btnAceptar);

    	add(botones, BorderLayout.SOUTH);
    }
}


