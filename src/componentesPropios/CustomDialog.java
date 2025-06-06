package componentesPropios;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog {
    private boolean confirmado = false;

    public CustomDialog(Frame parent, String titulo, String mensaje, boolean esConfirmacion) {
        super(parent, titulo, true);
        initUI(mensaje, esConfirmacion);
    }

    private void initUI(String mensajeTexto, boolean esConfirmacion) {
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

        if (esConfirmacion) {
            BotonBlanco btnSi = new BotonBlanco("Sí");
            BotonBlanco btnNo = new BotonBlanco("No");

            btnSi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					confirmado = true;
	                dispose();
					
				}
    		});
            
            btnNo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					confirmado = false;
	                dispose();
					
				}
    		});

            botones.add(btnSi);
            botones.add(btnNo);
        } else {
        	BotonBlanco btnAceptar = new BotonBlanco("Aceptar");
            btnAceptar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
	                dispose();
					
				}
    		});
            botones.add(btnAceptar);
        }

        add(botones, BorderLayout.SOUTH);
    }

    public boolean esConfirmado() {
        return confirmado;
    }
}


