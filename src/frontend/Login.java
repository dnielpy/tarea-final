package frontend;

import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JSeparator;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;

import componentesPropios.BotonBlanco;
import componentesPropios.InfoDialog;
import runner.Auth;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

public class Login extends JFrame {
	private boolean contrasenaVisible;
	private JTextField campoUsuario;
	private JPasswordField campoContrasenna;
	private boolean auth ;

	public Login() {
		this.auth = false;
		setBackground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 16));
		contrasenaVisible = false;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/fotos/Logo peque.png")));
		setTitle("Autenticaci\u00F3n");
		setBounds(100, 100, 800, 510);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(0, 0, 794, 534);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		JPanel panelLateral = new JPanel();
		panelLateral.setBackground(new Color(0, 171, 227));
		panelLateral.setBounds(0, 0, 250, 600);
		panelPrincipal.add(panelLateral);
		panelLateral.setLayout(null);

		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(0, 0, 250, 225);
		panelLateral.add(panelLogo);
		panelLogo.setBackground(new Color(109, 163, 67));
		panelLogo.setLayout(null);

		JLabel imagenTipografia = new JLabel();
		imagenTipografia.setHorizontalAlignment(SwingConstants.CENTER);
		imagenTipografia.setIcon(new ImageIcon(Login.class.getResource("/fotos/Tipografia-Peque.png")));
		imagenTipografia.setBounds(0, 129, 250, 62);
		panelLogo.add(imagenTipografia);

		JLabel cartelLogo = new JLabel("");
		cartelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		cartelLogo.setBounds(0, 15, 250, 120);
		panelLogo.add(cartelLogo);
		cartelLogo.setIcon(new ImageIcon(Login.class.getResource("/fotos/Logo peque.png")));

		JPanel panelBlancoLogo = new JPanel();
		panelBlancoLogo.setBackground(Color.WHITE);
		panelBlancoLogo.setBounds(21, 15, 208, 189);
		panelLogo.add(panelBlancoLogo);

		JPanel panelSeparadorLogo = new JPanel();
		panelSeparadorLogo.setBackground(Color.WHITE);
		panelSeparadorLogo.setBounds(-30, 224, 280, 10);
		panelLateral.add(panelSeparadorLogo);

		final JLabel ojoIcono = new JLabel("");
		ojoIcono.setToolTipText("Clic para ver u ocultar contrase\u00F1a");
		ojoIcono.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!contrasenaVisible && !campoContrasenna.getText().equals("\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022")) {
                	ojoIcono.setIcon(new ImageIcon(Login.class.getResource("/fotos/visible (1).png")));
            		campoContrasenna.setEchoChar((char) 0);  
            		contrasenaVisible = true;
                } else {
                	ojoIcono.setIcon(new ImageIcon(Login.class.getResource("/fotos/no visible (1).png")));
                	campoContrasenna.setEchoChar('\u2022');  
                	contrasenaVisible = false;
                }
            }
        });
		ojoIcono.setBackground(SystemColor.menu);
		ojoIcono.setIcon(new ImageIcon(Login.class.getResource("/fotos/no visible (1).png")));
		ojoIcono.setBounds(671, 281, 33, 33);
		panelPrincipal.add(ojoIcono);
		ojoIcono.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel cartelUsuario = new JLabel("USUARIO");
		cartelUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelUsuario.setBounds(325, 139, 92, 26);
		panelPrincipal.add(cartelUsuario);

		campoUsuario = new JTextField();
		campoUsuario.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (campoUsuario.getText().equals("Ingrese el nombre de usuario")) {
                	campoUsuario.setText("");
                	campoUsuario.setForeground(Color.BLACK);
                	campoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
                }
            }

            public void focusLost(FocusEvent e) {
                if (campoUsuario.getText().isEmpty()) {
                	campoUsuario.setForeground(Color.LIGHT_GRAY);
                	campoUsuario.setText("Ingrese el nombre de usuario");
                	campoUsuario.setFont(new Font("Arial", Font.ITALIC, 16));
                }
            }
        });
		campoUsuario.setForeground(SystemColor.controlShadow);
		campoUsuario.setText("Ingrese el nombre de usuario");
		campoUsuario.setFont(new Font("Arial", Font.ITALIC, 16));
		campoUsuario.setBackground(Color.WHITE);
		campoUsuario.setBounds(325, 169, 342, 32);
		panelPrincipal.add(campoUsuario);
		campoUsuario.setColumns(10);
		campoUsuario.setBorder(null);

		JLabel cartelContrasenna = new JLabel("CONTRASE\u00D1A");
		cartelContrasenna.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelContrasenna.setBounds(325, 249, 144, 26);
		panelPrincipal.add(cartelContrasenna);

		campoContrasenna = new JPasswordField();
		campoContrasenna.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (campoContrasenna.getText().equals("\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022")) {
                	campoContrasenna.setText("");
                	campoContrasenna.setForeground(Color.BLACK);
                }
            }

			public void focusLost(FocusEvent e) {
                if (campoContrasenna.getText().isEmpty()) {
                	campoContrasenna.setForeground(Color.LIGHT_GRAY);
                	campoContrasenna.setText("\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022");
                }
            }
        });
		campoContrasenna.setForeground(SystemColor.controlShadow);
		campoContrasenna.setText("\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022");
		campoContrasenna.setFont(new Font("Arial", Font.PLAIN, 16));
		campoContrasenna.setBackground(Color.WHITE);
		campoContrasenna.setBounds(325, 281, 342, 32);
		panelPrincipal.add(campoContrasenna);
		campoContrasenna.setColumns(10);
		campoContrasenna.setBorder(null);

		JLabel cartelIniciarSesion = new JLabel("INICIAR SESI\u00D3N");
		cartelIniciarSesion.setFont(new Font("Arial Black", Font.BOLD, 21));
		cartelIniciarSesion.setBounds(325, 82, 210, 26);
		panelPrincipal.add(cartelIniciarSesion);

		JSeparator separatorUsuario = new JSeparator();
		separatorUsuario.setBackground(SystemColor.menu);
		separatorUsuario.setBounds(325, 202, 379, 2);
		panelPrincipal.add(separatorUsuario);

		JSeparator separatorContrasenna = new JSeparator();
		separatorContrasenna.setBackground(SystemColor.menu);
		separatorContrasenna.setBounds(325, 315, 379, 2);
		panelPrincipal.add(separatorContrasenna);

		BotonBlanco botonAceptar = new BotonBlanco("ACEPTAR");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticar();
			}
		});
		botonAceptar.setForeground(new Color(0, 0, 0));
		botonAceptar.setBackground(SystemColor.menu);
		botonAceptar.setBounds(325, 361, 129, 43);
		panelPrincipal.add(botonAceptar);
		botonAceptar.setLayout(null);

	}
	
	public void autenticar() {
		String usuario = campoUsuario.getText();
    	String contrasenna = new String(campoContrasenna.getPassword());

    	Auth authManager = new Auth();

    	try {
    		if (authManager.authUser(usuario, contrasenna)) {
    			auth = true;
    			iniciarSesion();
    		}
    	} catch (Auth.AuthenticationException ex) {
    		InfoDialog dialogo = new InfoDialog(
    				null,
    				"Error de autenticación",
    				ex.getMessage()
    		);
    		dialogo.setVisible(true);
    	}
	}
	
	public void iniciarSesion() {
		InfoDialog dialogo = new InfoDialog(
				null,
				"Inicio de sesión",
				"Inicio de sesión exitoso"
		);
		dialogo.setVisible(true);
		dispose(); 
		Principal principal = new Principal();
		principal.setVisible(true);
	}
	
	public boolean getAuth(){
		return this.auth;
	}
}

