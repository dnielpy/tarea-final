package componentesPropios;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TextDialog extends JDialog {
	
	private JTextArea textArea;
	private boolean confirmado;

	public TextDialog(JFrame parent, String titulo, String mensaje) {
        super(parent, titulo, true);
        initUI(mensaje);
    }
	
	/**
	 * @wbp.parser.constructor
	 */
	public TextDialog(JDialog parent, String titulo, String mensaje) {
        super(parent, titulo, true);
        initUI(mensaje);
    }

    private void initUI(String mensajeTexto) {
    	confirmado = false;
    	setSize(500, 230);
    	setLocationRelativeTo(getParent());
    	setResizable(false);
    	getContentPane().setBackground(Color.WHITE);
    	getContentPane().setLayout(null);

    	JLabel mensaje = new JLabel("<html><div style='text-align: center;'>" + mensajeTexto.replaceAll("\n", "<br>") + "</div></html>");
    	mensaje.setHorizontalAlignment(SwingConstants.CENTER);
    	mensaje.setBounds(43, 26, 402, 20);
    	mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
    	mensaje.setForeground(Color.BLACK);
    	mensaje.setOpaque(false);

    	getContentPane().add(mensaje);

    	JPanel botones = new JPanel();
    	botones.setBounds(0, 144, 494, 51);
    	botones.setBackground(Color.WHITE);

    	BotonBlanco btnAceptar = new BotonBlanco("ACEPTAR");
    	btnAceptar.setBounds(91, 5, 136, 30);
    	btnAceptar.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			confirmado = true;
    			dispose();
    		}
    	});
    	botones.setLayout(null);
    	botones.add(btnAceptar);

    	BotonBlanco botonCancelar = new BotonBlanco("ACEPTAR");
    	botonCancelar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    		}
    	});
    	botonCancelar.setBounds(259, 5, 136, 30);
    	botonCancelar.setText("CANCELAR");
    	botones.add(botonCancelar);

    	getContentPane().add(botones);

    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(43, 58, 402, 66);
    	getContentPane().add(scrollPane);

    	textArea = new JTextArea();
    	textArea.setFont(new Font("Arial", Font.PLAIN, 16));
    	scrollPane.setViewportView(textArea);
    }

    public boolean isConfirmado() {
    	return confirmado;
    }

    public String getTextoIngresado() {
    	return textArea.getText().trim();
    }

}
