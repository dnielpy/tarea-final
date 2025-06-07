package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.util.Date;

import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;

import componentesPropios.BotonBlanco;
import entidades.Paciente;

import javax.swing.JList;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.SystemColor;

public class FormularioPaciente extends JDialog {

	private JPanel contentPane;
	private JLabel imagenPaciente;
	private JLabel cartelNombre;
	private JTextField campoNombre;
	private JLabel cartelPrimerApellido;
	private JTextField campoPrimerApellido;
	private JTextField campoSegundoApellido;
	private JLabel cartelSegundoApellido;
	private JTextField campoCI;
	private JLabel cartelCI;
	private JPanel panelAgrupador2;
	private JLabel cartelGenero;
	private JRadioButton radioMasculino;
	private JRadioButton radioFemenino;
	private JLabel cartelUltimaPruebaCitolgica;
	private JLabel imagenFondo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioPaciente frame = new FormularioPaciente(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormularioPaciente(Window ancestro) {
		super(ancestro, ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Ingresar paciente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelAgrupador1 = new JPanel();
		panelAgrupador1.setBackground(Color.WHITE);
		panelAgrupador1.setBounds(28, 45, 525, 161);
		panelAgrupador1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelAgrupador1);
		panelAgrupador1.setLayout(null);
		
		imagenPaciente = new JLabel("");
		imagenPaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		imagenPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imagenPaciente.setBounds(22, 24, 110, 110);
		imagenPaciente.setBorder(new LineBorder(SystemColor.scrollbar, 1, true));
		panelAgrupador1.add(imagenPaciente);
		
		cartelNombre = new JLabel("Nombre:");
		cartelNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelNombre.setBounds(159, 24, 154, 16);
		panelAgrupador1.add(cartelNombre);
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(159, 43, 154, 22);
		panelAgrupador1.add(campoNombre);
		
		cartelPrimerApellido = new JLabel("Primer apellido:");
		cartelPrimerApellido.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelPrimerApellido.setBounds(159, 89, 154, 22);
		panelAgrupador1.add(cartelPrimerApellido);
		
		campoPrimerApellido = new JTextField();
		campoPrimerApellido.setColumns(10);
		campoPrimerApellido.setBounds(159, 112, 154, 22);
		panelAgrupador1.add(campoPrimerApellido);
		
		campoSegundoApellido = new JTextField();
		campoSegundoApellido.setColumns(10);
		campoSegundoApellido.setBounds(338, 112, 162, 22);
		panelAgrupador1.add(campoSegundoApellido);
		
		cartelSegundoApellido = new JLabel("Segundo apellido:");
		cartelSegundoApellido.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelSegundoApellido.setBounds(338, 89, 162, 22);
		panelAgrupador1.add(cartelSegundoApellido);
		
		campoCI = new JTextField();
		campoCI.setColumns(10);
		campoCI.setBounds(338, 43, 162, 22);
		panelAgrupador1.add(campoCI);
		
		cartelCI = new JLabel("Carn\u00E9 de identidad:");
		cartelCI.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelCI.setBounds(338, 24, 162, 16);
		panelAgrupador1.add(cartelCI);
		
		panelAgrupador2 = new JPanel();
		panelAgrupador2.setBackground(Color.WHITE);
		panelAgrupador2.setBounds(28, 229, 525, 100);
		panelAgrupador2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelAgrupador2);
		panelAgrupador2.setLayout(null);
		
		cartelGenero = new JLabel("G\u00E9nero:");
		cartelGenero.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelGenero.setBounds(38, 25, 69, 16);
		panelAgrupador2.add(cartelGenero);
		
		radioMasculino = new JRadioButton("Masculino");
		radioMasculino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioMasculino.setBackground(Color.WHITE);
		radioMasculino.setBounds(109, 25, 99, 16);
		panelAgrupador2.add(radioMasculino);
		
		radioFemenino = new JRadioButton("Femenino");
		radioFemenino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioFemenino.setBackground(Color.WHITE);
		radioFemenino.setBounds(212, 25, 99, 16);
		panelAgrupador2.add(radioFemenino);
		
		ButtonGroup genero = new ButtonGroup();
		genero.add(radioMasculino);
		genero.add(radioFemenino);
		
		JCheckBox checkEmbarazada = new JCheckBox("Embarazada");
		checkEmbarazada.setHorizontalAlignment(SwingConstants.LEFT);
		checkEmbarazada.setBackground(Color.WHITE);
		checkEmbarazada.setFont(new Font("Arial", Font.PLAIN, 16));
		checkEmbarazada.setBounds(347, 25, 129, 16);
		panelAgrupador2.add(checkEmbarazada);
		
		JDateChooser fechaUltimaPrueba = new JDateChooser();
		fechaUltimaPrueba.setToolTipText("Introduzca la fecha o elijala por el calendario");
		fechaUltimaPrueba.setDateFormatString("d/MMM/yyyy");
		fechaUltimaPrueba.setBounds(347, 54, 119, 22);
		fechaUltimaPrueba.setFont(new Font("Arial", Font.PLAIN, 16));
		panelAgrupador2.add(fechaUltimaPrueba);
			
		fechaUltimaPrueba.setMaxSelectableDate(new Date());
		
		cartelUltimaPruebaCitolgica = new JLabel("Fecha de \u00FAltima prueba citol\u00F3gica:");
		cartelUltimaPruebaCitolgica.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelUltimaPruebaCitolgica.setBounds(38, 54, 273, 22);
		panelAgrupador2.add(cartelUltimaPruebaCitolgica);
		
		JPanel panelAgrupador3 = new JPanel();
		panelAgrupador3.setBackground(Color.WHITE);
		panelAgrupador3.setBounds(28, 354, 525, 233);
		panelAgrupador3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelAgrupador3);
		panelAgrupador3.setLayout(null);
		
