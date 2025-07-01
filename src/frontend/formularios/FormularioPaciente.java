package frontend.formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import service.Validations;
import util.ConstantesFrontend;
import util.UtilFecha;
import util.UtilSonido;

import com.toedter.calendar.JDateChooser;

import entidades.CMF;
import entidades.personal.Mujer;
import entidades.personal.Paciente;
import entidades.personal.Usuario;
import entidades.personal.Usuario.TipoDeRol;
import frontend.ui.botones.BotonBlanco;
import frontend.ui.botones.ImageButtonLabel;
import frontend.ui.dialogs.InfoDialog;
import frontend.ui.dialogs.InfoDialog.Estado;
import frontend.ui.dialogs.QuestionDialog;
import frontend.ui.dialogs.TextDialog;
import frontend.ui.placeholders.PlaceholderTextField;
import frontend.ui.placeholders.PlaceholderTextField.InputFormat;

public class FormularioPaciente extends JDialog implements ConstantesFrontend {

	private JPanel contentPane;
	private JPanel panelGris;
	private JPanel panelAzul;
	private JPanel panelAgrupador1;
	private JPanel panelAgrupador2;
	
	private PlaceholderTextField campoNombre;
	private PlaceholderTextField campoPrimerApellido;
	private PlaceholderTextField campoSegundoApellido;
	private PlaceholderTextField campoCI;
	private PlaceholderTextField campoDireccion;
	private PlaceholderTextField campoGenero;
	private PlaceholderTextField campoEdad;
	
	private DefaultListModel<String> listModelEnfermedades;
	private DefaultListModel<String> listModelVacunas;
	private JList<String> listaEnfermedades;
	private JList<String> listaVacunas;
	
	private JCheckBox checkEmbarazada;
	private JLabel cartelEmbarazada;
	private JDateChooser fechaUltimaPrueba;
	private PlaceholderTextField cartelFecha;
	private JLabel cartelUltimaPrueba;
	
	private JLabel cartelEnRiesgo;
	private JLabel cartelHistoriaClinica;
	
	private JLabel imagenPaciente;
	private JLabel botonAgregarEnfermedad;
	private JLabel botonEliminarEnfermedad;
	private JLabel botonAgregarVacuna;
	private JLabel botonEliminarVacuna;
	private ImageButtonLabel botonHistoriaClinica;
	
	private BotonBlanco botonCancelar;
	private BotonBlanco botonEditar;
	private BotonBlanco botonGuardar;
	
	private boolean edadCalculada = false;
	
	private Paciente paciente;
	private ModoFormulario modoActual;

	public enum ModoFormulario {
		VISUALIZACION,
		EDICION,
		CREACION
	}

	public FormularioPaciente(Window ancestro) {
		super(ancestro, ModalityType.APPLICATION_MODAL);
		inicializarFormulario();
		setModoActual(ModoFormulario.CREACION);
		setTitle("Agregar paciente");
	}

	/**
	 * @wbp.parser.constructor
	 */
	public FormularioPaciente(Window ancestro, Paciente paciente, ModoFormulario modoActual) {
		super(ancestro, ModalityType.APPLICATION_MODAL);
		this.paciente = paciente;
		inicializarFormulario();
		setModoActual(modoActual);
		mostrarInformacionPaciente(paciente);		
	}

	public void abrirHistoriaClinica() {
		FormularioHistoriaClinica formHC = new FormularioHistoriaClinica(this, paciente.getHistoriaClinica());

		// Obtener la posición de este formulario (el de paciente)
		Point location = getLocationOnScreen();

		// Posicionar el nuevo formulario a la derecha
		formHC.setLocation(location.x + 100, location.y + 100);
		formHC.setVisible(true);
	}

	public ModoFormulario getModoActual() {
		return modoActual;
	}

