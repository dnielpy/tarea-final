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

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JLabel imagenFondo;
	private JTextField campoDireccion;

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
		setTitle("Agregar paciente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel cartelInformacionMedica = new JLabel("Informaci\u00F3n m\u00E9dica:");
		cartelInformacionMedica.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacionMedica.setBackground(Color.WHITE);
		cartelInformacionMedica.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacionMedica.setBounds(55, 313, 219, 26);
		cartelInformacionMedica.setOpaque(true);
		cartelInformacionMedica.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(cartelInformacionMedica);
		
		JLabel cartelInformacinPersonal = new JLabel("Informaci\u00F3n personal:");
		cartelInformacinPersonal.setBounds(55, 18, 232, 26);
		contentPane.add(cartelInformacinPersonal);
		cartelInformacinPersonal.setOpaque(true);
		cartelInformacinPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacinPersonal.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacinPersonal.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		cartelInformacinPersonal.setBackground(Color.WHITE);
		
		JPanel panelAgrupador1 = new JPanel();
		panelAgrupador1.setBackground(Color.WHITE);
		panelAgrupador1.setBounds(28, 31, 589, 269);
		panelAgrupador1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelAgrupador1);
		panelAgrupador1.setLayout(null);
		
		imagenPaciente = new JLabel("");
		imagenPaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		imagenPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imagenPaciente.setBounds(40, 36, 110, 110);
		imagenPaciente.setBorder(new LineBorder(SystemColor.scrollbar, 1, true));
		panelAgrupador1.add(imagenPaciente);
		
		cartelNombre = new JLabel("Nombre:");
		cartelNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelNombre.setBounds(173, 36, 175, 16);
		panelAgrupador1.add(cartelNombre);
		
		campoNombre = new JTextField();
		campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		campoNombre.setColumns(10);
		campoNombre.setBounds(173, 55, 175, 22);
		panelAgrupador1.add(campoNombre);
		
		cartelPrimerApellido = new JLabel("Primer apellido:");
		cartelPrimerApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelPrimerApellido.setBounds(173, 101, 175, 22);
		panelAgrupador1.add(cartelPrimerApellido);
		
		campoPrimerApellido = new JTextField();
		campoPrimerApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		campoPrimerApellido.setColumns(10);
		campoPrimerApellido.setBounds(173, 124, 175, 22);
		panelAgrupador1.add(campoPrimerApellido);
		
		campoSegundoApellido = new JTextField();
		campoSegundoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		campoSegundoApellido.setColumns(10);
		campoSegundoApellido.setBounds(372, 124, 175, 22);
		panelAgrupador1.add(campoSegundoApellido);
		
		cartelSegundoApellido = new JLabel("Segundo apellido:");
		cartelSegundoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelSegundoApellido.setBounds(372, 101, 173, 22);
		panelAgrupador1.add(cartelSegundoApellido);
		
		campoCI = new JTextField();
		campoCI.setFont(new Font("Arial", Font.PLAIN, 16));
		campoCI.setColumns(10);
		campoCI.setBounds(372, 55, 175, 22);
		panelAgrupador1.add(campoCI);
		
		cartelCI = new JLabel("Carn\u00E9 de identidad:");
		cartelCI.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelCI.setBounds(372, 36, 173, 16);
		panelAgrupador1.add(cartelCI);
		
		campoDireccion = new JTextField();
		campoDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		campoDireccion.setColumns(10);
		campoDireccion.setBounds(126, 173, 421, 22);
		panelAgrupador1.add(campoDireccion);
		
		JLabel cartelDireccion = new JLabel("Direcci\u00F3n:");
		cartelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelDireccion.setBounds(40, 172, 82, 22);
		panelAgrupador1.add(cartelDireccion);
		
		JLabel cartelGenero = new JLabel("G\u00E9nero:");
		cartelGenero.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelGenero.setBounds(40, 219, 69, 16);
		panelAgrupador1.add(cartelGenero);
		
		JRadioButton radioMasculino = new JRadioButton("Masculino");
		radioMasculino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioMasculino.setBackground(Color.WHITE);
		radioMasculino.setBounds(126, 219, 99, 16);
		panelAgrupador1.add(radioMasculino);
		
		JRadioButton radioFemenino = new JRadioButton("Femenino");
		radioFemenino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioFemenino.setBackground(Color.WHITE);
		radioFemenino.setBounds(249, 219, 99, 16);
		panelAgrupador1.add(radioFemenino);
		
		JCheckBox checkEmbarazada = new JCheckBox("Embarazada");
		checkEmbarazada.setHorizontalAlignment(SwingConstants.LEFT);
		checkEmbarazada.setFont(new Font("Arial", Font.PLAIN, 16));
		checkEmbarazada.setBackground(Color.WHITE);
		checkEmbarazada.setBounds(372, 219, 129, 16);
		panelAgrupador1.add(checkEmbarazada);
		
		ButtonGroup genero = new ButtonGroup();
		genero.add(radioFemenino);
		genero.add(radioMasculino);
		
		JPanel panelAgrupador2 = new JPanel();
		panelAgrupador2.setBackground(Color.WHITE);
		panelAgrupador2.setBounds(28, 326, 589, 272);
		panelAgrupador2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(panelAgrupador2);
		panelAgrupador2.setLayout(null);
		
		JScrollPane scrollPaneEnfermedades = new JScrollPane();
		scrollPaneEnfermedades.setBounds(40, 91, 240, 122);
		panelAgrupador2.add(scrollPaneEnfermedades);
		
		JList<String> listaEnfermedades = new JList<String>();
		listaEnfermedades.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneEnfermedades.setViewportView(listaEnfermedades);
		
		JScrollPane scrollPaneVacunas = new JScrollPane();
		scrollPaneVacunas.setBounds(308, 91, 240, 122);
		panelAgrupador2.add(scrollPaneVacunas);
		
		JList<String> listaVacunas = new JList<String>();
		listaVacunas.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneVacunas.setViewportView(listaVacunas);
		
		JLabel cartelEnfermedadesCronicas = new JLabel("Enfermedades cr\u00F3nicas:");
		cartelEnfermedadesCronicas.setBounds(40, 64, 176, 22);
		cartelEnfermedadesCronicas.setFont(new Font("Arial", Font.PLAIN, 16));
		panelAgrupador2.add(cartelEnfermedadesCronicas);
		
		JLabel cartelVacunas = new JLabel("Vacunas aplicadas:");
		cartelVacunas.setBounds(308, 64, 155, 22);
		cartelVacunas.setFont(new Font("Arial", Font.PLAIN, 16));
		panelAgrupador2.add(cartelVacunas);
		
		JLabel botonEliminarEnfermedad = new JLabel("");
		botonEliminarEnfermedad.setToolTipText("Clic para borrar los elemento(s) selecionado(s) lista");
		botonEliminarEnfermedad.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarEnfermedad.setBounds(258, 64, 22, 22);
		botonEliminarEnfermedad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonEliminarEnfermedad);
		
		JLabel botonEliminarVacuna = new JLabel("");
		botonEliminarVacuna.setToolTipText("Clic para borrar los elemento(s) selecionado(s) lista");
		botonEliminarVacuna.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarVacuna.setBounds(526, 64, 22, 22);
		botonEliminarVacuna.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonEliminarVacuna);
		
		JLabel botonAgregarEnfermedad = new JLabel("");
		botonAgregarEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		botonAgregarEnfermedad.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarEnfermedad.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarEnfermedad.setBounds(234, 64, 22, 22);
		botonAgregarEnfermedad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonAgregarEnfermedad);
		
		JLabel botonAgregarVacuna = new JLabel("");
		botonAgregarVacuna.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarVacuna.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarVacuna.setBounds(502, 64, 22, 22);
		botonAgregarVacuna.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonAgregarVacuna);
		
		JLabel cartelUltimaPrueba = new JLabel("Fecha de \u00FAltima prueba citol\u00F3gica:");
		cartelUltimaPrueba.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelUltimaPrueba.setBounds(40, 29, 240, 22);
		panelAgrupador2.add(cartelUltimaPrueba);
		
		JDateChooser fechaUltimaPrueba = new JDateChooser();
		fechaUltimaPrueba.setToolTipText("Introduzca la fecha o elijala por el calendario");
		fechaUltimaPrueba.setFont(new Font("Arial", Font.PLAIN, 16));
		fechaUltimaPrueba.setDateFormatString("d/MMM/yyyy");
		fechaUltimaPrueba.setBounds(308, 29, 238, 22);
		panelAgrupador2.add(fechaUltimaPrueba);
		
		BotonBlanco botonAceptar = new BotonBlanco((String) null);
		botonAceptar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonAceptar.setToolTipText("Cick para guardar los datos actuales");
		botonAceptar.setText("ACEPTAR");
		botonAceptar.setBounds(260, 620, 130, 30);
		contentPane.add(botonAceptar);
		
		imagenFondo = new JLabel("");
		imagenFondo.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/fondo formulario paciente.jpg")));
		imagenFondo.setBounds(0, 0, 650, 700);
		contentPane.add(imagenFondo);
	}
}
