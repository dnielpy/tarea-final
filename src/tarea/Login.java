package tarea;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;

import java.awt.Color;

import javax.swing.JDesktopPane;
import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Canvas;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JCheckBox;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.Button;

import javax.swing.JSeparator;

import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

public class Login extends JFrame {
	private boolean contrasenaVisible;
	private JTextField campoUsuario;
	private JPasswordField campoContrasenna;

	public Login() {
		contrasenaVisible = false;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/fotos/hospital-logo-and-symbols-templa.png")));
		setTitle("Autentificaci\u00F3n");
		setBounds(100, 100, 800, 510);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 794, 450);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panelLateral = new JPanel();
		panelLateral.setBackground(new Color(164, 200, 225));
		panelLateral.setBounds(0, 0, 250, 600);
		panel.add(panelLateral);
		panelLateral.setLayout(null);
		
		JLabel fotoLateral = new JLabel("New label");
		fotoLateral.setIcon(new ImageIcon(Login.class.getResource("/fotos/1076688 (1).png")));
		fotoLateral.setBounds(0, -83, 250, 601);
		panelLateral.add(fotoLateral);
		
		final JLabel ojoIcono = new JLabel("");
		ojoIcono.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!contrasenaVisible && !campoContrasenna.getText().equals("\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF")) {
                	ojoIcono.setIcon(new ImageIcon(Login.class.getResource("/fotos/visible (1).png")));
            		campoContrasenna.setEchoChar((char) 0);  // Mostrar contraseña
            		contrasenaVisible = true;
                } else {
                	ojoIcono.setIcon(new ImageIcon(Login.class.getResource("/fotos/no visible (1).png")));
                	campoContrasenna.setEchoChar('•');  // Ocultar contraseña
                	contrasenaVisible = false;
                }
            }
        });
		ojoIcono.setBackground(SystemColor.menu);
		ojoIcono.setIcon(new ImageIcon(Login.class.getResource("/fotos/no visible (1).png")));
		ojoIcono.setBounds(671, 281, 33, 32);
		panel.add(ojoIcono);
		ojoIcono.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JLabel cartelUsuario = new JLabel("USUARIO");
		cartelUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		cartelUsuario.setBounds(325, 139, 92, 26);
		panel.add(cartelUsuario);
		
		campoUsuario = new JTextField();
		campoUsuario.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (campoUsuario.getText().equals("Ingrese el nombre de usuario")) {
                	campoUsuario.setText("");
                	campoUsuario.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (campoUsuario.getText().isEmpty()) {
                	campoUsuario.setForeground(Color.LIGHT_GRAY);
                	campoUsuario.setText("Ingrese el nombre de usuario");
                }
            }
        });
		campoUsuario.setForeground(SystemColor.controlShadow);
		campoUsuario.setText("Ingrese el nombre de usuario");
		campoUsuario.setFont(new Font("Arial", Font.ITALIC, 16));
		campoUsuario.setBackground(Color.WHITE);
		campoUsuario.setBounds(325, 169, 342, 32);
		panel.add(campoUsuario);
		campoUsuario.setColumns(10);
		campoUsuario.setBorder(null);
		
		JLabel cartelContrasena = new JLabel("CONTRASE\u00D1A");
		cartelContrasena.setFont(new Font("Arial", Font.BOLD, 18));
		cartelContrasena.setBounds(325, 249, 144, 26);
		panel.add(cartelContrasena);
		
		campoContrasenna = new JPasswordField();
		campoContrasenna.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (campoContrasenna.getText().equals("\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF")) {
                	campoContrasenna.setText("");
                	campoContrasenna.setForeground(Color.BLACK);
                }
            }

			public void focusLost(FocusEvent e) {
                if (campoContrasenna.getText().isEmpty()) {
                	campoContrasenna.setForeground(Color.LIGHT_GRAY);
                	campoContrasenna.setText("\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF");
                }
            }
        });
		campoContrasenna.setForeground(SystemColor.controlShadow);
		campoContrasenna.setText("\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF\u25CF");
		campoContrasenna.setFont(new Font("Arial", Font.ITALIC, 16));
		campoContrasenna.setBackground(Color.WHITE);
		campoContrasenna.setBounds(325, 281, 342, 32);
		panel.add(campoContrasenna);
		campoContrasenna.setColumns(10);
		campoContrasenna.setBorder(null);
		
		JLabel cartelIniciarSesion = new JLabel("INICIAR SESI\u00D3N");
		cartelIniciarSesion.setFont(new Font("Arial Black", Font.BOLD, 21));
		cartelIniciarSesion.setBounds(325, 82, 210, 26);
		panel.add(cartelIniciarSesion);
		
		JSeparator separatorUsuario = new JSeparator();
		separatorUsuario.setBackground(SystemColor.menu);
		separatorUsuario.setBounds(325, 202, 379, 2);
		panel.add(separatorUsuario);
		
		JSeparator separatorContraseña = new JSeparator();
		separatorContraseña.setBackground(SystemColor.menu);
		separatorContraseña.setBounds(325, 315, 379, 2);
		panel.add(separatorContraseña);
		
		Panel botonAceptar = new Panel();
		botonAceptar.setBackground(SystemColor.menu);
		botonAceptar.setBounds(325, 361, 115, 43);
		panel.add(botonAceptar);
		botonAceptar.setLayout(null);
		
		JLabel cartelAceptar = new JLabel("ACEPTAR");
		cartelAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		cartelAceptar.setBounds(0, 0, 115, 43);
		botonAceptar.add(cartelAceptar);
		cartelAceptar.setFont(new Font("Arial", Font.BOLD, 18));
		cartelAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
