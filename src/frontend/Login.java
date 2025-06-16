package frontend;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JSeparator;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;

import componentesPropios.BotonBlanco;
import componentesPropios.CopyDialog;
import componentesPropios.ImageButtonLabel;
import componentesPropios.InfoDialog;
import componentesPropios.QuestionDialog;
import runner.Auth;
import runner.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import entidades.CMF;

public class Login extends JDialog implements ConstantesFrontend{
	private boolean contrasenaVisible;
	private JTextField campoUsuario;
	private JPasswordField campoContrasenna;
	private ImageButtonLabel ojoIcono;
	private boolean auth;

	public Login(Window parent) {
        super(parent, "Autenticación", ModalityType.APPLICATION_MODAL); // Modal y con título
		this.auth = false;
		setBackground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, 16));
		contrasenaVisible = false;

		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/fotos/Logo peque.png")));
		setTitle("Autenticaci\u00F3n");
		setBounds(100, 100, 800, 510);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parent);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Evita cierre automático

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				QuestionDialog confirmacion = new QuestionDialog(Login.this,
						"Confirmar salida", "¿Estás seguro de que deseas cerrar la aplicación?");
				confirmacion.setVisible(true); // Espera respuesta

				if (confirmacion.esConfirmado()) {
					System.exit(0); // Cierra completamente la aplicación
				}
			}
		});
		inicializarInterfaz();
	}
	
	public void autenticar() {
	    String usuario = campoUsuario.getText();
	    String contrasenna = new String(campoContrasenna.getPassword());

	    if (contrasenna.equals("●●●●●●●●●●") || contrasenna.trim().isEmpty()) {
	        InfoDialog dialogo = new InfoDialog(
	            this.getOwner(),
	            "Error de autenticación",
	            "La contraseña no puede estar vacía"
	        );
	        dialogo.setVisible(true);
	        return;
	    }

	    Auth authManager = new Auth();

	    try {
	        Usuario usuarioAutenticado = authManager.authUser(usuario, contrasenna);

	        // Guardar en la sesión (singleton CMF)
	        CMF.getInstance().setUsuario(usuarioAutenticado);

	        auth = true;
	        iniciarSesion();

	    } catch (Auth.AuthenticationException ex) {
	        InfoDialog dialogo = new InfoDialog(
	            this.getOwner(),
	            "Error de autenticación",
	            ex.getMessage()
	        );
	        dialogo.setVisible(true);
	    }
	}
	
	private void inicializarInterfaz() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(0, 0, 794, 534);
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(null);

		JPanel panelLateral = new JPanel();
		panelLateral.setBackground(COLOR_AZUL);
		panelLateral.setBounds(0, 232, 250, 289);
		panelPrincipal.add(panelLateral);
		panelLateral.setLayout(null);

		ojoIcono = new ImageButtonLabel(new ImageIcon(Login.class.getResource("/fotos/no visible (1).png")));
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
		ojoIcono.setBounds(671, 281, 33, 33);
		panelPrincipal.add(ojoIcono);

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
		
		campoUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        autenticar();
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
		
		campoContrasenna.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        autenticar();
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
		botonAceptar.setToolTipText("Clic para iniciar sesi\u00F3n con los datos introducidos");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticar();
			}
		});
		botonAceptar.setBounds(325, 360, 129, 33);
		panelPrincipal.add(botonAceptar);
		botonAceptar.setLayout(null);
		
		ImageButtonLabel botonInfo = new ImageButtonLabel((ImageIcon) null);
		botonInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrarInformacion();
			}
		});
		botonInfo.setIcon(new ImageIcon(Login.class.getResource("/fotos/Info.png")));
		botonInfo.setText("");
		botonInfo.setBounds(466, 360, 33, 33);
		panelPrincipal.add(botonInfo);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setLayout(null);
		panelLogo.setBackground(new Color(109, 163, 67));
		panelLogo.setBounds(0, 0, 250, 225);
		panelPrincipal.add(panelLogo);
		
		JLabel cartelTipografia = new JLabel();
		cartelTipografia.setIcon(new ImageIcon(Login.class.getResource("/fotos/Tipografia-Peque.png")));
		cartelTipografia.setHorizontalAlignment(SwingConstants.CENTER);
		cartelTipografia.setBounds(0, 129, 250, 62);
		panelLogo.add(cartelTipografia);
		
		JLabel cartelLogo = new JLabel("");
		cartelLogo.setIcon(new ImageIcon(Login.class.getResource("/fotos/Logo peque.png")));
		cartelLogo.setHorizontalAlignment(SwingConstants.CENTER);
		cartelLogo.setBounds(0, 15, 250, 120);
		panelLogo.add(cartelLogo);
		
		JPanel panelBlanco = new JPanel();
		panelBlanco.setBorder(new LineBorder(Color.WHITE, 1, true));
		panelBlanco.setBackground(Color.WHITE);
		panelBlanco.setBounds(21, 15, 208, 189);
		panelLogo.add(panelBlanco);
	}
	
	public void mostrarInformacion() {
	    Usuario usuarioMedico = CMF.getInstance().getMedico().getUser();
	    Usuario usuarioEnfermera = CMF.getInstance().getEnfermera().getUser();

	    String datosMedico = usuarioMedico.getUserName() + " / " + usuarioMedico.getPassword();
	    String datosEnfermera = usuarioEnfermera.getUserName() + " / " + usuarioEnfermera.getPassword();

	    String mensaje = "Para probar el programa inicie sesi\u00F3n con alguno de estos usuarios:<br><br>"
	            + "<b>M\u00E9dico:</b> " + datosMedico + "<br>"
	            + "<b>Enfermera:</b> " + datosEnfermera;

	    new CopyDialog(this, "Informaci\u00F3n", mensaje).setVisible(true);
	}
	
	public void iniciarSesion() {
		InfoDialog dialogo = new InfoDialog(
				this.getOwner(),
				"Inicio de sesión",
				"Inicio de sesión exitoso"
		);
		dialogo.setVisible(true);
		dispose(); 
		
	}
	
	public boolean authenticado() {
	    return auth;
	}
}