		JScrollPane scrollPaneEnfermedades = new JScrollPane();
		scrollPaneEnfermedades.setBounds(31, 50, 217, 122);
		panelAgrupador3.add(scrollPaneEnfermedades);
		
		JList<String> listaEnfermedades = new JList<String>();
		listaEnfermedades.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneEnfermedades.setViewportView(listaEnfermedades);
		
		JScrollPane scrollPaneVacunas = new JScrollPane();
		scrollPaneVacunas.setBounds(278, 50, 215, 122);
		panelAgrupador3.add(scrollPaneVacunas);
		
		JList<String> listaVacunas = new JList<String>();
		listaVacunas.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneVacunas.setViewportView(listaVacunas);
		
		JLabel cartelEnfermedadesCronicas = new JLabel("Enfermedades cr\u00F3nicas:");
		cartelEnfermedadesCronicas.setBounds(31, 23, 217, 22);
		cartelEnfermedadesCronicas.setFont(new Font("Arial", Font.PLAIN, 18));
		panelAgrupador3.add(cartelEnfermedadesCronicas);
		
		JLabel cartelVacunas = new JLabel("Vacunas aplicadas:");
		cartelVacunas.setBounds(278, 23, 215, 22);
		cartelVacunas.setFont(new Font("Arial", Font.PLAIN, 18));
		panelAgrupador3.add(cartelVacunas);
		
		JLabel botonEliminarEnfermedad = new JLabel("");
		botonEliminarEnfermedad.setToolTipText("Clic para borrar los elemento(s) selecionado(s) lista");
		botonEliminarEnfermedad.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarEnfermedad.setBounds(226, 185, 22, 22);
		panelAgrupador3.add(botonEliminarEnfermedad);
		
		JLabel botonEliminarVacuna = new JLabel("");
		botonEliminarVacuna.setToolTipText("Clic para borrar los elemento(s) selecionado(s) lista");
		botonEliminarVacuna.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarVacuna.setBounds(471, 185, 22, 22);
		panelAgrupador3.add(botonEliminarVacuna);
		
		JLabel botonAgregarEnfermedad = new JLabel("");
		botonAgregarEnfermedad.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarEnfermedad.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarEnfermedad.setBounds(194, 185, 22, 22);
		panelAgrupador3.add(botonAgregarEnfermedad);
		
		JLabel botonAgregarVacuna = new JLabel("");
		botonAgregarVacuna.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarVacuna.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarVacuna.setBounds(440, 185, 22, 22);
		panelAgrupador3.add(botonAgregarVacuna);
		
		BotonBlanco botonAceptar = new BotonBlanco((String) null);
		botonAceptar.setToolTipText("Cick para guardar los datos actuales");
		botonAceptar.setText("ACEPTAR");
		botonAceptar.setBounds(229, 605, 129, 30);
		contentPane.add(botonAceptar);
		
		imagenFondo = new JLabel("");
		imagenFondo.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/fondo formulario paciente.jpg")));
		imagenFondo.setBounds(0, 0, 600, 700);
		contentPane.add(imagenFondo);
	}
}
