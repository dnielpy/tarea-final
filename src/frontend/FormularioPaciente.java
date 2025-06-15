package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;

import componentesPropios.BotonBlanco;
import componentesPropios.ImageButtonLabel;
import componentesPropios.PlaceholderTextField;
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
import java.awt.SystemColor;

public class FormularioPaciente extends JDialog implements ConstantesFrontend {

	private JPanel contentPane;
	private JLabel imagenPaciente;
	private JLabel cartelNombre;
	private PlaceholderTextField campoNombre;
	private JLabel cartelPrimerApellido;
	private PlaceholderTextField campoPrimerApellido;
	private PlaceholderTextField campoSegundoApellido;
	private JLabel cartelSegundoApellido;
	private PlaceholderTextField campoCI;
	private JLabel cartelCI;
	private PlaceholderTextField campoDireccion;
	private DefaultListModel<String> listModelEnfermedades;
	private DefaultListModel<String> listModelVacunas;
	private JList<String> listaEnfermedades;
	private JList<String> listaVacunas;

	private JRadioButton radioFemenino; 
	private JRadioButton radioMasculino;
	private JCheckBox checkEmbarazada;
	private JDateChooser fechaUltimaPrueba;
	private JTextField campoEdad;
	
	private JPanel panelAgrupador2;
	
	private JLabel botonEliminarEnfermedad;
	private JLabel botonAgregarVacuna;
	private JLabel botonEliminarVacuna;
	private JLabel botonAgregarEnfermedad;
	
	private JButton botonCancelar;
	private JButton botonEditar;
	
	private boolean editable;
	private JPanel panelGris;

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

	
	/**
	 * @wbp.parser.constructor
	 */
	public FormularioPaciente(Window ancestro) {
		super(ancestro, ModalityType.APPLICATION_MODAL);
		this.editable = true;
		inicializarFormulario();
		setTitle("Agregar paciente");
		activarEdicion(this.editable);
	}

	
	public FormularioPaciente(Window ancestro, Paciente paciente, boolean editable) {	
		super(ancestro, ModalityType.APPLICATION_MODAL);
		inicializarFormulario();
		setTitle("Informaci�n del paciente");
		this.editable = editable;
		mostrarInformacionPaciente(paciente);	
		activarEdicion(this.editable);
		
		JLabel cartelHistoriaClinica = new JLabel
				("N�mero de Historia Cl�nica: " + String.valueOf(paciente.getHistoriaClinica().getId()));
		cartelHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelHistoriaClinica.setBounds(40, 226, 250, 22);
		panelAgrupador2.add(cartelHistoriaClinica);
		
	}
	