	public void inicializarFormulario() {
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel de informacion personal

		JLabel cartelInformacionMedica = new JLabel("Informaci\u00F3n m\u00E9dica");
		cartelInformacionMedica.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacionMedica.setBackground(Color.WHITE);
		cartelInformacionMedica.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacionMedica.setBounds(55, 289, 219, 26);
		cartelInformacionMedica.setOpaque(true);
		cartelInformacionMedica.setBorder(BORDE_PEQUENO);
		contentPane.add(cartelInformacionMedica);

		JLabel cartelInformacionPersonal = new JLabel("Informaci\u00F3n personal");
		cartelInformacionPersonal.setBounds(55, 18, 232, 26);
		contentPane.add(cartelInformacionPersonal);
		cartelInformacionPersonal.setOpaque(true);
		cartelInformacionPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		cartelInformacionPersonal.setFont(new Font("Arial", Font.BOLD, 18));
		cartelInformacionPersonal.setBorder(BORDE_PEQUENO);
		cartelInformacionPersonal.setBackground(Color.WHITE);

		panelAgrupador1 = new JPanel();
		panelAgrupador1.setBackground(Color.WHITE);
		panelAgrupador1.setBounds(28, 31, 589, 236);
		panelAgrupador1.setBorder(BORDE_GRANDE);
		contentPane.add(panelAgrupador1);
		panelAgrupador1.setLayout(null);

		imagenPaciente = new JLabel("");
		imagenPaciente.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/Logo peque.png")));
		imagenPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		imagenPaciente.setBounds(40, 36, 110, 110);
		imagenPaciente.setBorder(BORDE_COMPONENTE);
		panelAgrupador1.add(imagenPaciente);

		// Nombre

		JLabel cartelNombre = new JLabel("Nombre:");
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

		JLabel cartelPrimerApellido = new JLabel("Primer apellido:");
		cartelPrimerApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelPrimerApellido.setBounds(173, 65, 118, 22);
		panelAgrupador1.add(cartelPrimerApellido);

		campoPrimerApellido = new PlaceholderTextField();
		campoPrimerApellido.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoPrimerApellido.setColumns(10);
		campoPrimerApellido.setBounds(293, 65, 257, 22);
		panelAgrupador1.add(campoPrimerApellido);

		// Segundo apellido

		JLabel cartelSegundoApellido = new JLabel("Segundo apellido:");
		cartelSegundoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelSegundoApellido.setBounds(173, 95, 130, 22);
		panelAgrupador1.add(cartelSegundoApellido);

		campoSegundoApellido = new PlaceholderTextField();
		campoSegundoApellido.setInputFormat(PlaceholderTextField.InputFormat.ALPHABETIC);
		campoSegundoApellido.setColumns(10);
		campoSegundoApellido.setBounds(311, 95, 239, 22);
		panelAgrupador1.add(campoSegundoApellido);

		// Carne de identidad

		JLabel cartelCI = new JLabel("Carn\u00E9 de identidad:");
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

			public void changedUpdate(DocumentEvent e) {
				// No usado
			}

			private void manejarCambio() {
				final String ci = campoCI.getText().trim();
				if (ci.length() >= 6) {
					actualizarEdad();
				} else {
					edadCalculada = false;
					campoEdad.setText("");
				}
				calcularGenero();
			}
		});

		// Direccion

		JLabel cartelDireccion = new JLabel("Direcci\u00F3n:");
		cartelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelDireccion.setBounds(40, 153, 75, 22);
		panelAgrupador1.add(cartelDireccion);

		campoDireccion = new PlaceholderTextField();
		campoDireccion.setColumns(10);
		campoDireccion.setBounds(117, 153, 433, 22);
		panelAgrupador1.add(campoDireccion);

		// Genero

		JLabel cartelGenero = new JLabel("G\u00E9nero:");
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
		checkEmbarazada.setHorizontalAlignment(SwingConstants.LEFT);
		checkEmbarazada.setFont(new Font("Arial", Font.PLAIN, 16));
		checkEmbarazada.setBackground(Color.WHITE);
		checkEmbarazada.setBounds(297, 189, 118, 16);
		panelAgrupador1.add(checkEmbarazada);

		cartelEmbarazada = new JLabel();
		cartelEmbarazada.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelEmbarazada.setBounds(297, 189, 118, 16);
		panelAgrupador1.add(cartelEmbarazada);

		// Edad

		JLabel cartelEdad = new JLabel("Edad:");
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
		panelAgrupador2.setBorder(BORDE_GRANDE);
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

		botonEliminarEnfermedad = new ImageButtonLabel(
				new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarEnfermedadesSeleccionadas();
			}
		});
		botonEliminarEnfermedad.setToolTipText("Clic para borrar los elemento(s) selecionado(s) de la lista");
		botonEliminarEnfermedad.setBounds(258, 64, 22, 22);
		panelAgrupador2.add(botonEliminarEnfermedad);

		botonAgregarEnfermedad = new ImageButtonLabel(
				new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
		botonAgregarEnfermedad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarEnfermedadCronica();
			}
		});
		botonAgregarEnfermedad.setToolTipText("Clic para agregar nuevo elemento a la lista");
		botonAgregarEnfermedad.setBounds(234, 64, 22, 22);
		panelAgrupador2.add(botonAgregarEnfermedad);
		
		cartelEnRiesgo = new JLabel(" En riesgo");
		cartelEnRiesgo.setIcon(new ImageIcon(FormularioPaciente.class.getResource("/fotos/riesgo25x25.png")));
		cartelEnRiesgo.setHorizontalTextPosition(SwingConstants.LEFT);
		cartelEnRiesgo.setHorizontalAlignment(SwingConstants.LEFT);
		cartelEnRiesgo.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelEnRiesgo.setBounds(180, 225, 100, 25);
		cartelEnRiesgo.setVisible(false);
		panelAgrupador2.add(cartelEnRiesgo);

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

		botonEliminarVacuna = new ImageButtonLabel(
				new ImageIcon(FormularioPaciente.class.getResource("/fotos/trash-22x22.png")));
		botonEliminarVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarVacunasSeleccionadas();
			}
		});
		botonEliminarVacuna.setToolTipText("Clic para borrar los elemento(s) selecionado(s) de la lista");
		botonEliminarVacuna.setBounds(526, 64, 22, 22);
		panelAgrupador2.add(botonEliminarVacuna);

		botonAgregarVacuna = new ImageButtonLabel(
				new ImageIcon(FormularioPaciente.class.getResource("/fotos/agregar-22x22.png")));
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
		
		// Historia clinica
		
		cartelHistoriaClinica = new JLabel(
				"Historia Cl\u00EDnica #0");
		cartelHistoriaClinica.setHorizontalAlignment(SwingConstants.RIGHT);
		cartelHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 16));
		cartelHistoriaClinica.setBounds(308, 226, 206, 22);
		panelAgrupador2.add(cartelHistoriaClinica);

		botonHistoriaClinica = new ImageButtonLabel(
				new ImageIcon(FormularioPaciente.class.getResource("/fotos/historia-clinica.png")));
		botonHistoriaClinica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirHistoriaClinica();
			}
		});
		botonHistoriaClinica.setText("");
		botonHistoriaClinica.setBounds(526, 223, 20, 25);
		panelAgrupador2.add(botonHistoriaClinica);

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

		// Botones

		botonCancelar = new BotonBlanco("CANCELAR");
		botonCancelar.setBounds(343, 99, 130, 30);
		panelAzul.add(botonCancelar);
		botonCancelar.setToolTipText("Clic para cancelar la operación actual");
		botonCancelar.setFont(new Font("Arial", Font.PLAIN, 18));
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pedirConfirmacion();
			}
		});

		botonCancelar.setVisible(modoActual == ModoFormulario.CREACION || modoActual == ModoFormulario.EDICION);

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
		            guardarEdicionPaciente();
		        }
		    }
		});

		botonEditar = new BotonBlanco("EDITAR");
		botonEditar.setBounds(255, 99, 130, 30);
		panelAzul.add(botonEditar);
		botonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestionDialog dialogo = new QuestionDialog(FormularioPaciente.this,
						"Editar paciente", "¿Deseas editar la información de este paciente?");
				dialogo.setVisible(true);

				if (dialogo.esConfirmado()) {
					setModoActual(ModoFormulario.EDICION);
				}
			}
		});
		botonEditar.setToolTipText("Clic para editar los datos del formulario");
		botonEditar.setFont(new Font("Arial", Font.PLAIN, 18));
		
		UtilSonido.reproducir("sonidos/ventana.wav");
	}

	public void pedirConfirmacion() {
	    if (modoActual == ModoFormulario.CREACION) {
	        QuestionDialog dialogo = new QuestionDialog(this,
	                "Cancelar creación",
	                "¿Estás seguro de que deseas cancelar la creación del paciente?\nLos datos introducidos se perderán.");
	        dialogo.setVisible(true);

	        if (dialogo.esConfirmado()) {
	            dispose();
	        }
	    } else if (modoActual == ModoFormulario.EDICION) {
	        QuestionDialog dialogo = new QuestionDialog(this,
	                "Cancelar edición",
	                "¿Deseas cancelar la edición y volver a la visualización?");
	        dialogo.setVisible(true);

	        if (dialogo.esConfirmado()) {
	            setModoActual(ModoFormulario.VISUALIZACION);
	        }
	    } else {
	        dispose();
	    }
	}

	public void mostrarInformacionPaciente(Paciente paciente) {
	    listModelEnfermedades.clear();
	    listModelVacunas.clear();

	    campoNombre.setText(paciente.getNombre());
	    campoPrimerApellido.setText(paciente.getPrimerApellido());
	    campoSegundoApellido.setText(paciente.getSegundoApellido());
	    campoCI.setText(paciente.getCI());
	    campoDireccion.setText(paciente.getDireccion());
	    campoEdad.setText(String.valueOf(paciente.getEdad()));
	    campoGenero.setText(paciente.getGenero());

	    if (paciente.getHistoriaClinica() != null) {
	        cartelHistoriaClinica.setText("Historia Clínica #" + paciente.getHistoriaClinica().getId());
	    } else {
	        cartelHistoriaClinica.setText("Historia Clínica #0");
	    }

	    boolean esMujer = paciente instanceof Mujer;

	    cartelUltimaPrueba.setVisible(esMujer);
	    cartelFecha.setVisible(esMujer);
	    cartelEmbarazada.setVisible(esMujer && ((Mujer) paciente).isEmbarazada());

	    if (esMujer) {
	        Mujer mujer = (Mujer) paciente;
	        LocalDate fecha = mujer.getFechaUltimaRevision();

	        Date fechaConvertida = UtilFecha.convertirALegacyDate(fecha);
	        if (fechaConvertida != null) {
	            fechaUltimaPrueba.setDate(fechaConvertida);
	            String textoFecha = UtilFecha.formatearLargoEsp(fecha);
	            cartelFecha.setText(textoFecha);
	        } else {
	            cartelFecha.setText("Nunca realizada");
	        }

	        checkEmbarazada.setSelected(mujer.isEmbarazada());
	        cartelEmbarazada.setText(mujer.isEmbarazada() ? "Embarazada" : "");
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
		TextDialog dialogo = new TextDialog((JDialog) this,
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
		TextDialog dialogo = new TextDialog((JDialog) this,
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

	private void calcularGenero() {
		String ci = campoCI.getText();
		if (ci.length() >= 10 && Character.isDigit(ci.charAt(9))) {
			int digito = Character.getNumericValue(ci.charAt(9));
			boolean femenino = digito % 2 != 0;
			campoGenero.setText(femenino ? "Femenino" : "Masculino");
			actualizarGenero(femenino);
		}
	}

	private void actualizarGenero(boolean activo) {
		if (modoActual != ModoFormulario.VISUALIZACION) {
			fechaUltimaPrueba.setVisible(activo);
			fechaUltimaPrueba.setEnabled(activo);
			cartelUltimaPrueba.setVisible(activo);
			checkEmbarazada.setVisible(activo);
			checkEmbarazada.setEnabled(activo);
			if (!activo) {
				fechaUltimaPrueba.setDate(null);
				checkEmbarazada.setSelected(false);
			}
		}
	}

	private void actualizarEdad() {
		String ci = campoCI.getText();
		if (ci.length() >= 6 && !edadCalculada) {
			int edad = Validations.getYearsFromString(ci);
			if (edad == -1) {
				campoEdad.setText("inválida");
				new InfoDialog(this, "Error", "La fecha de nacimiento es inválida.", Estado.ERROR).setVisible(true);
			} else {
				campoEdad.setText(String.valueOf(edad));
				edadCalculada = true;
			}
		}
	}

	private String[] obtenerDatosBasicos() {
		String nombre = Validations.capitalize(campoNombre.getText().trim());
		String primerApellido = Validations.capitalize(campoPrimerApellido.getText().trim());
		String segundoApellido = Validations.capitalize(campoSegundoApellido.getText().trim());
		String ci = campoCI.getText().trim();
		String direccion = campoDireccion.getText().trim();

		if (nombre.isEmpty() || primerApellido.isEmpty() || ci.isEmpty()) {
			throw new IllegalArgumentException("Los campos Nombre, Primer Apellido y CI son obligatorios.");
		}

		return new String[] { nombre, primerApellido, segundoApellido, ci, direccion };
	}

	private boolean validarEmbarazo(String ci, boolean embarazada) {
		if (!Validations.isFemale(ci) && embarazada) {
			throw new IllegalArgumentException("Un paciente masculino no puede estar embarazado.");
		}
		return embarazada;
	}

	private LocalDate obtenerFechaUltimaPrueba() {
		Date fecha = fechaUltimaPrueba.getDate();

		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private ArrayList<String> obtenerListaEnfermedades() {
		ArrayList<String> enfermedadesCronicas = new ArrayList<>(listModelEnfermedades.size());
		for (int i = 0; i < listModelEnfermedades.size(); i++) {
			enfermedadesCronicas.add(listModelEnfermedades.getElementAt(i));
		}
		return enfermedadesCronicas;
	}

	private ArrayList<String> obtenerListaVacunas() {
		ArrayList<String> vacunas = new ArrayList<>(listModelVacunas.size());
		for (int i = 0; i < listModelVacunas.size(); i++) {
			vacunas.add(listModelVacunas.getElementAt(i));
		}
		return vacunas;
	}

	public void agregarPaciente() {
		CMF cmf = CMF.getInstance();

		try {
			String[] datos = obtenerDatosBasicos(); // nombre, primerApellido, segundoApellido, ci, direccion
			boolean embarazada = validarEmbarazo(datos[3], checkEmbarazada.isSelected());
			LocalDate fechaUltima;

			if (paciente instanceof Mujer && ((Mujer) paciente).getFechaUltimaRevision() != null) {
				fechaUltima = ((Mujer) paciente).getFechaUltimaRevision();
			} else if (fechaUltimaPrueba == null || fechaUltimaPrueba.getDate() == null) {
				fechaUltima = null;
			} else {
				fechaUltima = obtenerFechaUltimaPrueba();
			}
			if (fechaUltima != null && fechaUltima.isAfter(LocalDate.now())) {
				throw new IllegalArgumentException("La fecha de la \u00FAltima prueba no puede ser futura.");
			}

			ArrayList<String> enfermedades = obtenerListaEnfermedades();
			ArrayList<String> vacunas = obtenerListaVacunas();

			if (cmf.isCiRepited(datos[3])) {
				throw new IllegalArgumentException("El CI proporcionado ya est\u00E1 registrado.");
			}

			boolean agregado = cmf.agregarPaciente(
					datos[0], datos[1], datos[2], enfermedades, vacunas,
					datos[3], embarazada, fechaUltima, datos[4]);

			if (agregado) {
				new InfoDialog(SwingUtilities.getWindowAncestor(contentPane),
						"\u00C9xito",
						"Paciente agregado exitosamente.", Estado.EXITO).setVisible(true);
				dispose();
			} else {
				new InfoDialog(SwingUtilities.getWindowAncestor(contentPane),
						"Error",
						"No se pudo agregar el paciente.", Estado.ERROR).setVisible(true);
			}
		} catch (IllegalArgumentException ex) {
			new InfoDialog(SwingUtilities.getWindowAncestor(contentPane),
					"Error",
					ex.getMessage(),
					Estado.ERROR).setVisible(true);
		} catch (Exception ex) {
			new InfoDialog(SwingUtilities.getWindowAncestor(contentPane),
					"Error",
					"Ocurri\u00F3 un error inesperado: " + ex.getMessage(),
					Estado.ERROR).setVisible(true);
		}
	}
	
	private void guardarEdicionPaciente() {
	    try {
	        String[] datos = obtenerDatosBasicos(); // nombre, primerApellido, segundoApellido, ci, direccion
	        ArrayList<String> enfermedades = obtenerListaEnfermedades();
	        ArrayList<String> vacunas = obtenerListaVacunas();

	        Boolean estadoEmbarazo = null;
	        LocalDate fechaCitologia = null;

	        if (paciente instanceof Mujer) {
	            estadoEmbarazo = validarEmbarazo(datos[3], checkEmbarazada.isSelected());

	            if (fechaUltimaPrueba != null && fechaUltimaPrueba.getDate() != null) {
	                fechaCitologia = obtenerFechaUltimaPrueba();
	                if (fechaCitologia.isAfter(LocalDate.now())) {
	                    throw new IllegalArgumentException("La fecha de la última prueba no puede ser futura.");
	                }
	            }
	        }

	        CMF.getInstance().editarPaciente(
	            paciente,
	            datos[0], // nuevoNombre
	            datos[1], // nuevoPrimerApellido
	            datos[2], // nuevoSegundoApellido
	            datos[4], // nuevaDireccion
	            estadoEmbarazo,
	            fechaCitologia,
	            enfermedades,
	            vacunas
	        );

	        new InfoDialog(FormularioPaciente.this,
	            "Éxito",
	            "Paciente actualizado exitosamente.",
	            Estado.EXITO).setVisible(true);

	        setModoActual(ModoFormulario.VISUALIZACION);

	    } catch (IllegalArgumentException ex) {
	        new InfoDialog(FormularioPaciente.this, "Error", ex.getMessage(), Estado.ERROR).setVisible(true);
	    } catch (Exception ex) {
	        new InfoDialog(FormularioPaciente.this,
	            "Error",
	            "Ocurrió un error inesperado: " + ex.getMessage(),
	            Estado.ERROR).setVisible(true);
	    }
	}

	public void setModoActual(ModoFormulario modo) {
	    this.modoActual = modo;

	    TipoDeRol rol = CMF.getInstance().getUsuario().getRole();

	    // Si es enfermera, forzar modo visualización, solo puede ver
	    if (rol.equals(Usuario.TipoDeRol.ENFERMERA)) {
	        modo = ModoFormulario.VISUALIZACION;
	        this.modoActual = modo;
	    }

	    // Mostrar botones según modo y rol
	    botonCancelar.setVisible(modo != ModoFormulario.VISUALIZACION);
	    botonGuardar.setVisible(modo != ModoFormulario.VISUALIZACION);

	    // Botón editar visible sólo en visualización y si es médico
	    botonEditar.setVisible(modo == ModoFormulario.VISUALIZACION && rol.equals(Usuario.TipoDeRol.MÉDICO));

	    boolean activo = modo != ModoFormulario.VISUALIZACION;
	    activarEdicion(activo);

	    if (modo == ModoFormulario.VISUALIZACION || modo == ModoFormulario.EDICION)
	        mostrarInformacionPaciente(paciente);

	    cartelEnRiesgo.setVisible(modo == ModoFormulario.VISUALIZACION && paciente.estaEnRiesgo());
	    cartelHistoriaClinica.setVisible(modo != ModoFormulario.CREACION);
	    botonHistoriaClinica.setVisible(modo != ModoFormulario.CREACION);

	    switch (modo) {
	        case CREACION:
	            setTitle("Agregar paciente");
	            break;
	        case EDICION:
	            setTitle("Editar paciente");
	            break;
	        case VISUALIZACION:
	        default:
	            setTitle("Información del paciente");
	            break;
	    }
	}

	public void activarEdicion(boolean activo) {
	    campoNombre.setEditable(activo);
	    campoPrimerApellido.setEditable(activo);
	    campoSegundoApellido.setEditable(activo);
	    campoCI.setEditable(modoActual == ModoFormulario.CREACION);
	    campoDireccion.setEditable(activo);

	    botonAgregarEnfermedad.setVisible(activo);
	    botonEliminarEnfermedad.setVisible(activo);
	    botonAgregarVacuna.setVisible(activo);
	    botonEliminarVacuna.setVisible(activo);

	    // Checkbox y fecha solo visible/activas en edición y solo para mujeres
	    boolean esMujer = paciente instanceof Mujer;
	    checkEmbarazada.setVisible(activo && esMujer);
	    checkEmbarazada.setEnabled(activo && esMujer);

	    fechaUltimaPrueba.setVisible(activo && esMujer);
	    fechaUltimaPrueba.setEnabled(activo && esMujer);

	    // Cartel embarazo solo visible en modo visualización y mujer embarazada
	    cartelEmbarazada.setVisible(!activo && esMujer && ((Mujer) paciente).isEmbarazada());

	    botonGuardar.setEnabled(activo);
	    botonEditar.setEnabled(!activo);

	    revalidate();
	    repaint();
	}
}
