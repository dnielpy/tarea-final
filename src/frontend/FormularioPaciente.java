package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JCheckBox;

import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;

import componentesPropios.BotonBlanco;
import componentesPropios.ImageButtonLabel;
import componentesPropios.InfoDialog;
import componentesPropios.PlaceholderTextField;
import componentesPropios.TextDialog;
import entidades.CMF;
import entidades.Mujer;
import entidades.Paciente;

import javax.swing.JList;

import service.Validations;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

import componentesPropios.PlaceholderTextField.InputFormat;

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
	private JLabel cartelDireccion;
	private PlaceholderTextField campoDireccion;
	private JLabel cartelGenero;
	private DefaultListModel<String> listModelEnfermedades;
	private DefaultListModel<String> listModelVacunas;
	private JList<String> listaEnfermedades;
	private JList<String> listaVacunas;
	private PlaceholderTextField campoGenero;
	private PlaceholderTextField campoEdad;
	private JLabel cartelUltimaPrueba;

	JPanel panelAgrupador1;

	private JCheckBox checkEmbarazada;
	private JDateChooser fechaUltimaPrueba;
	private JLabel cartelEdad;

	private JPanel panelAgrupador2;

	private JLabel botonEliminarEnfermedad;
	private JLabel botonAgregarVacuna;
	private JLabel botonEliminarVacuna;
	private JLabel botonAgregarEnfermedad;

	private BotonBlanco botonAtras;
	private BotonBlanco botonEditar;
	private BotonBlanco botonGuardar;  

	private JPanel panelGris;
	private JPanel panelAzul;
	
	private PlaceholderTextField cartelFecha;

	private ModoFormulario modoActual;

	public enum ModoFormulario {
		VISUALIZACION,
		EDICION,
		CREACION
	}

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
		inicializarFormulario();
		setModoActual(ModoFormulario.CREACION);
		setTitle("Agregar paciente");
	}

	public FormularioPaciente(Window ancestro, Paciente paciente, ModoFormulario modoActual) {	
		super(ancestro, ModalityType.APPLICATION_MODAL);
		inicializarFormulario();
		setModoActual(modoActual);
		mostrarInformacionPaciente(paciente);	

		JLabel cartelHistoriaClinica = new JLabel
				("N\u00famero de Historia Cl\u00ednica: " + String.valueOf(paciente.getHistoriaClinica().getId()));
		cartelHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelHistoriaClinica.setBounds(40, 226, 250, 22);
		panelAgrupador2.add(cartelHistoriaClinica);

	}

	public ModoFormulario getModoActual() {
		return modoActual;
	}

	public void inicializarFormulario() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel de informacion personal

		JLabel cartelInformacionMedica = new JLabel("Informaci\u00F3n m\u00E9dica:");
		cartelInformacionMedica.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacionMedica.setBackground(Color.WHITE);
		cartelInformacionMedica.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacionMedica.setBounds(55, 289, 219, 26);
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

		panelAgrupador1 = new JPanel();
		panelAgrupador1.setBackground(Color.WHITE);
		panelAgrupador1.setBounds(28, 31, 589, 236);
		panelAgrupador1.setBorder(BORDE_COMPONENTE);
		contentPane.add(panelAgrupador1);
		panelAgrupador1.setLayout(null);

		imagenPaciente = new JLabel("");
		imagenPaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		imagenPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imagenPaciente.setBounds(40, 36, 110, 110);
		imagenPaciente.setBorder(BORDE_COMPONENTE);
		panelAgrupador1.add(imagenPaciente);

		// Nombre

		cartelNombre = new JLabel("Nombre:");
		cartelNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelNombre.setBounds(173, 36, 64, 19);
		panelAgrupador1.add(cartelNombre);

		campoNombre = new PlaceholderTextField();
		campoNombre.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		campoNombre.setColumns(10);
		campoNombre.setBounds(240, 34, 310, 22);
		panelAgrupador1.add(campoNombre);

		// Primer apellido

		cartelPrimerApellido = new JLabel("Primer apellido:");
		cartelPrimerApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelPrimerApellido.setBounds(173, 65, 118, 22);
		panelAgrupador1.add(cartelPrimerApellido);

		campoPrimerApellido = new PlaceholderTextField();
		campoPrimerApellido.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoPrimerApellido.setColumns(10);
		campoPrimerApellido.setBounds(293, 65, 257, 22);
		panelAgrupador1.add(campoPrimerApellido);

		// Segundo apellido

		cartelSegundoApellido = new JLabel("Segundo apellido:");
		cartelSegundoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelSegundoApellido.setBounds(173, 95, 130, 22);
		panelAgrupador1.add(cartelSegundoApellido);

		campoSegundoApellido = new PlaceholderTextField();
		campoSegundoApellido.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoSegundoApellido.setColumns(10);
		campoSegundoApellido.setBounds(311, 95, 239, 22);
		panelAgrupador1.add(campoSegundoApellido);

		// Carne de identidad

		cartelCI = new JLabel("Carn\u00E9 de identidad:");
		cartelCI.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelCI.setBounds(173, 124, 142, 22);
		panelAgrupador1.add(cartelCI);

		campoCI = new PlaceholderTextField(); 
		campoCI.setCharacterLimit(11);
		campoCI.setInputFormat(PlaceholderTextField.InputFormat.NUMERIC);
		campoCI.setColumns(10);
		campoCI.setBounds(321, 124, 229, 22);
		panelAgrupador1.add(campoCI);
		campoCI.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				manejarCambio();
			}
			public void removeUpdate(DocumentEvent e) {
				manejarCambio();
			}
			public void changedUpdate(DocumentEvent e) {}

			private void manejarCambio() {
				if (campoCI.getText().length() < 6) {
					edadCalculada = false;
					campoEdad.setText("");
				}
				actualizarEdad();
				calcularGenero();
			}
		});


		// Direccion

		cartelDireccion = new JLabel("Direcci\u00F3n:");
		cartelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelDireccion.setBounds(40, 153, 75, 22);
		panelAgrupador1.add(cartelDireccion);

		campoDireccion = new PlaceholderTextField();
		campoDireccion.setColumns(10);
		campoDireccion.setBounds(117, 153, 433, 22);
		panelAgrupador1.add(campoDireccion);

		// Genero

		cartelGenero = new JLabel("G\u00E9nero:");
		cartelGenero.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelGenero.setBounds(40, 189, 60, 16);
		panelAgrupador1.add(cartelGenero);

		campoGenero = new PlaceholderTextField();
		campoGenero.setEditable(false);
		campoGenero.setInputFormat(InputFormat.ALPHABETIC);
		campoGenero.setColumns(10);
		campoGenero.setBounds(105, 185, 181, 22);
		panelAgrupador1.add(campoGenero);

		// Embarazada

		checkEmbarazada = new JCheckBox("Embarazada");
		checkEmbarazada.setEnabled(false);
		checkEmbarazada.setVisible(false);
		checkEmbarazada.setHorizontalAlignment(SwingConstants.LEFT);
		checkEmbarazada.setFont(new Font("Arial", Font.PLAIN, 16));
		checkEmbarazada.setBackground(Color.WHITE);
		checkEmbarazada.setBounds(297, 189, 118, 16);
		panelAgrupador1.add(checkEmbarazada);

		// Edad

		cartelEdad = new JLabel("Edad:");
		cartelEdad.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelEdad.setBounds(426, 188, 46, 19);
		panelAgrupador1.add(cartelEdad);

		campoEdad = new PlaceholderTextField();
		campoEdad.setEditable(false);
		campoEdad.setInputFormat(InputFormat.NUMERIC);
		campoEdad.setColumns(10);
		campoEdad.setBounds(473, 186, 77, 22);
		panelAgrupador1.add(campoEdad);

		// Panel informacion medica

		panelAgrupador2 = new JPanel();
		panelAgrupador2.setBackground(Color.WHITE);
		panelAgrupador2.setBounds(28, 302, 589, 272);
		panelAgrupador2.setBorder(BORDE_COMPONENTE);
		contentPane.add(panelAgrupador2);
		panelAgrupador2.setLayout(null);

		// Enfermedades cronicas

		JLabel cartelEnfermedadesCronicas = new JLabel("Enfermedades cr\u00F3nicas:");
		cartelEnfermedadesCronicas.setBounds(40, 64, 176, 22);
		cartelEnfermedadesCronicas.setFont(new Font("Arial", Font.PLAIN, 16));
		panelAgrupador2.add(cartelEnfermedadesCronicas);

		JScrollPane scrollPaneEnfermedades = new JScrollPane();
		scrollPaneEnfermedades.setBounds(40, 91, 240, 122);
		scrollPaneEnfermedades.setBorder(BORDE_COMPONENTE);
		scrollPaneEnfermedades.setFocusable(false);
		panelAgrupador2.add(scrollPaneEnfermedades);

		listModelEnfermedades = new DefaultListModel<>();

		listaEnfermedades = new JList<>(listModelEnfermedades);
		listaEnfermedades.setFocusable(false);
		listaEnfermedades.setBorder(null);
		listaEnfermedades.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneEnfermedades.setViewportView(listaEnfermedades);

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

		// Vacunas aplicadas

		JLabel cartelVacunas = new JLabel("Vacunas aplicadas:");
		cartelVacunas.setBounds(308, 64, 155, 22);
		cartelVacunas.setFont(new Font("Arial", Font.PLAIN, 16));
		panelAgrupador2.add(cartelVacunas);

		JScrollPane scrollPaneVacunas = new JScrollPane();
		scrollPaneVacunas.setFocusable(false);
		scrollPaneVacunas.setBounds(308, 91, 240, 122);
		scrollPaneVacunas.setBorder(BORDE_COMPONENTE);
		panelAgrupador2.add(scrollPaneVacunas);

		listModelVacunas = new DefaultListModel<>();

		listaVacunas = new JList<>(listModelVacunas);
		listaVacunas.setFocusable(false);
		listaVacunas.setBorder(null);
		listaVacunas.setFont(new Font("Arial", Font.PLAIN, 16));
		scrollPaneVacunas.setViewportView(listaVacunas);

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

		// Fecha ultima prueba citologica

		cartelUltimaPrueba = new JLabel("Fecha de \u00FAltima prueba citol\u00F3gica:");
		cartelUltimaPrueba.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelUltimaPrueba.setBounds(40, 29, 240, 22);
		cartelUltimaPrueba.setVisible(false);
		panelAgrupador2.add(cartelUltimaPrueba);

		fechaUltimaPrueba = new JDateChooser();
		fechaUltimaPrueba.setToolTipText("Introduzca la fecha o seleccione por el calendario");
		fechaUltimaPrueba.setFont(new Font("Arial", Font.PLAIN, 16));
		fechaUltimaPrueba.setDateFormatString("d/MMM/yyyy");
		fechaUltimaPrueba.setBounds(308, 29, 238, 22);
		fechaUltimaPrueba.setMaxSelectableDate(new Date());
		fechaUltimaPrueba.setVisible(false);
		panelAgrupador2.add(fechaUltimaPrueba); 
		
		cartelFecha = new PlaceholderTextField();
		cartelFecha.setVisible(false);
		cartelFecha.setEditable(false);
		cartelFecha.setInputFormat(InputFormat.ANY);
		cartelFecha.setColumns(10);
		cartelFecha.setBounds(308, 29, 240, 22);
		panelAgrupador2.add(cartelFecha);

		// Paneles fondo

		JPanel panelVerde = new JPanel();
		panelVerde.setBounds(0, 0, 644, 124);
		panelVerde.setBackground(COLOR_VERDE);
		contentPane.add(panelVerde);
		panelVerde.setLayout(null);

		panelAzul = new JPanel();
		panelAzul.setBounds(0, 499, 650, 194);
		panelAzul.setBackground(COLOR_AZUL);
		contentPane.add(panelAzul);
		panelAzul.setLayout(null);

		panelGris = new JPanel();
		panelGris.setBackground(SystemColor.menu);
		panelGris.setBounds(0, 134, 644, 354);
		contentPane.add(panelGris);
		panelGris.setLayout(null);

		botonAtras = new BotonBlanco("ATR\u00C1S");
		botonAtras.setBounds(343, 99, 130, 30);
		panelAzul.add(botonAtras);
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonAtras.setToolTipText("Clic para volver a la ventana principal");
		botonAtras.setFont(new Font("Arial", Font.PLAIN, 18));

		botonGuardar = new BotonBlanco("GUARDAR");
		botonGuardar.setBounds(176, 99, 130, 30);
		panelAzul.add(botonGuardar);
		botonGuardar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonGuardar.setToolTipText("Clic para guardar los datos actuales");
		botonGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getModoActual() == ModoFormulario.CREACION) {
					agregarPaciente();
				} else if (getModoActual() == ModoFormulario.EDICION) {

				}
			}
		});

		botonEditar = new BotonBlanco("EDITAR");
		botonEditar.setBounds(176, 99, 130, 30);
		panelAzul.add(botonEditar);
		botonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setModoActual(ModoFormulario.EDICION);
			}
		});
		botonEditar.setToolTipText("Clic para editar los datos del formulario");
		botonEditar.setFont(new Font("Arial", Font.PLAIN, 18));	   	
	}

	public void mostrarInformacionPaciente(Paciente paciente) {
		campoNombre.setText(paciente.getNombre());
		campoPrimerApellido.setText(paciente.getPrimerApellido());
		campoSegundoApellido.setText(paciente.getSegundoApellido());
		campoCI.setText(paciente.getCI());
		campoDireccion.setText(paciente.getDireccion());
		campoEdad.setText(String.valueOf(paciente.getEdad()));
		campoGenero.setText(paciente.getGenero());

		if (paciente instanceof Mujer && ((Mujer) paciente).getFechaUltimaRevision() != null) {		
			Date fecha = ((Mujer) paciente).getFechaUltimaRevision();
			cartelUltimaPrueba.setVisible(true);
			fechaUltimaPrueba.setDate(fecha);
			fechaUltimaPrueba.setVisible(false);
			SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
			String fechaFormateada = formato.format(fecha);
			cartelFecha.setText(fechaFormateada);
			cartelFecha.setVisible(true);	
			
			checkEmbarazada.setSelected(((Mujer) paciente).isEmbarazada());
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
		TextDialog dialogo = new TextDialog((JDialog)this, 
				"Agregar enfermedad cr\u00F3nica", 
				"Introduzca una enfermedad cr\u00F3nica para agregar");
		dialogo.setVisible(true);

		if (dialogo.isConfirmado()) {
			String texto = dialogo.getTextoIngresado();
			if (!texto.isEmpty()) {
				listModelEnfermedades.addElement(texto);
			}
		}
	}

	protected void agregarVacuna() {
		TextDialog dialogo = new TextDialog((JDialog)this, 
				"Agregar vacuna aplicada", 
				"Introduzca una vacuna aplicada para agregar");
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

	private boolean edadCalculada = false; 

	private void actualizarEdad() {
		String ci = campoCI.getText();

		if (ci.length() >= 6 && !edadCalculada) {
			int edad = Validations.getYearsFromString(ci);
			if (edad == -1) {
				campoEdad.setText("inv\u00E1lida");
				new InfoDialog(
						this,
						"Error al calcular la edad",
						"La fecha de nacimiento es inv\u00E1lida o no tiene un formato correcto."
						).setVisible(true);
			} else {
				campoEdad.setText(String.valueOf(edad));
				edadCalculada = true;
			}
		}
	}
	
	private void actualizarGenero(boolean activo) {
		fechaUltimaPrueba.setVisible(activo);
		fechaUltimaPrueba.setEnabled(activo);
		cartelUltimaPrueba.setVisible(activo);
		cartelUltimaPrueba.setEnabled(activo);
		checkEmbarazada.setVisible(activo);
		checkEmbarazada.setEnabled(activo);

		if (!activo) {
			fechaUltimaPrueba.setDate(null); 
			checkEmbarazada.setSelected(false);
		}
		    		
	}
	
	private void calcularGenero() {
        String ci = campoCI.getText();
        if (ci.length() >= 10) { // El penúltimo dígito está en la posición 10 (índice 9)
            char penultimoDigito = ci.charAt(9); 
            if (Character.isDigit(penultimoDigito)) {
            	int digito = Character.getNumericValue(penultimoDigito);
            	if (digito % 2 == 0) {
            		campoGenero.setText("Femenino");   
            		actualizarGenero(true);
            	} else {
            		campoGenero.setText("Masculino");
            		actualizarGenero(false);
            	}
            } else {
            	campoGenero.setText("Inválido");
            }
        } else {
        	campoGenero.setText(""); // Vacío si no hay suficiente longitud
        	actualizarGenero(false);
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
			boolean estaEmbarazada = checkEmbarazada.isSelected();
			Date fechaUltimaPruebaSeleccionada = fechaUltimaPrueba.getDate(); // Obtener la fecha seleccionada del JDateChooser

			// Validar que los campos obligatorios no estï¿½n vacï¿½os
			if (nombre.isEmpty() || primerApellido.isEmpty() || ci.isEmpty()) {
				throw new IllegalArgumentException("Los campos Nombre, Primer Apellido y CI son obligatorios.");
			}

			// Validar gï¿½nero y embarazo basado en el CI
			boolean ciEsFemenino = Validations.isFemale(ci);

			if (!ciEsFemenino && estaEmbarazada) {
				throw new IllegalArgumentException("Un paciente masculino no puede estar embarazado.");
			}

			if(cmf.isCiRepited(ci)) {
				throw new IllegalArgumentException("El CI proporcionado ya est\u00E1 registrado.");
			}

			// Obtener listas de enfermedades crï¿½nicas y vacunas
			ArrayList<String> enfermedadesCronicas = new ArrayList<>(listModelEnfermedades.size());
			for (int i = 0; i < listModelEnfermedades.size(); i++) {
				enfermedadesCronicas.add(listModelEnfermedades.getElementAt(i));
			}

			ArrayList<String> vacunas = new ArrayList<>(listModelVacunas.size());
			for (int i = 0; i < listModelVacunas.size(); i++) {
				vacunas.add(listModelVacunas.getElementAt(i));
			}

			// Llamar al mï¿½todo agregarPaciente
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
				JOptionPane.showMessageDialog(contentPane, "Paciente agregado exitosamente.", "\u00C9xito", JOptionPane.INFORMATION_MESSAGE);
				dispose(); // Cerrar el formulario
			} else {
				JOptionPane.showMessageDialog(contentPane, "No se pudo agregar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(contentPane, "Ocurri\u00F3 un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setModoActual(ModoFormulario modo) {
		modoActual = modo;

		if (getModoActual() == ModoFormulario.VISUALIZACION) {
			setTitle("Informaci\u00F3n del paciente");
			activarEdicion(false);
			
		} else {	
			activarEdicion(true);
			if (getModoActual() == ModoFormulario.EDICION) {
				setTitle("Editar paciente");
			} else if (getModoActual() == ModoFormulario.CREACION) {
				setTitle("Crear del paciente");
			}
		}
	}

	public void activarEdicion(boolean activo) {
		campoNombre.setEditable(activo);
		campoPrimerApellido.setEditable(activo);
		campoSegundoApellido.setEditable(activo);
		campoCI.setEditable(activo);
		campoDireccion.setEditable(activo);

		botonAgregarEnfermedad.setVisible(activo);
		botonAgregarVacuna.setVisible(activo);
		botonEliminarEnfermedad.setVisible(activo);
		botonEliminarVacuna.setVisible(activo);		
		
		botonGuardar.setEnabled(activo);
		botonGuardar.setVisible(activo);
		botonEditar.setEnabled(!activo);
		botonEditar.setVisible(!activo);

		revalidate();
		repaint();
	}
}