	public void inicializarFormulario() {
		setResizable(false);
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

		cartelNombre = new JLabel("Nombre: ");
		cartelNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelNombre.setBounds(173, 36, 69, 19);
		panelAgrupador1.add(cartelNombre);

		campoNombre = new PlaceholderTextField();
		campoNombre.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		campoNombre.setColumns(10);
		campoNombre.setBounds(240, 34, 310, 22);
		panelAgrupador1.add(campoNombre);

		cartelPrimerApellido = new JLabel("Primer apellido: ");
		cartelPrimerApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelPrimerApellido.setBounds(173, 65, 119, 22);
		panelAgrupador1.add(cartelPrimerApellido);

		campoPrimerApellido = new PlaceholderTextField();
		campoPrimerApellido.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoPrimerApellido.setColumns(10);
		campoPrimerApellido.setBounds(293, 65, 257, 22);
		panelAgrupador1.add(campoPrimerApellido);

		campoSegundoApellido = new PlaceholderTextField();
		campoSegundoApellido.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoSegundoApellido.setColumns(10);
		campoSegundoApellido.setBounds(311, 95, 236, 22);
		panelAgrupador1.add(campoSegundoApellido);

		cartelSegundoApellido = new JLabel("Segundo apellido: ");
		cartelSegundoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelSegundoApellido.setBounds(173, 95, 135, 22);
		panelAgrupador1.add(cartelSegundoApellido);

		campoCI = new PlaceholderTextField();
		campoCI.setInputFormat(PlaceholderTextField.InputFormat.NUMERIC);
		campoCI.setColumns(10);
		campoCI.setBounds(321, 124, 226, 22);
		panelAgrupador1.add(campoCI);

		cartelCI = new JLabel("Carn\u00E9 de identidad: ");
		cartelCI.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelCI.setBounds(173, 124, 143, 22);
		panelAgrupador1.add(cartelCI);

		campoDireccion = new PlaceholderTextField();
		campoDireccion.setColumns(10);
		campoDireccion.setBounds(117, 172, 433, 22);
		panelAgrupador1.add(campoDireccion);

		JLabel cartelDireccion = new JLabel("Direcci\u00F3n: ");
		cartelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelDireccion.setBounds(40, 172, 82, 22);
		panelAgrupador1.add(cartelDireccion);

		JLabel cartelGenero = new JLabel("G\u00E9nero: ");
		cartelGenero.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelGenero.setBounds(40, 219, 61, 16);
		panelAgrupador1.add(cartelGenero);

		// Initialize radio buttons
		radioMasculino = new JRadioButton("Masculino");
		radioMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  checkEmbarazada.setSelected(false);
				  checkEmbarazada.setEnabled(false);
				  fechaUltimaPrueba.setDate(null);
				  fechaUltimaPrueba.setEnabled(false);
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
				fechaUltimaPrueba.setEnabled(true);
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
		
		JLabel cartelEdad = new JLabel("Edad: ");
		cartelEdad.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelEdad.setBounds(429, 218, 47, 19);
		panelAgrupador1.add(cartelEdad);
		
		campoEdad = new PlaceholderTextField();
		campoEdad.setColumns(10);
		campoEdad.setBounds(478, 216, 69, 22);
		panelAgrupador1.add(campoEdad);

		panelAgrupador2 = new JPanel();
		panelAgrupador2.setBackground(Color.WHITE);
		panelAgrupador2.setBounds(28, 326, 589, 272);
		panelAgrupador2.setBorder(BORDE_COMPONENTE);
		contentPane.add(panelAgrupador2);
		panelAgrupador2.setLayout(null);

		JScrollPane scrollPaneEnfermedades = new JScrollPane();
		scrollPaneEnfermedades.setBounds(40, 91, 240, 122);
		scrollPaneEnfermedades.setBorder(BORDE_COMPONENTE);
		panelAgrupador2.add(scrollPaneEnfermedades);

		listModelEnfermedades = new DefaultListModel<>();

		listaEnfermedades = new JList<>(listModelEnfermedades);
		listaEnfermedades.setBorder(null);
		listaEnfermedades.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneEnfermedades.setViewportView(listaEnfermedades);

		JScrollPane scrollPaneVacunas = new JScrollPane();
		scrollPaneVacunas.setBounds(308, 91, 240, 122);
		scrollPaneVacunas.setBorder(BORDE_COMPONENTE);
		panelAgrupador2.add(scrollPaneVacunas);

		listModelVacunas = new DefaultListModel<>();

		listaVacunas = new JList<>(listModelVacunas);
		listaVacunas.setBorder(null);
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

		botonEliminarEnfermedad = new ImageButtonLabel
				(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarEnfermedadesSeleccionadas();
			}
		});
		botonEliminarEnfermedad.setToolTipText("Clic para borrar los elemento(s) selecionado(s) de la lista");
		botonEliminarEnfermedad.setBounds(258, 64, 22, 22);
		panelAgrupador2.add(botonEliminarEnfermedad);

		botonEliminarVacuna = new ImageButtonLabel
				(new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarVacunasSeleccionadas();
			}
		});
		botonEliminarVacuna.setToolTipText("Clic para borrar los elemento(s) selecionado(s) de la lista");
		botonEliminarVacuna.setBounds(526, 64, 22, 22);
		panelAgrupador2.add(botonEliminarVacuna);

		botonAgregarEnfermedad = new ImageButtonLabel
				(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarEnfermedadCronica();
			}
		});
		botonAgregarEnfermedad.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarEnfermedad.setBounds(234, 64, 22, 22);
		panelAgrupador2.add(botonAgregarEnfermedad);

		botonAgregarVacuna = new ImageButtonLabel
				(new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarVacuna();
			}
		});
		botonAgregarVacuna.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarVacuna.setBounds(502, 64, 22, 22);
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

		BotonBlanco botonAceptar = new BotonBlanco("ACEPTAR");
		botonAceptar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonAceptar.setToolTipText("Clic para guardar los datos actuales");
		botonAceptar.setBounds(181, 620, 130, 30);
		contentPane.add(botonAceptar);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agregarPaciente();
			}
		});
		
		BotonBlanco botonGuardar = new BotonBlanco("GUARDAR");
		botonGuardar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonGuardar.setToolTipText("Clic para guardar los datos actuales");
		botonGuardar.setBounds(181, 620, 130, 30);
		contentPane.add(botonGuardar);
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		botonEditar = new BotonBlanco("EDITAR");
		botonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activarEdicion(true);
			}
		});
		botonEditar.setToolTipText("Clic para editar los datos del formulario");
		botonEditar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonEditar.setBounds(344, 620, 130, 30);
		contentPane.add(botonEditar);
		
		botonCancelar = new BotonBlanco("CANCELAR");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonCancelar.setToolTipText("Clic para volver a la ventana principal");
		botonCancelar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonCancelar.setBounds(344, 620, 130, 30);
		contentPane.add(botonCancelar);
		
		JPanel panelVerde = new JPanel();
		panelVerde.setBounds(0, 0, 644, 124);
		panelVerde.setBackground(COLOR_VERDE);
		contentPane.add(panelVerde);
		
		JPanel panelAzul = new JPanel();
		panelAzul.setBounds(0, 499, 650, 166);
		panelAzul.setBackground(COLOR_AZUL);
		contentPane.add(panelAzul);
		
		panelGris = new JPanel();
		panelGris.setBackground(SystemColor.menu);
		panelGris.setBounds(0, 134, 644, 354);
		contentPane.add(panelGris);
	}

	public void mostrarInformacionPaciente(Paciente paciente) {
		campoNombre.setText(paciente.getNombre());
		campoPrimerApellido.setText(paciente.getPrimerApellido());
		campoSegundoApellido.setText(paciente.getSegundoApellido());
		campoCI.setText(paciente.getCI());
		campoDireccion.setText(paciente.getDireccion());
		campoEdad.setText(String.valueOf(paciente.getEdad()));
		
		if (paciente instanceof Mujer) {
			radioFemenino.setSelected(true);
			checkEmbarazada.setSelected(((Mujer) paciente).isEmbarazada());
			fechaUltimaPrueba.setDate(((Mujer) paciente).getFechaUltimaRevision());
		} else {
			radioMasculino.setSelected(true);
		}

		
		if (paciente.getEnfermedadesCronicas() != null) {
			for (String enfermedad : paciente.getEnfermedadesCronicas()) {
				listModelEnfermedades.addElement(enfermedad);
			}
		}

		if (paciente.getVacunacion() != null) {
			for (String vacuna : paciente.getVacunacion()) {
				listModelVacunas.addElement(vacuna);
			}
		}

	}
	
	protected void agregarEnfermedadCronica() {
		TextDialog dialogo = new TextDialog((JDialog)this, "Agregar enfermedad cr�nica", "Introduzca una enfermedad cr�nica para agregar");
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
	
	public void agregarPaciente() {
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

			// Validar que los campos obligatorios no est�n vac�os
			if (nombre.isEmpty() || primerApellido.isEmpty() || ci.isEmpty()) {
				throw new IllegalArgumentException("Los campos Nombre, Primer Apellido y CI son obligatorios.");
			}

			// Validar g�nero y embarazo basado en el CI
			boolean ciEsFemenino = Validations.isFemale(ci);
			if (ciEsFemenino != esFemenino) {
				throw new IllegalArgumentException("El g�nero seleccionado no coincide con el CI proporcionado.");
			}
			if (!ciEsFemenino && estaEmbarazada) {
				throw new IllegalArgumentException("Un paciente masculino no puede estar embarazado.");
			}

			if(cmf.isCiRepited(ci)) {
				throw new IllegalArgumentException("El CI proporcionado ya est� registrado.");
			}

			// Obtener listas de enfermedades cr�nicas y vacunas
			ArrayList<String> enfermedadesCronicas = new ArrayList<>(listModelEnfermedades.size());
			for (int i = 0; i < listModelEnfermedades.size(); i++) {
				enfermedadesCronicas.add(listModelEnfermedades.getElementAt(i));
			}

			ArrayList<String> vacunas = new ArrayList<>(listModelVacunas.size());
			for (int i = 0; i < listModelVacunas.size(); i++) {
				vacunas.add(listModelVacunas.getElementAt(i));
			}

			// Llamar al m�todo agregarPaciente
			boolean pacienteAgregado = cmf.agregarPaciente(
					nombre,
					primerApellido,
					segundoApellido,
					enfermedadesCronicas,
					vacunas,
					ci,
					estaEmbarazada,
					fechaUltimaPruebaSeleccionada,
					direccion
					);

			if (pacienteAgregado) {
				JOptionPane.showMessageDialog(contentPane, "Paciente agregado exitosamente.", "�xito", JOptionPane.INFORMATION_MESSAGE);
				dispose(); // Cerrar el formulario
			} else {
				JOptionPane.showMessageDialog(contentPane, "No se pudo agregar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(contentPane, "Ocurri� un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void activarEdicion(boolean activo) {
		editable = activo;
		campoNombre.setEditable(activo);
		campoPrimerApellido.setEditable(activo);
		campoSegundoApellido.setEditable(activo);
		campoCI.setEditable(activo);
		campoDireccion.setEditable(activo);
		campoEdad.setEditable(activo);
		
		radioMasculino.setVisible(activo);
		radioFemenino.setVisible(activo);
		checkEmbarazada.setVisible(activo);
		fechaUltimaPrueba.setVisible(activo);
		
		botonAgregarEnfermedad.setVisible(activo);
		botonAgregarVacuna.setVisible(activo);
		botonEliminarEnfermedad.setVisible(activo);
		botonEliminarVacuna.setVisible(activo);		
		
		botonCancelar.setEnabled(activo);
		botonCancelar.setVisible(activo);

		botonEditar.setEnabled(!activo);
		botonEditar.setVisible(!activo);
		
		revalidate();
		repaint();
	}
}
