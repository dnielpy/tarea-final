package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.util.Date;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import com.toedter.calendar.JDateChooser;

public class FormularioPaciente extends JFrame {

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
	private JLabel lblltimaPruebaCitolgica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioPaciente frame = new FormularioPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Border borde = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		JPanel panelAgrupador1 = new JPanel();
		panelAgrupador1.setBackground(Color.WHITE);
		panelAgrupador1.setBounds(28, 45, 525, 161);
		panelAgrupador1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelAgrupador1);
		panelAgrupador1.setLayout(null);
		
		imagenPaciente = new JLabel("");
		imagenPaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		imagenPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imagenPaciente.setBounds(22, 24, 110, 110);
		imagenPaciente.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		cartelPrimerApellido.setBounds(159, 92, 154, 16);
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
		
		cartelCI = new JLabel("Carnet de identidad:");
		cartelCI.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelCI.setBounds(338, 24, 162, 16);
		panelAgrupador1.add(cartelCI);
		
		panelAgrupador2 = new JPanel();
		panelAgrupador2.setBackground(Color.WHITE);
		panelAgrupador2.setBounds(28, 229, 525, 100);
		panelAgrupador2.setBorder(borde);
		contentPane.add(panelAgrupador2);
		panelAgrupador2.setLayout(null);
		
		cartelGenero = new JLabel("G\u00E9nero:");
		cartelGenero.setFont(new Font("Arial", Font.PLAIN, 18));
		cartelGenero.setBounds(22, 25, 69, 16);
		panelAgrupador2.add(cartelGenero);
		
		radioMasculino = new JRadioButton("Masculino");
		radioMasculino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioMasculino.setBackground(Color.WHITE);
		radioMasculino.setBounds(93, 25, 99, 16);
		panelAgrupador2.add(radioMasculino);
		
		radioFemenino = new JRadioButton("Femenino");
		radioFemenino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioFemenino.setBackground(Color.WHITE);
		radioFemenino.setBounds(196, 25, 99, 16);
		panelAgrupador2.add(radioFemenino);
		
		ButtonGroup genero = new ButtonGroup();
		genero.add(radioMasculino);
		genero.add(radioFemenino);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Embarazada");
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("Arial", Font.PLAIN, 16));
		chckbxNewCheckBox.setBounds(347, 25, 129, 16);
		panelAgrupador2.add(chckbxNewCheckBox);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("d/MMM/yyyy");
		dateChooser.setBounds(347, 54, 119, 22);
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 16));
		panelAgrupador2.add(dateChooser);
			
		dateChooser.setMaxSelectableDate(new Date());
		
		lblltimaPruebaCitolgica = new JLabel("Fecha de \u00FAltima prueba citol\u00F3gica:");
		lblltimaPruebaCitolgica.setFont(new Font("Arial", Font.PLAIN, 18));
		lblltimaPruebaCitolgica.setBounds(22, 54, 273, 22);
		panelAgrupador2.add(lblltimaPruebaCitolgica);
	}
}
