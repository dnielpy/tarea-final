package frontend;

import java.awt.EventQueue;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/fotos/hospital-logo-and-symbols-templa.png")));
		setTitle("Sistema de gesti\u00F3n del CMF");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 0, 300, 254);
		contentPane.add(panelUsuario);
		panelUsuario.setBackground(new Color(109, 163, 67));
		panelUsuario.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/fotos/Logo peque.png")));
		lblNewLabel.setBounds(0, 13, 300, 122);
		panelUsuario.add(lblNewLabel);
		
		JLabel cartelUsuario = new JLabel("USUARIO");
		cartelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		cartelUsuario.setForeground(Color.WHITE);
		cartelUsuario.setFont(new Font("Arial Black", Font.BOLD, 18));
		cartelUsuario.setBounds(31, 133, 237, 26);
		panelUsuario.add(cartelUsuario);
		
		JLabel cartelRol = new JLabel("ROL");
		cartelRol.setHorizontalAlignment(SwingConstants.CENTER);
		cartelRol.setForeground(Color.WHITE);
		cartelRol.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelRol.setBounds(31, 157, 237, 26);
		panelUsuario.add(cartelRol);
		
		BotonMenu botonCerrarSesion = new BotonMenu();
		botonCerrarSesion.setForeground(Color.BLACK);
		botonCerrarSesion.setFont(new Font("Arial", Font.BOLD, 16));
		botonCerrarSesion.setBackground(Color.WHITE);
		botonCerrarSesion.setText("CERRAR SESI\u00D3N");
		botonCerrarSesion.setBounds(59, 196, 173, 29);
		panelUsuario.add(botonCerrarSesion);
		
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
		botonInicio.setBounds(0, 13, 300, 75);
		botonInicio.addMouseListener(this);
		botonInicio.setActivo(true);
		panelLateral.add(botonInicio);
		
		setBotonActivo(botonInicio);
		
		BotonMenu botonPacientes = new BotonMenu((String) null);
		botonPacientes.setText("PACIENTES");
		botonPacientes.setBounds(0, 87, 300, 75);
		botonPacientes.addMouseListener(this);
		panelLateral.add(botonPacientes);
		
		BotonMenu botonHojaCargo = new BotonMenu((String) null);
		botonHojaCargo.setText("HOJAS DE CARGO");
		botonHojaCargo.setBounds(0, 161, 300, 75);
		botonHojaCargo.addMouseListener(this);
		panelLateral.add(botonHojaCargo);
		
		BotonMenu botonVisitas = new BotonMenu((String) null);
		botonVisitas.setText("VISITAS");
		botonVisitas.setBounds(0, 234, 300, 75);
		botonVisitas.addMouseListener(this);
		panelLateral.add(botonVisitas);
		
		BotonMenu botonReportes = new BotonMenu((String) null);
		botonReportes.setText("REPORTES");
		botonReportes.setBounds(0, 308, 300, 75);
		botonReportes.addMouseListener(this);
		panelLateral.add(botonReportes);
		
		panelVentanas = new JPanel();
		panelVentanas.setBounds(305, 0, 796, 673);
		panelPrincipal.add(panelVentanas);
		panelVentanas.setLayout(new CardLayout(0, 0));
		
		VentanaInicio inicio = new VentanaInicio();
		panelVentanas.add(inicio, "INICIO");
		inicio.setLayout(null);
		
		VentanaPacientes pacientes = new VentanaPacientes();
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