package componentesPropios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class QuestionDialog extends JDialog {
	
	private boolean confirmado = false;

	public QuestionDialog(Window parent, String titulo, String mensaje) {
		super(parent, titulo, DEFAULT_MODALITY_TYPE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(QuestionDialog.class.getResource("/fotos/Logo peque.png")));
        initUI(mensaje);
	}
	
	private void initUI(String mensajeTexto) {
		setSize(500, 200);
		setLocationRelativeTo(getParent());
		getContentPane().setLayout(new BorderLayout(10, 10));
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);

		JLabel mensaje = new JLabel("<html><div style='text-align: center;'>" + mensajeTexto.replaceAll("\n", "<br>") + "</div></html>");
		mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
		mensaje.setForeground(Color.BLACK);
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setOpaque(false);

		getContentPane().add(mensaje, BorderLayout.CENTER);

		JPanel botones = new JPanel();
		botones.setBackground(Color.WHITE); // fondo blanco
		botones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

		BotonBlanco btnSi = new BotonBlanco("SÍ");
		BotonBlanco btnNo = new BotonBlanco("NO");

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

		getContentPane().add(botones, BorderLayout.SOUTH);
	}

	public boolean esConfirmado() {
		return confirmado;
	}
}
