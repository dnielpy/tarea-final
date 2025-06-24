package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import frontend.panelesPrincipales.VentanaAnalisis;
import frontend.panelesPrincipales.VentanaHojasDeCargo;
import frontend.panelesPrincipales.VentanaInicio;
import frontend.panelesPrincipales.VentanaPacientes;
import frontend.panelesPrincipales.VentanaReportes;
import frontend.panelesPrincipales.VentanaVisitas;
import frontend.ui.botones.*;
import frontend.ui.dialogs.*;

import javax.swing.SwingConstants;

import entidades.CMF;
import entidades.personal.Enfermera;
import entidades.personal.Medico;
import entidades.personal.PersonalSanitario;
import runner.Runner;
import util.ConstantesFrontend;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class Principal extends JFrame implements MouseListener, ConstantesFrontend {

	private JLabel imagenUsuario;
	private JLabel cartelRol;
	private JLabel cartelUsuario;
	private JPanel panelVentanas;
    private BotonMenu botonActivo;
    private JPanel contentPane;
    private BotonBlanco botonCerrarSesion;
    private BotonMenu botonVisitasOAnalisis;
    private CMF cmf;

    public BotonMenu getBotonActivo() {
        return botonActivo;
    }

    public void setBotonActivo(BotonMenu botonActivo) {
        this.botonActivo = botonActivo;
    }
    
    public Principal() {
        cmf = CMF.getInstance();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/fotos/Logo peque.png")));
        setTitle("Sistema de gesti\u00F3n del CMF");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(0, 0, 1100, 700);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        inicializarComponentes();
        
        // Solicitar foco después de que todo esté visible
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                botonCerrarSesion.requestFocusInWindow();
            }
        });
        
        cargarUsuario();
    };
    
    private void inicializarComponentes() {
    	JPanel panelUsuario = new JPanel();
        panelUsuario.setBounds(0, 0, 300, 254);
        contentPane.add(panelUsuario);
        panelUsuario.setBackground(COLOR_VERDE);
        panelUsuario.setLayout(null);

        imagenUsuario = new ImageButtonLabel(new ImageIcon(Principal.class.getResource("/fotos/Logo peque.png")));
        imagenUsuario.setToolTipText("Clic para acceder a su informaci\u00F3n personal");
        imagenUsuario.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		TarjetaUsuario tarjeta = new TarjetaUsuario(Principal.this, imagenUsuario.getIcon(), cmf.getEntitytyUsuario());
        		tarjeta.setLocationRelativeTo(Principal.this);
        		tarjeta.setVisible(true);
        	}
        });

        imagenUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        imagenUsuario.setBounds(92, 30, 110, 110);
        panelUsuario.add(imagenUsuario);
        
        cartelUsuario = new JLabel("USUARIO");
        cartelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        cartelUsuario.setForeground(Color.WHITE);
        cartelUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        cartelUsuario.setBounds(31, 147, 237, 26);
        panelUsuario.add(cartelUsuario);

        cartelRol = new JLabel("ROL");
        cartelRol.setHorizontalAlignment(SwingConstants.CENTER);
        cartelRol.setForeground(Color.WHITE);
        cartelRol.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelRol.setBounds(31, 168, 237, 26);
        panelUsuario.add(cartelRol);

        botonCerrarSesion = new BotonBlanco("CERRAR SESIÓN");
        botonCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
        botonCerrarSesion.setBounds(55, 200, 185, 30);
        panelUsuario.add(botonCerrarSesion);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(0, 0, 1137, 689);
        contentPane.add(panelPrincipal);
        panelPrincipal.setLayout(null);

        JPanel panelLateral = new JPanel();
        panelLateral.setBounds(0, 262, 300, 491);
        panelPrincipal.add(panelLateral);
        panelLateral.setBackground(COLOR_AZUL);
        panelLateral.setLayout(null);

        BotonMenu botonInicio = new BotonMenu("INICIO");
        botonInicio.setToolTipText("Clic para ver datos generales del consultorio");
        botonInicio.setBounds(0, 16, 300, 75);
        botonInicio.addMouseListener(this);
        botonInicio.setActivo(true);
        panelLateral.add(botonInicio);

        setBotonActivo(botonInicio);

        BotonMenu botonPacientes = new BotonMenu("PACIENTES");
        botonPacientes.setActivo(false);
        botonPacientes.setBounds(0, 90, 300, 75);
        botonPacientes.addMouseListener(this);
        panelLateral.add(botonPacientes);

        BotonMenu botonHojaCargo = new BotonMenu("HOJAS DE CARGO");
        botonHojaCargo.setActivo(false);
        botonHojaCargo.setBounds(0, 164, 300, 75);
        botonHojaCargo.addMouseListener(this);
        panelLateral.add(botonHojaCargo);

        botonVisitasOAnalisis = new BotonMenu((String) null);
        botonVisitasOAnalisis.setActivo(false);
        botonVisitasOAnalisis.setBounds(0, 237, 300, 75);
        botonVisitasOAnalisis.addMouseListener(this);
        panelLateral.add(botonVisitasOAnalisis);

        BotonMenu botonReportes = new BotonMenu("REPORTES");
        botonReportes.setActivo(false);
        botonReportes.setBounds(0, 311, 300, 75);
        botonReportes.addMouseListener(this);
        panelLateral.add(botonReportes);

        panelVentanas = new JPanel();
        panelVentanas.setBackground(Color.WHITE);
        panelVentanas.setBounds(305, 0, 795, 700);
        panelPrincipal.add(panelVentanas);
        panelVentanas.setLayout(new CardLayout(0, 0));

        VentanaInicio inicio = new VentanaInicio();
        panelVentanas.add(inicio, "INICIO");
        inicio.setLayout(null);

        VentanaPacientes pacientes = new VentanaPacientes();
        panelVentanas.add(pacientes, "PACIENTES");
        pacientes.setLayout(null);

        VentanaHojasDeCargo hojaDeCargo = new VentanaHojasDeCargo();
        panelVentanas.add(hojaDeCargo, "HOJAS DE CARGO");
        hojaDeCargo.setLayout(null);

        VentanaReportes reportes = new VentanaReportes();
        panelVentanas.add(reportes, "REPORTES");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mostrarConfirmacionSalida();
            }
        });
    }
    
    private void cargarUsuario() {
    	PersonalSanitario personaAutenticada = cmf.getEntitytyUsuario();
        if (personaAutenticada != null) {
            String nombre = personaAutenticada.getNombre() + " " + personaAutenticada.getPrimerApellido();

            if (personaAutenticada instanceof Medico) {
                imagenUsuario.setIcon(new ImageIcon(Principal.class.getResource("/fotos/medico.png")));
                botonVisitasOAnalisis.setText("VISITAS");
                VentanaVisitas visitas = new VentanaVisitas();
                panelVentanas.add(visitas, "VISITAS");
                visitas.setLayout(null);
                cartelRol.setText("MÉDICO");
            } else if (personaAutenticada instanceof Enfermera) {
                imagenUsuario.setIcon(new ImageIcon(Principal.class.getResource("/fotos/enfermera.png")));
                botonVisitasOAnalisis.setText("ANÁLISIS");
                VentanaAnalisis analisis = new VentanaAnalisis();
                panelVentanas.add(analisis, "ANÁLISIS");
                cartelRol.setText("ENFERMERA");
            }
            cartelUsuario.setText(nombre);
        } else {
            System.err.println("Error: Usuario en sesión es null");
            new InfoDialog(
                    this,
                    "Error",
                    "No se ha iniciado sesión correctamente.\nLa aplicación se cerrará.").setVisible(true);
            System.exit(1);
        }
    }

    private void mostrarConfirmacionSalida() {
        QuestionDialog dialogo = new QuestionDialog(
                this,
                "Confirmar salida",
                "\u00BFSeguro que desea salir?\n\nSe perder\u00E1n todos los progresos no guardados al salir de la aplicaci\u00F3n.");
        dialogo.setVisible(true);

        if (dialogo.esConfirmado()) {
            cmf.setUsuario(null);
            dispose(); // Cierra la ventana
            System.exit(0); // Finaliza la app
        }
    }

    public void cerrarSesion() {
        QuestionDialog dialogo = new QuestionDialog(
                this,
                "Confirmar cierre de sesi\u00F3n",
                "\u00BFSeguro que desea cerrar su sesi\u00F3n?");
        dialogo.setVisible(true);

        if (dialogo.esConfirmado()) {
            dispose(); // Cierra la ventana
            Runner.login();
        }
    }

    public void mouseClicked(MouseEvent e) {
        BotonMenu boton = (BotonMenu) e.getSource();
        if (!boton.estaActivo()) {
            getBotonActivo().setActivo(false);
            boton.setActivo(true);
            setBotonActivo(boton);

            CardLayout card = (CardLayout) (panelVentanas.getLayout());
            card.show(panelVentanas, boton.getText());
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (!((BotonMenu) e.getSource()).estaActivo()) {
            ((BotonMenu) e.getSource()).setBackground(new Color(0, 192, 255));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (!((BotonMenu) e.getSource()).estaActivo()) {
            ((BotonMenu) e.getSource()).setBackground(new Color(0, 171, 227));
        }
    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}