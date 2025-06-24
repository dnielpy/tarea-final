package frontend;

import java.awt.Window;

import javax.swing.Icon;
import javax.swing.JDialog;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;

import entidades.personal.Enfermera;
import entidades.personal.PersonalSanitario;
import frontend.ui.PlaceholderTextField;

import java.awt.Font;

import frontend.ui.PlaceholderTextField.InputFormat;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import util.ConstantesFrontend;

public class TarjetaUsuario extends JDialog implements ConstantesFrontend {

	private PlaceholderTextField campoId;
	private PlaceholderTextField campoFechaInicio;
	private PlaceholderTextField campoCI;
	private PlaceholderTextField campoNombre;
	private PlaceholderTextField campoUsuario;
	private PlaceholderTextField campoRol;
	private PlaceholderTextField campoLicenciatura;
	private PlaceholderTextField campoAniosExperiencia;
	private JLabel cartelLicenciatura;
	private JLabel cartelAniosExperiencia;
	private JLabel cartelNombre;
	private JLabel cartelUsuario;
	private PersonalSanitario usuario;
	
	public TarjetaUsuario(Window ancestor, Icon foto, PersonalSanitario usuario) {
		super(ancestor, "Informaci\u00F3n de Usuario", DEFAULT_MODALITY_TYPE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TarjetaUsuario.class.getResource("/fotos/Logo peque.png")));
		setBounds(0, 0, 520, 360);	
		
