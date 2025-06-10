package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;

import componentesPropios.BotonBlanco;
import componentesPropios.TextDialog;
import entidades.CMF;
import entidades.Paciente;
import entidades.Mujer;

import javax.swing.JList;

import service.Validations;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormularioPaciente extends JDialog implements ConstantesFrontend {

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
	private DefaultListModel<String> listModelEnfermedades;
	private DefaultListModel<String> listModelVacunas;
	private JList<String> listaEnfermedades;
	private JList<String> listaVacunas;

	private JRadioButton radioFemenino; // Declare as a class-level variable
	private JRadioButton radioMasculino; // Declare as a class-level variable
	private JCheckBox checkEmbarazada; // Declare as a class-level variable
	private JDateChooser fechaUltimaPrueba; // Declare as a class-level variable
	private JTextField textField;

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

	// New constructor for editing users
	/**
	 * @wbp.parser.constructor
	 */
	public FormularioPaciente(Window ancestro, Paciente paciente) {
		super(ancestro, ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle(paciente == null ? "Agregar paciente" : "Editar paciente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Populate fields based on the provided Paciente object
		String nombre = paciente != null ? paciente.getNombre() : "";
		String primerApellido = paciente != null ? paciente.getPrimerApellido() : "";
		String segundoApellido = paciente != null ? paciente.getSegundoApellido() : "";
		String ci = paciente != null ? paciente.getCi() : "";
		String direccion = paciente != null ? paciente.getDireccion() : "";
		// Adapt logic for esFemenino and Mujer-specific fields
		boolean esFemenino = paciente != null && Validations.isFemale(paciente.getCi());
		boolean estaEmbarazada = false;
		Date fechaUltimaPruebaSeleccionada = null;

		if (esFemenino && paciente instanceof Mujer) {
			Mujer mujer = (Mujer) paciente;
			estaEmbarazada = mujer.isEmbarazada();
			String fechaRevisionStr = mujer.getFechaUltimaRevision();
			if (fechaRevisionStr != null && !fechaRevisionStr.isEmpty()) {
				try {
					fechaUltimaPruebaSeleccionada = new SimpleDateFormat("dd/MM/yyyy").parse(fechaRevisionStr);
				} catch (Exception e) {
					System.err.println("Error parsing fechaUltimaRevision: " + e.getMessage());
				}
			}
		}

		JLabel cartelInformacionMedica = new JLabel("Informaci\u00F3n m\u00E9dica:");
		cartelInformacionMedica.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacionMedica.setBackground(Color.WHITE);
		cartelInformacionMedica.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacionMedica.setBounds(55, 313, 219, 26);
		cartelInformacionMedica.setOpaque(true);
		cartelInformacionMedica.setBorder(BORDE_COMPONENTE);
		contentPane.add(cartelInformacionMedica);

		JLabel cartelInformacionPersonal = new JLabel("Informaci\u00F3n personal:");
		cartelInformacionPersonal.setBounds(55, 18, 232, 26);
		contentPane.add(cartelInformacionPersonal);
		cartelInformacionPersonal.setOpaque(true);
		cartelInformacionPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacionPersonal.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacionPersonal.setBorder(BORDE_COMPONENTE);
		cartelInformacionPersonal.setBackground(Color.WHITE);

		JPanel panelAgrupador1 = new JPanel();
		panelAgrupador1.setBackground(Color.WHITE);
		panelAgrupador1.setBounds(28, 31, 589, 269);
		panelAgrupador1.setBorder(BORDE_COMPONENTE);
		contentPane.add(panelAgrupador1);
		panelAgrupador1.setLayout(null);

		imagenPaciente = new JLabel("");
		imagenPaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		imagenPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imagenPaciente.setBounds(40, 36, 110, 110);
		imagenPaciente.setBorder(BORDE_COMPONENTE);
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
		cartelGenero.setBounds(40, 219, 61, 16);
		panelAgrupador1.add(cartelGenero);

		// Initialize radio buttons
		radioMasculino = new JRadioButton("Masculino");
		radioMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  checkEmbarazada.setSelected(false);
				  checkEmbarazada.setEnabled(false);
			}
		});
		radioMasculino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioMasculino.setBackground(Color.WHITE);
		radioMasculino.setBounds(100, 219, 99, 16);
		panelAgrupador1.add(radioMasculino);

		radioFemenino = new JRadioButton("Femenino");
		radioFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkEmbarazada.setEnabled(true);
			}
		});
		radioFemenino.setFont(new Font("Arial", Font.PLAIN, 16));
		radioFemenino.setBackground(Color.WHITE);
		radioFemenino.setBounds(203, 219, 99, 16);
		panelAgrupador1.add(radioFemenino);

		// Initialize checkbox
		checkEmbarazada = new JCheckBox("Embarazada");
		checkEmbarazada.setHorizontalAlignment(SwingConstants.LEFT);
		checkEmbarazada.setFont(new Font("Arial", Font.PLAIN, 16));
		checkEmbarazada.setBackground(Color.WHITE);
		checkEmbarazada.setBounds(302, 219, 119, 16);
		panelAgrupador1.add(checkEmbarazada);

		ButtonGroup genero = new ButtonGroup();
		genero.add(radioFemenino);
		genero.add(radioMasculino);
		
		genero.clearSelection();

		JPanel panelAgrupador2 = new JPanel();
		panelAgrupador2.setBackground(Color.WHITE);
		panelAgrupador2.setBounds(28, 326, 589, 272);
		panelAgrupador2.setBorder(BORDE_COMPONENTE);
		contentPane.add(panelAgrupador2);
		panelAgrupador2.setLayout(null);

		JScrollPane scrollPaneEnfermedades = new JScrollPane();
		scrollPaneEnfermedades.setBounds(40, 91, 240, 122);
		panelAgrupador2.add(scrollPaneEnfermedades);

		listModelEnfermedades = new DefaultListModel<>();

		listaEnfermedades = new JList<>(listModelEnfermedades);
		listaEnfermedades.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneEnfermedades.setViewportView(listaEnfermedades);

		JScrollPane scrollPaneVacunas = new JScrollPane();
		scrollPaneVacunas.setBounds(308, 91, 240, 122);
		panelAgrupador2.add(scrollPaneVacunas);

		listModelVacunas = new DefaultListModel<>();

		listaVacunas = new JList<>(listModelVacunas);
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
		botonEliminarEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarEnfermedadesSeleccionadas();
			}
		});
		botonEliminarEnfermedad.setToolTipText("Clic para borrar los elemento(s) selecionado(s) lista");
		botonEliminarEnfermedad.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarEnfermedad.setBounds(258, 64, 22, 22);
		botonEliminarEnfermedad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonEliminarEnfermedad);

		JLabel botonEliminarVacuna = new JLabel("");
		botonEliminarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarVacunasSeleccionadas();
			}
		});
		botonEliminarVacuna.setToolTipText("Clic para borrar los elemento(s) selecionado(s) lista");
		botonEliminarVacuna.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarVacuna.setBounds(526, 64, 22, 22);
		botonEliminarVacuna.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonEliminarVacuna);

		JLabel botonAgregarEnfermedad = new JLabel("");
		botonAgregarEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarEnfermedadCronica();
			}
		});
		botonAgregarEnfermedad.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarEnfermedad.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarEnfermedad.setBounds(234, 64, 22, 22);
		botonAgregarEnfermedad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonAgregarEnfermedad);

		JLabel botonAgregarVacuna = new JLabel("");
		botonAgregarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarVacuna();
			}
		});
		botonAgregarVacuna.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarVacuna.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarVacuna.setBounds(502, 64, 22, 22);
		botonAgregarVacuna.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgrupador2.add(botonAgregarVacuna);

		JLabel cartelUltimaPrueba = new JLabel("Fecha de \u00FAltima prueba citol\u00F3gica:");
		cartelUltimaPrueba.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelUltimaPrueba.setBounds(40, 29, 240, 22);
		panelAgrupador2.add(cartelUltimaPrueba);

		fechaUltimaPrueba = new JDateChooser();
		fechaUltimaPrueba.setToolTipText("Introduzca la fecha o elijala por el calendario");
		fechaUltimaPrueba.setFont(new Font("Arial", Font.PLAIN, 16));
		fechaUltimaPrueba.setDateFormatString("d/MMM/yyyy");
		fechaUltimaPrueba.setBounds(308, 29, 238, 22);
		fechaUltimaPrueba.setMaxSelectableDate(new Date());
		panelAgrupador2.add(fechaUltimaPrueba);

		BotonBlanco botonAceptar = new BotonBlanco((String) null);
		botonAceptar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonAceptar.setToolTipText("Cick para guardar los datos actuales");
		botonAceptar.setText("ACEPTAR");
		botonAceptar.setBounds(260, 620, 130, 30);
		contentPane.add(botonAceptar);


		// Set field values
		campoNombre.setText(nombre);
		campoPrimerApellido.setText(primerApellido);
		campoSegundoApellido.setText(segundoApellido);
		campoCI.setText(ci);
		campoDireccion.setText(direccion);
		checkEmbarazada.setSelected(estaEmbarazada);
		
		JLabel cartelEdad = new JLabel("Edad:");
		cartelEdad.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelEdad.setBounds(429, 218, 47, 19);
		panelAgrupador1.add(cartelEdad);
		
		textField = new JTextField();
		textField.setText("");
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(478, 216, 69, 22);
		panelAgrupador1.add(textField);
		fechaUltimaPrueba.setDate(fechaUltimaPruebaSeleccionada);

		// Populate lists
		listModelEnfermedades.clear();
		if (paciente != null && paciente.getEnfermedadesCronicas() != null) {
			for (String enfermedad : paciente.getEnfermedadesCronicas()) {
				listModelEnfermedades.addElement(enfermedad);
			}
		}

		listModelVacunas.clear();
		if (paciente != null && paciente.getVacunacion() != null) {
			for (String vacuna : paciente.getVacunacion()) {
				listModelVacunas.addElement(vacuna);
			}
		}

		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CMF cmf = CMF.getInstance();

				try {
					// Obtener datos del formulario
					String nombre = Validations.capitalize(campoNombre.getText().trim());
					String primerApellido = Validations.capitalize(campoPrimerApellido.getText().trim());
					String segundoApellido = Validations.capitalize(campoSegundoApellido.getText().trim());
					String ci = campoCI.getText().trim();
					String direccion = campoDireccion.getText().trim();
					boolean esFemenino = radioFemenino.isSelected();
					boolean estaEmbarazada = checkEmbarazada.isSelected();
					Date fechaUltimaPruebaSeleccionada = fechaUltimaPrueba.getDate(); // Obtener la fecha seleccionada del JDateChooser

					// Validar que los campos obligatorios no estén vacíos
					if (nombre.isEmpty() || primerApellido.isEmpty() || ci.isEmpty()) {
						throw new IllegalArgumentException("Los campos Nombre, Primer Apellido y CI son obligatorios.");
					}

					// Validar género y embarazo basado en el CI
					boolean ciEsFemenino = Validations.isFemale(ci);
					if (ciEsFemenino != esFemenino) {
						throw new IllegalArgumentException("El género seleccionado no coincide con el CI proporcionado.");
					}
					if (!ciEsFemenino && estaEmbarazada) {
						throw new IllegalArgumentException("Un paciente masculino no puede estar embarazado.");
					}

					// Validar que la fecha no sea nula antes de formatearla
					String fechaUltimaPruebaStr = null;
					if (fechaUltimaPruebaSeleccionada != null) {
						fechaUltimaPruebaStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaUltimaPruebaSeleccionada);
					}

					if(cmf.isCiRepited(ci)) {
						throw new IllegalArgumentException("El CI proporcionado ya está registrado.");
					}

					// Obtener listas de enfermedades crónicas y vacunas
					ArrayList<String> enfermedadesCronicas = new ArrayList<>(listModelEnfermedades.size());
					for (int i = 0; i < listModelEnfermedades.size(); i++) {
						enfermedadesCronicas.add(listModelEnfermedades.getElementAt(i));
					}

					ArrayList<String> vacunas = new ArrayList<>(listModelVacunas.size());
					for (int i = 0; i < listModelVacunas.size(); i++) {
						vacunas.add(listModelVacunas.getElementAt(i));
					}

					// Llamar al método agregarPaciente
					boolean pacienteAgregado = cmf.agregarPaciente(
							nombre,
							primerApellido,
							segundoApellido,
							enfermedadesCronicas,
							vacunas,
							ci,
							estaEmbarazada,
							fechaUltimaPruebaStr
							);

					if (pacienteAgregado) {
						JOptionPane.showMessageDialog(contentPane, "Paciente agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						dispose(); // Cerrar el formulario
					} else {
						JOptionPane.showMessageDialog(contentPane, "No se pudo agregar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		imagenFondo = new JLabel("");
		imagenFondo.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/fondo formulario paciente.jpg")));
		imagenFondo.setBounds(0, 0, 650, 700);
		contentPane.add(imagenFondo);
	}

	// Modify the existing constructor to call the new one with null
	public FormularioPaciente(Window ancestro) {
		this(ancestro, null); // Default to adding a new patient
	}

	protected void agregarEnfermedadCronica() {
		TextDialog dialogo = new TextDialog((JDialog)this, "Agregar enfermedad crónica", "Introduzca una enfermedad crónica para agregar");
		dialogo.setVisible(true);

		if (dialogo.isConfirmado()) {
			String texto = dialogo.getTextoIngresado();
			if (!texto.isEmpty()) {
				listModelEnfermedades.addElement(texto);
			}
		}
	}

	protected void agregarVacuna() {
		TextDialog dialogo = new TextDialog((JDialog)this, "Agregar vacuna aplicada", "Introduzca una vacuna aplicada para agregar");
		dialogo.setVisible(true);

		if (dialogo.isConfirmado()) {
			String texto = dialogo.getTextoIngresado();
			if (!texto.isEmpty()) {
				listModelVacunas.addElement(texto);
			}
		}
	}

	private void eliminarEnfermedadesSeleccionadas() {
		int[] indices = listaEnfermedades.getSelectedIndices();
		for (int i = indices.length - 1; i >= 0; i--) {
			listModelEnfermedades.remove(indices[i]);
		}
	}

	private void eliminarVacunasSeleccionadas() {
		int[] indices = listaVacunas.getSelectedIndices();
		for (int i = indices.length - 1; i >= 0; i--) {
			listModelVacunas.remove(indices[i]);
		}
	}
}
