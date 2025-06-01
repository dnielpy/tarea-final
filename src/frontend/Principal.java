package frontend;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import componentesPropios.BotonMenu;

import javax.swing.SwingConstants;

import entidades.CMF;

import java.awt.CardLayout;

public class Principal extends JFrame implements MouseListener {
	
	private JPanel panelVentanas;
	private BotonMenu botonActivo;
	private JPanel contentPane;
	
	
	public BotonMenu getBotonActivo() {
		return botonActivo;
	}

	public void setBotonActivo(BotonMenu botonActivo) {
		this.botonActivo = botonActivo;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		
		//Inicializacion del CMF
		CMF cmf = new CMF(1, "Policlinico Alberro Cotorro", "Esteban Marrero Bermudez");
		cmf.cargarDatos();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/fotos/hospital-logo-and-symbols-templa.png")));
		setTitle("Sistema de gesti\u00F3n del CMF");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 0, 300, 254);
		contentPane.add(panelUsuario);
		panelUsuario.setBackground(new Color(109, 163, 67));
		panelUsuario.setLayout(null);
		
		JLabel imagenUsuario = new JLabel("");
		imagenUsuario.setIcon(new ImageIcon(Principal.class.getResource("/fotos/medico.png")));
		imagenUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		imagenUsuario.setBounds(0, 27, 300, 122);
		panelUsuario.add(imagenUsuario);
		
		JLabel cartelUsuario = new JLabel("USUARIO");
		cartelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		cartelUsuario.setForeground(Color.WHITE);
		cartelUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelUsuario.setBounds(31, 147, 237, 26);
		panelUsuario.add(cartelUsuario);
		
		JLabel cartelRol = new JLabel("ROL");
		cartelRol.setHorizontalAlignment(SwingConstants.CENTER);
		cartelRol.setForeground(Color.WHITE);
		cartelRol.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelRol.setBounds(31, 168, 237, 26);
		panelUsuario.add(cartelRol);
		
		JButton botonIniciarSesion = new JButton();
		botonIniciarSesion.setText("CERRAR SESI\u00D3N");
		botonIniciarSesion.setForeground(Color.BLACK);
		botonIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 16));
		botonIniciarSesion.setBackground(Color.WHITE);
		botonIniciarSesion.setBounds(61, 195, 173, 29);
		panelUsuario.add(botonIniciarSesion);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(0, 0, 1137, 689);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JPanel panelLateral = new JPanel();
		panelLateral.setBounds(0, 262, 300, 491);
		panelPrincipal.add(panelLateral);
		panelLateral.setBackground(new Color(0, 171, 227));
		panelLateral.setLayout(null);
		
		BotonMenu botonInicio = new BotonMenu((String) null);
		botonInicio.setText("INICIO");
		botonInicio.setBounds(0, 16, 300, 75);
		botonInicio.addMouseListener(this);
		botonInicio.setActivo(true);
		panelLateral.add(botonInicio);
		
		setBotonActivo(botonInicio);
		
		BotonMenu botonPacientes = new BotonMenu((String) null);
		botonPacientes.setActivo(false);
		botonPacientes.setText("PACIENTES");
		botonPacientes.setBounds(0, 90, 300, 75);
		botonPacientes.addMouseListener(this);
		panelLateral.add(botonPacientes);
		
		BotonMenu botonHojaCargo = new BotonMenu((String) null);
		botonHojaCargo.setActivo(false);
		botonHojaCargo.setText("HOJAS DE CARGO");
		botonHojaCargo.setBounds(0, 164, 300, 75);
		botonHojaCargo.addMouseListener(this);
		panelLateral.add(botonHojaCargo);
		
		BotonMenu botonVisitas = new BotonMenu((String) null);
		botonVisitas.setActivo(false);
		botonVisitas.setText("VISITAS");
		botonVisitas.setBounds(0, 237, 300, 75);
		botonVisitas.addMouseListener(this);
		panelLateral.add(botonVisitas);
		
		BotonMenu botonReportes = new BotonMenu((String) null);
		botonReportes.setActivo(false);
		botonReportes.setText("REPORTES");
		botonReportes.setBounds(0, 311, 300, 75);
		botonReportes.addMouseListener(this);
		panelLateral.add(botonReportes);
		
		panelVentanas = new JPanel();
		panelVentanas.setBounds(305, 0, 796, 673);
		panelPrincipal.add(panelVentanas);
		panelVentanas.setLayout(new CardLayout(0, 0));
		
		VentanaInicio inicio = new VentanaInicio();
		panelVentanas.add(inicio, "INICIO");
		inicio.setLayout(null);
		
		VentanaPacientes pacientes = new VentanaPacientes(cmf);
		panelVentanas.add(pacientes, "PACIENTES");
		pacientes.setLayout(null);
		
		VentanaVisitas visitas = new VentanaVisitas();
		panelVentanas.add(visitas, "VISITAS");
		visitas.setLayout(null);
		
		VentanaHojasDeCargo hojaDeCargo = new VentanaHojasDeCargo();
		panelVentanas.add(hojaDeCargo, "HOJAS DE CARGO");
		hojaDeCargo.setLayout(null);
		
		VentanaReportes reportes = new VentanaReportes();
		panelVentanas.add(reportes, "REPORTES");
		reportes.setLayout(null);
	}

	public void mouseClicked(MouseEvent e) {
		BotonMenu boton = (BotonMenu)e.getSource();
		if (!boton.estaActivo()) {
			getBotonActivo().setActivo(false);
			boton.setActivo(true);
			setBotonActivo(boton);
			
			CardLayout card = (CardLayout)(panelVentanas.getLayout());
			card.show(panelVentanas, boton.getText());
		
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		if (!((BotonMenu)e.getSource()).estaActivo()) {
			((BotonMenu)e.getSource()).setBackground(new Color(0, 192, 255));	
		}	
	}

	public void mouseExited(MouseEvent e) {
		if (!((BotonMenu)e.getSource()).estaActivo()) {
			((BotonMenu)e.getSource()).setBackground(new Color(0, 171, 227));
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}