		this.usuario = usuario;
		iniciarComponentes(foto);
		cargarDatos();
	}
	
	public void iniciarComponentes(Icon foto) {
		
		JLabel cartelInfoUsuario = new JLabel("Informaci\u00F3n usuario");
		cartelInfoUsuario.setOpaque(true);
		cartelInfoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInfoUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInfoUsuario.setBorder(BORDE_PEQUENO);
		cartelInfoUsuario.setBackground(Color.WHITE);
		cartelInfoUsuario.setBounds(35, 13, 205, 26);
		getContentPane().add(cartelInfoUsuario);
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBackground(Color.WHITE);
		panelUsuario.setBounds(22, 24, 466, 275);
		panelUsuario.setBorder(BORDE_GRANDE);
		getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);
		
		cartelNombre = new JLabel("Nombre:");
		cartelNombre.setBounds(25, 159, 70, 25);
		panelUsuario.add(cartelNombre);
		cartelNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		
		campoNombre = new PlaceholderTextField();
		campoNombre.setEditable(false);
		campoNombre.setBounds(97, 159, 345, 25);
		panelUsuario.add(campoNombre);
		campoNombre.setInputFormat(InputFormat.ALPHABETIC);
		
		cartelUsuario = new JLabel("Usuario:");
		cartelUsuario.setBounds(25, 199, 70, 25);
		panelUsuario.add(cartelUsuario);
		cartelUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
		
		campoUsuario = new PlaceholderTextField();
		campoUsuario.setEditable(false);
		campoUsuario.setBounds(97, 199, 345, 25);
		panelUsuario.add(campoUsuario);
		campoUsuario.setInputFormat(InputFormat.ANY);
		
		cartelLicenciatura = new JLabel("Licenciatura:");
		cartelLicenciatura.setBounds(25, 237, 90, 25);
		panelUsuario.add(cartelLicenciatura);
		cartelLicenciatura.setFont(new Font("Arial", Font.PLAIN, 16));
		
		campoLicenciatura = new PlaceholderTextField();
		campoLicenciatura.setEditable(false);
		campoLicenciatura.setBounds(122, 237, 85, 25);
		panelUsuario.add(campoLicenciatura);
		campoLicenciatura.setInputFormat(InputFormat.ALPHABETIC);
		
		cartelAniosExperiencia = new JLabel("A\u00F1os de experiencia:");
		cartelAniosExperiencia.setBounds(222, 237, 150, 25);
		panelUsuario.add(cartelAniosExperiencia);
		cartelAniosExperiencia.setFont(new Font("Arial", Font.PLAIN, 16));
		
		campoAniosExperiencia = new PlaceholderTextField();
		campoAniosExperiencia.setEditable(false);
		campoAniosExperiencia.setBounds(372, 237, 70, 25);
		panelUsuario.add(campoAniosExperiencia);
		campoAniosExperiencia.setInputFormat(InputFormat.NUMERIC);
		
		JLabel imagenUsuario = new JLabel("");
		imagenUsuario.setBounds(25, 25, 110, 110);
		panelUsuario.add(imagenUsuario);
		imagenUsuario.setBorder(BORDE_COMPONENTE);
		imagenUsuario.setIcon(foto);
		
		JLabel cartelId = new JLabel("Identificador:");
		cartelId.setBounds(152, 23, 95, 25);
		panelUsuario.add(cartelId);
		cartelId.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel cartelFechaInicio = new JLabel("Fecha inicio:");
		cartelFechaInicio.setBounds(152, 67, 95, 25);
		panelUsuario.add(cartelFechaInicio);
		cartelFechaInicio.setFont(new Font("Arial", Font.PLAIN, 16));
		
		campoId = new PlaceholderTextField();
		campoId.setBounds(248, 23, 70, 25);
		panelUsuario.add(campoId);
		campoId.setInputFormat(InputFormat.NUMERIC);
		campoId.setEditable(false);
		
		JLabel cartelRol = new JLabel("Rol:");
		cartelRol.setBounds(333, 23, 36, 25);
		panelUsuario.add(cartelRol);
		cartelRol.setFont(new Font("Arial", Font.PLAIN, 16));
		
		campoRol = new PlaceholderTextField();
		campoRol.setEditable(false);
		campoRol.setBounds(367, 23, 76, 25);
		panelUsuario.add(campoRol);
		campoRol.setInputFormat(InputFormat.ALPHABETIC);
		
		campoFechaInicio = new PlaceholderTextField();
		campoFechaInicio.setEditable(false);
		campoFechaInicio.setBounds(247, 67, 196, 25);
		panelUsuario.add(campoFechaInicio);
		campoFechaInicio.setInputFormat(InputFormat.ANY);
		
		JLabel cartelCI = new JLabel("Carn\u00E9 de identidad:");
		cartelCI.setBounds(150, 110, 138, 25);
		panelUsuario.add(cartelCI);
		cartelCI.setFont(new Font("Arial", Font.PLAIN, 16));
		
		campoCI = new PlaceholderTextField();
		campoCI.setEditable(false);
		campoCI.setBounds(303, 110, 140, 25);
		panelUsuario.add(campoCI);
		campoCI.setInputFormat(InputFormat.NUMERIC);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(COLOR_GRIS);
		separator.setBackground(COLOR_GRIS);
		separator.setBounds(12, 148, 442, 7);
		panelUsuario.add(separator);
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBackground(COLOR_VERDE);
		panelVerde.setBounds(0, 0, 514, 70);
		getContentPane().add(panelVerde);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBounds(0, 255, 514, 70);
		panelAzul.setBackground(COLOR_AZUL);
		getContentPane().add(panelAzul);
		
		JPanel panelGris = new JPanel();
		panelGris.setBounds(0, 78, 514, 170);
		panelGris.setBackground(COLOR_GRIS_CLARO);
		getContentPane().add(panelGris);
	}
	
	public void cargarDatos() {
		campoId.setText(String.valueOf(usuario.getIdentificador()));
		campoCI.setText(usuario.getCI());
		campoNombre.setText(usuario.getNombreYApellidos());
		campoRol.setText(usuario.getUser().getRole().toString().toLowerCase());
		campoUsuario.setText(usuario.getUser().getUserName());
		campoFechaInicio.setText(usuario.getFechaInicioCMFFormateada());
		
		if (usuario instanceof Enfermera) {
			campoLicenciatura.setText(((Enfermera)usuario).getLicenciatura() ? "Tiene" : "No tiene");
			campoAniosExperiencia.setText(String.valueOf(((Enfermera)usuario).getExperiencia()));
		} else {
			campoAniosExperiencia.setVisible(false);
			campoLicenciatura.setVisible(false);
			cartelAniosExperiencia.setVisible(false);
			cartelLicenciatura.setVisible(false);
			cartelUsuario.setBounds(25, 216, 70, 25);
			campoUsuario.setBounds(97, 216, 345, 25);
			cartelNombre.setBounds(25, 176, 70, 25);
			campoNombre.setBounds(97, 176, 345, 25);
		}
	}
}
