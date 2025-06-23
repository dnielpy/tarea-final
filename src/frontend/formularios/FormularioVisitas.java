package frontend.formularios;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entidades.CMF;
import entidades.personal.Paciente;
import entidades.registros.Analisis;
import entidades.registros.Visita;
import frontend.ConstantesFrontend;
import frontend.ui.PlaceholderTextField;
import frontend.ui.PlaceholderTextField.InputFormat;
import frontend.ui.ScrollPaneModerno;
import frontend.ui.botones.ImageButtonLabel;
import frontend.ui.botones.BotonBlanco;
import frontend.ui.dialogs.InfoDialog;
import frontend.ui.dialogs.QuestionDialog;
import frontend.ui.dialogs.SelectorDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

import runner.Usuario;
import util.ConstantesAnalisis;
import util.ConstantesEspecialidades;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormularioVisitas extends JDialog implements ConstantesFrontend {

    private CMF cmf;
    private Usuario usuarioActual;
    private Visita visita;

    private PlaceholderTextField barraBuscarPacienteCI;
    private PlaceholderTextField campoFecha;
    private PlaceholderTextField campoDireccion;
    private PlaceholderTextField campoNombre;

    private JTextArea textDiagnostico;
    private JTextArea textTratamiento;

    private DefaultListModel<String> modeloEspecialidades;
    private DefaultListModel<String> modeloAnalisis;
    private JList<String> listaEspecialidades;
    private JList<String> listaAnalisis;

    private JPopupMenu popupResultados;
    private DefaultListModel<String> modeloResultados;
    private JList<String> listaResultados;
    private JLabel cartelHistoriaClinica;

    private ImageButtonLabel botonAgregarEspecialidad;
    private ImageButtonLabel botonEliminarEspecialidad;
    private ImageButtonLabel botonAgregarAnalisis;
    private ImageButtonLabel botonEliminarAnalisis;

    private BotonBlanco botonGuardar;
    private BotonBlanco botonEditar;
    private BotonBlanco botonAtras;

    private ModoFormulario modoActual;

    public enum ModoFormulario {
        VISUALIZACION,
        EDICION,
        CREACION
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioVisitas dialogo = new FormularioVisitas(null);
                dialogo.setLocationRelativeTo(null);
                dialogo.setModal(true);
                dialogo.setVisible(true);
            }
        });
    }

    public FormularioVisitas(Window ancestor) {
        super(ancestor, ModalityType.APPLICATION_MODAL);
        initComponents();
        setModoActual(ModoFormulario.CREACION);
        // Poner fecha actual en modo creaci\u00F3n por defecto sin visita
        campoFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")));
    }

    /**
     * Constructor con visita y modo
     * 
     * @wbp.parser.constructor
     */
    public FormularioVisitas(Window ancestor, Visita visita, ModoFormulario modo) {
        super(ancestor, ModalityType.APPLICATION_MODAL);
        this.visita = visita;
        initComponents();
        setModoActual(modo);
        cargarDatosVisita();
    }

    public void initComponents() {
        setIconImage(java.awt.Toolkit.getDefaultToolkit()
                .getImage(FormularioVisitas.class.getResource("/fotos/Logo peque.png")));
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (modoActual == ModoFormulario.CREACION || modoActual == ModoFormulario.EDICION) {
                    QuestionDialog confirmacion = new QuestionDialog(FormularioVisitas.this,
                            "Confirmar salida",
                            "Est\u00E1 a punto de salir del formulario.\nSe perder\u00E1n los cambios no guardados.\n¿Desea continuar?");
                    confirmacion.setVisible(true);
                    if (confirmacion.esConfirmado()) {
                        dispose();
                    }
                } else {
                    dispose();
                }
            }
        });
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        setSize(760, 640);

        cmf = CMF.getInstance();
        usuarioActual = cmf.getUsuario();
        modeloResultados = new DefaultListModel<>();
        popupResultados = new JPopupMenu();
        popupResultados.setFocusable(false);

        JLabel cartelRegistroVisita = new JLabel("Registro de la visita");
        cartelRegistroVisita.setOpaque(true);
        cartelRegistroVisita.setHorizontalAlignment(SwingConstants.CENTER);
        cartelRegistroVisita.setFont(new Font("Arial", Font.BOLD, 18));
        cartelRegistroVisita.setBorder(BORDE_PEQUENO);
        cartelRegistroVisita.setBackground(Color.WHITE);
        cartelRegistroVisita.setBounds(45, 210, 232, 26);
        getContentPane().add(cartelRegistroVisita);

        JPanel panelRegistroVisita = new JPanel();
        panelRegistroVisita.setBounds(25, 225, 700, 308);
        getContentPane().add(panelRegistroVisita);
        panelRegistroVisita.setLayout(null);
        panelRegistroVisita.setBorder(BORDE_GRANDE);
        panelRegistroVisita.setBackground(Color.WHITE);

        textDiagnostico = new JTextArea();
        textDiagnostico.setLineWrap(true); // Importante para evitar scroll horizontal en JTextArea
        textDiagnostico.setWrapStyleWord(true);
        textDiagnostico.setFont(new Font("Arial", Font.PLAIN, 16));
        ScrollPaneModerno diagnosticoScrollPane = new ScrollPaneModerno(textDiagnostico);
        diagnosticoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        diagnosticoScrollPane.setBounds(24, 45, 300, 95);
        panelRegistroVisita.add(diagnosticoScrollPane);

        JLabel lblDiagnostico = new JLabel("Diagn\u00F3stico:");
        lblDiagnostico.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDiagnostico.setBounds(24, 23, 170, 19);
        panelRegistroVisita.add(lblDiagnostico);

        textTratamiento = new JTextArea();
        textTratamiento.setLineWrap(true);
        textTratamiento.setWrapStyleWord(true);
        textTratamiento.setFont(new Font("Arial", Font.PLAIN, 16));
        ScrollPaneModerno tratamientoScrollPane = new ScrollPaneModerno(textTratamiento);
        tratamientoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tratamientoScrollPane.setBounds(363, 44, 300, 95);
        panelRegistroVisita.add(tratamientoScrollPane);

        JLabel lblTratamiento = new JLabel("Tratamiento:");
        lblTratamiento.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTratamiento.setBounds(363, 23, 170, 19);
        panelRegistroVisita.add(lblTratamiento);

        botonAgregarEspecialidad = new ImageButtonLabel((ImageIcon) null);
        botonAgregarEspecialidad.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SelectorDialog dialog = new SelectorDialog(FormularioVisitas.this, "Seleccionar especialidad",
                        "Seleccione una especialidad:", ConstantesEspecialidades.ESPECIALIDADES_REMITIDAS);
                dialog.setVisible(true);
                if (dialog.esConfirmado()) {
                    String seleccion = dialog.getSeleccionado();
                    if (!modeloEspecialidades.contains(seleccion)) {
                        modeloEspecialidades.addElement(seleccion);
                    } else {
                        new InfoDialog(FormularioVisitas.this, "Informaci\u00F3n", "La especialidad ya fue a\u00F1adida.")
                                .setVisible(true);
                    }
                }
            }
        });
        botonAgregarEspecialidad
                .setIcon(new ImageIcon(FormularioVisitas.class.getResource("/fotos/agregar-22x22.png")));
        botonAgregarEspecialidad.setToolTipText("Clic para agregar nuevo elemento a la lista");
        botonAgregarEspecialidad.setBounds(616, 152, 22, 22);
        panelRegistroVisita.add(botonAgregarEspecialidad);

        botonEliminarEspecialidad = new ImageButtonLabel((ImageIcon) null);
        botonEliminarEspecialidad.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                List<String> seleccionados = listaEspecialidades.getSelectedValuesList();
                if (!seleccionados.isEmpty()) {
                    QuestionDialog confirmacion = new QuestionDialog(FormularioVisitas.this,
                            "Confirmar eliminaci\u00F3n",
                            "¿Est\u00E1 seguro que desea eliminar las especialidades seleccionadas?");
                    confirmacion.setVisible(true);
                    if (confirmacion.esConfirmado()) {
                        for (String sel : seleccionados) {
                            modeloEspecialidades.removeElement(sel);
                        }
                    }
                }
            }
        });
        botonEliminarEspecialidad.setIcon(new ImageIcon(FormularioVisitas.class.getResource("/fotos/trash-22x22.png")));
        botonEliminarEspecialidad.setToolTipText("Clic para borrar los elemento(s) selecionado(s) de la lista");
        botonEliminarEspecialidad.setBounds(640, 152, 22, 22);
        panelRegistroVisita.add(botonEliminarEspecialidad);

        JLabel cartelAnalisis = new JLabel("An\u00E1lisis orientados:");
        cartelAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelAnalisis.setBounds(24, 152, 176, 22);
        panelRegistroVisita.add(cartelAnalisis);

        modeloAnalisis = new DefaultListModel<>();
        listaAnalisis = new JList<>(modeloAnalisis);
        listaAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        ScrollPaneModerno analisisScrollPane = new ScrollPaneModerno(listaAnalisis);
        analisisScrollPane.setBounds(24, 179, 300, 95);
        panelRegistroVisita.add(analisisScrollPane);

        botonAgregarAnalisis = new ImageButtonLabel((ImageIcon) null);
        botonAgregarAnalisis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SelectorDialog dialog = new SelectorDialog(FormularioVisitas.this, "Seleccionar an\u00E1lisis",
                        "Seleccione un an\u00E1lisis:", ConstantesAnalisis.TIPOS_ANALISIS);
                dialog.setVisible(true);
                if (dialog.esConfirmado()) {
                    String seleccion = dialog.getSeleccionado();
                    if (!modeloAnalisis.contains(seleccion)) {
                        modeloAnalisis.addElement(seleccion);
                    } else {
                        new InfoDialog(FormularioVisitas.this, "Informaci\u00F3n", "El an\u00E1lisis ya fue a\u00F1adido.")
                                .setVisible(true);
                    }
                }
            }
        });
        botonAgregarAnalisis.setIcon(new ImageIcon(FormularioVisitas.class.getResource("/fotos/agregar-22x22.png")));
        botonAgregarAnalisis.setToolTipText("Clic para agregar nuevo elemento a la lista");
        botonAgregarAnalisis.setBounds(278, 153, 22, 22);
        panelRegistroVisita.add(botonAgregarAnalisis);

        botonEliminarAnalisis = new ImageButtonLabel((ImageIcon) null);
        botonEliminarAnalisis.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                List<String> seleccionados = listaAnalisis.getSelectedValuesList();
                if (!seleccionados.isEmpty()) {
                    QuestionDialog confirmacion = new QuestionDialog(FormularioVisitas.this,
                            "Confirmar eliminaci\u00F3n",
                            "¿Est\u00E1 seguro que desea eliminar los an\u00E1lisis seleccionados?");
                    confirmacion.setVisible(true);
                    if (confirmacion.esConfirmado()) {
                        for (String sel : seleccionados) {
                            modeloAnalisis.removeElement(sel);
                        }
                    }
                }
            }
        });
        botonEliminarAnalisis.setIcon(new ImageIcon(FormularioVisitas.class.getResource("/fotos/trash-22x22.png")));
        botonEliminarAnalisis.setToolTipText("Clic para borrar los elemento(s) selecionado(s) de la lista");
        botonEliminarAnalisis.setBounds(302, 153, 22, 22);
        panelRegistroVisita.add(botonEliminarAnalisis);

        JLabel cartelEspecialidades = new JLabel("Especialidades remitidas");
        cartelEspecialidades.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelEspecialidades.setBounds(364, 152, 193, 22);
        panelRegistroVisita.add(cartelEspecialidades);

        modeloEspecialidades = new DefaultListModel<>();
        listaEspecialidades = new JList<>(modeloEspecialidades);
        listaEspecialidades.setFont(new Font("Arial", Font.PLAIN, 16));
        ScrollPaneModerno especialidadesScrollPane = new ScrollPaneModerno(listaEspecialidades);
        especialidadesScrollPane.setBounds(363, 179, 300, 95);
        panelRegistroVisita.add(especialidadesScrollPane);

        JLabel label = new JLabel("Informaci\u00F3n personal");
        label.setBounds(45, 25, 232, 26);
        getContentPane().add(label);
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBorder(BORDE_PEQUENO);
        label.setBackground(Color.WHITE);

        JPanel panelPersonal = new JPanel();
        panelPersonal.setBackground(Color.WHITE);
        panelPersonal.setBorder(BORDE_GRANDE);
        panelPersonal.setBounds(25, 40, 700, 150);
        getContentPane().add(panelPersonal);
        panelPersonal.setLayout(null);

        JLabel cartelPaciente = new JLabel("Paciente: ");
        cartelPaciente.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelPaciente.setBounds(30, 30, 76, 19);
        panelPersonal.add(cartelPaciente);

        barraBuscarPacienteCI = new PlaceholderTextField();
        barraBuscarPacienteCI.setInputFormat(InputFormat.NUMERIC);
        barraBuscarPacienteCI.setCharacterLimit(11);
        barraBuscarPacienteCI.setBounds(104, 24, 142, 25);
        barraBuscarPacienteCI.setFont(new Font("Arial", Font.PLAIN, 16));
        panelPersonal.add(barraBuscarPacienteCI);
        barraBuscarPacienteCI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (popupResultados.isVisible()) {
                    int index = listaResultados.getSelectedIndex();
                    int size = modeloResultados.size();

                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_DOWN:
                            if (index < size - 1) {
                                listaResultados.setSelectedIndex(index + 1);
                                listaResultados.ensureIndexIsVisible(index + 1);
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (index > 0) {
                                listaResultados.setSelectedIndex(index - 1);
                                listaResultados.ensureIndexIsVisible(index - 1);
                            }
                            break;
                        case KeyEvent.VK_ENTER:
                            if (!listaResultados.isSelectionEmpty()) {
                                String seleccionado = listaResultados.getSelectedValue();
                                manejarSeleccionPaciente(seleccionado);
                                e.consume();
                            }
                            break;
                    }
                }
            }
        });

        cartelHistoriaClinica = new JLabel("Historia Clínica #");
        cartelHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelHistoriaClinica.setBounds(266, 27, 191, 19);
        panelPersonal.add(cartelHistoriaClinica);

        JLabel cartelFecha = new JLabel("Fecha:");
        cartelFecha.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelFecha.setBounds(469, 27, 59, 19);
        panelPersonal.add(cartelFecha);

        campoFecha = new PlaceholderTextField();
        campoFecha.setEditable(false);
        campoFecha.setInputFormat(InputFormat.ANY);
        campoFecha.setFont(new Font("Arial", Font.PLAIN, 16));
        campoFecha.setCharacterLimit(11);
        campoFecha.setBounds(521, 24, 149, 25);
        panelPersonal.add(campoFecha);

        campoDireccion = new PlaceholderTextField();
        campoDireccion.setBounds(104, 100, 566, 25);
        panelPersonal.add(campoDireccion);

        campoNombre = new PlaceholderTextField("Seleccione un CI para mostrar");
        campoNombre.setEditable(false);
        campoNombre.setBounds(104, 62, 566, 25);
        panelPersonal.add(campoNombre);

        JLabel cartelNombre = new JLabel("Nombre:");
        cartelNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelNombre.setBounds(30, 67, 76, 19);
        panelPersonal.add(cartelNombre);

        JLabel cartelDireccion = new JLabel("Direcci\u00F3n:");
        cartelDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelDireccion.setBounds(30, 105, 76, 19);
        panelPersonal.add(cartelDireccion);

        // JList para mostrar resultados en popup
        listaResultados = new JList<>(modeloResultados);
        listaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaResultados.setFont(new Font("Arial", Font.PLAIN, 16));
        listaResultados.setVisibleRowCount(5);
        listaResultados.setFocusable(false);
        listaResultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!listaResultados.isSelectionEmpty()) {
                    String seleccionado = listaResultados.getSelectedValue();
                    manejarSeleccionPaciente(seleccionado);
                }
            }
        });

        ScrollPaneModerno scrollPopup = new ScrollPaneModerno(listaResultados);
        scrollPopup.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPopup.setPreferredSize(new Dimension(barraBuscarPacienteCI.getWidth(), 100));
        scrollPopup.setBorder(null);
        popupResultados.add(scrollPopup);

        barraBuscarPacienteCI.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarResultados();
                limpiarDatosSiNoCoincide();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarResultados();
                limpiarDatosSiNoCoincide();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarResultados();
                limpiarDatosSiNoCoincide();
            }
        });

        JPanel panelVerde = new JPanel();
        panelVerde.setBounds(0, 0, 792, 112);
        panelVerde.setBackground(COLOR_VERDE);
        getContentPane().add(panelVerde);
        panelVerde.setLayout(null);

        JPanel panelAzul = new JPanel();
        panelAzul.setBackground(COLOR_AZUL);
        panelAzul.setBounds(0, 451, 778, 165);
        getContentPane().add(panelAzul);
        panelAzul.setLayout(null);

        botonGuardar = new BotonBlanco("GUARDAR");
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modoActual == ModoFormulario.CREACION) {
                    agregarNuevaVisita();
                } else if (modoActual == ModoFormulario.EDICION) {
                    guardarEdicionVisita(visita);
                }
            }
        });

        botonGuardar.setToolTipText("Clic para guardar los datos actuales");
        botonGuardar.setFont(new Font("Arial", Font.PLAIN, 18));
        botonGuardar.setBounds(221, 99, 130, 30);
        panelAzul.add(botonGuardar);

        botonEditar = new BotonBlanco("EDITAR");
        botonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setModoActual(ModoFormulario.EDICION);
            }
        });
        botonEditar.setToolTipText("Clic para editar la visita");
        botonEditar.setFont(new Font("Arial", Font.PLAIN, 18));
        botonEditar.setBounds(221, 99, 130, 30);
        botonEditar.setVisible(false);
        panelAzul.add(botonEditar);

        botonAtras = new BotonBlanco("ATR\u00C1S");
        botonAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modoActual == ModoFormulario.CREACION) {
                    QuestionDialog confirmacion = new QuestionDialog(FormularioVisitas.this,
                            "Confirmar salida",
                            "Est\u00E1 a punto de salir del formulario.\nSe perder\u00E1n los cambios no guardados.\n¿Desea continuar?");
                    confirmacion.setVisible(true);
                    if (confirmacion.esConfirmado()) {
                        dispose();
                    }
                } else if (modoActual == ModoFormulario.EDICION) {
                    QuestionDialog confirmacion = new QuestionDialog(FormularioVisitas.this,
                            "Cancelar edici\u00F3n",
                            "Est\u00E1 a punto de cancelar la edici\u00F3n.\nSe perder\u00E1n los cambios no guardados.\n¿Desea continuar?");
                    confirmacion.setVisible(true);
                    if (confirmacion.esConfirmado()) {
                        setModoActual(ModoFormulario.VISUALIZACION);
                        cargarDatosVisita();
                        popupResultados.setVisible(false);
                    }
                } else {
                    dispose();
                }
            }
        });
        botonAtras.setToolTipText("Clic para volver a la ventana principal");
        botonAtras.setFont(new Font("Arial", Font.PLAIN, 18));
        botonAtras.setBounds(388, 99, 130, 30);
        panelAzul.add(botonAtras);

        JPanel panelGris = new JPanel();
        panelGris.setBackground(COLOR_GRIS_CLARO);
        panelGris.setBounds(0, 120, 816, 322);
        getContentPane().add(panelGris);
        panelGris.setLayout(null);
    }

    private boolean esMedico() {
        boolean resultado = false;
        if (usuarioActual != null && "M\u00C9DICO".equalsIgnoreCase(usuarioActual.getRole().toString())) {
            resultado = true;
        }
        return resultado;
    }

    private List<Analisis> construirListaAnalisis(DefaultListModel<String> modeloAnalisis, int idVisita,
            int historiaClinicaId) {
        List<Analisis> lista = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (int i = 0; i < modeloAnalisis.size(); i++) {
            String tipo = modeloAnalisis.getElementAt(i);
            try {
                int idAnalisis = cmf.obtenerNuevoAnalisisID();
                Analisis a = new Analisis(idAnalisis, tipo, hoy, idVisita, historiaClinicaId);
                lista.add(a);
            } catch (IllegalArgumentException ex) {

            }
        }
        return lista;
    }

    private List<String> construirListaEspecialidades() {
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < modeloEspecialidades.size(); i++) {
            String esp = modeloEspecialidades.getElementAt(i);
            if (esp != null && !esp.trim().isEmpty()
                    && ConstantesEspecialidades.ESPECIALIDADES_REMITIDAS.contains(esp.trim())) {
                lista.add(esp.trim());
            }
        }
        return lista;
    }

    // M\u00E9todo para agregar una nueva visita
    public void agregarNuevaVisita() {
        boolean accesoPermitido = esMedico();
        if (!accesoPermitido) {
            new InfoDialog(this, "Acceso denegado", "Solo el m\u00E9dico puede agregar visitas.").setVisible(true);
        } else {
            try {
                String ci = barraBuscarPacienteCI.getText().trim();
                if (ci.isEmpty())
                    throw new IllegalArgumentException("Debe ingresar el CI del paciente.");

                Paciente paciente = cmf.getPacientePorCI(ci);
                if (paciente == null)
                    throw new IllegalArgumentException("Debe seleccionar un paciente v\u00E1lido.");

                int historiaClinicaID = paciente.getHistoriaClinica().getId();
                int id = cmf.obtenerNuevoVisitaID();
                LocalDate fecha = LocalDate.now();

                String diagnostico = textDiagnostico.getText().trim();
                String tratamiento = textTratamiento.getText().trim();
                String direccion = campoDireccion.getText().trim();

                if (diagnostico.isEmpty() || tratamiento.isEmpty() || direccion.isEmpty()) {
                    throw new IllegalArgumentException("Diagn\u00F3stico, tratamiento y direcci\u00F3n no pueden estar vac\u00ED­os.");
                }

                List<Analisis> listaAnalisis = construirListaAnalisis(modeloAnalisis, id, historiaClinicaID);
                List<String> listaEspecialidades = construirListaEspecialidades();

                Visita nuevaVisita = new Visita(id, historiaClinicaID, fecha, diagnostico, tratamiento,
                        listaAnalisis, listaEspecialidades, direccion);

                cmf.agregarVisita(nuevaVisita);
                this.visita = nuevaVisita;

                new InfoDialog(this, "Visita Agregada", "Visita guardada exitosamente.").setVisible(true);
                mostrarVisitaGuardada();
            } catch (Exception ex) {
                new InfoDialog(this, "Error", ex.getMessage()).setVisible(true);
            }
        }
    }

    // M\u00E9todo para guardar edici\u00F3n de visita existente
    public void guardarEdicionVisita(Visita visitaExistente) {
        boolean accesoPermitido = esMedico();
        if (!accesoPermitido) {
            new InfoDialog(this, "Acceso denegado", "Solo el m\u00E9dico puede guardar cambios.").setVisible(true);
        } else {
            try {
                boolean visitaValida = (visitaExistente != null);
                if (!visitaValida) {
                    throw new IllegalArgumentException("No hay visita existente para editar.");
                }

                String textoId = cartelHistoriaClinica.getText();
                int historiaClinicaID = -1;

                if (textoId.length() > 18) {
                    String idStr = textoId.substring(18).trim();
                    historiaClinicaID = Integer.parseInt(idStr);
                }

                LocalDate fecha = visitaExistente.getFecha();

                String diagnostico = textDiagnostico.getText().trim();
                String tratamiento = textTratamiento.getText().trim();
                String direccion = campoDireccion.getText().trim();

                boolean camposValidos = !(diagnostico.isEmpty() || tratamiento.isEmpty() || direccion.isEmpty());
                if (!camposValidos) {
                    throw new IllegalArgumentException("Diagn\u00F3stico, tratamiento y direcci\u00F3n no pueden estar vac\u00ED­os.");
                }

                List<Analisis> listaAnalisis = construirListaAnalisis(modeloAnalisis, visitaExistente.getId(),
                        historiaClinicaID);
                List<String> listaEspecialidades = construirListaEspecialidades();

                boolean editada = cmf.editarVisita(
                        visitaExistente.getId(), fecha,
                        diagnostico, tratamiento, listaAnalisis, listaEspecialidades, direccion);

                if (!editada) {
                    throw new IllegalStateException("No se pudo editar la visita.");
                }

                new InfoDialog(this, "Visita Guardada", "Visita editada exitosamente.").setVisible(true);
                mostrarVisitaGuardada();
            } catch (NumberFormatException ex) {
                new InfoDialog(this, "Error", "El campo 'H. Clínica' debe contener un n\u00FAmero v\u00E1lido.").setVisible(true);
            } catch (IllegalArgumentException ex) {
                new InfoDialog(this, "Error", ex.getMessage()).setVisible(true);
            } catch (Exception ex) {
                new InfoDialog(this, "Error inesperado", "Error al guardar la visita: " + ex.getMessage())
                        .setVisible(true);
            }
        }
    }

    private void manejarSeleccionPaciente(String ciSeleccionado) {
        barraBuscarPacienteCI.setText(ciSeleccionado);

        Paciente pacienteSeleccionado = cmf.getPacientePorCI(ciSeleccionado);

        if (pacienteSeleccionado != null) {
            cartelHistoriaClinica.setText("Historia Clínica #" + pacienteSeleccionado.getHistoriaClinica().getId());
            campoNombre.setText(pacienteSeleccionado.getNombreYApellidos());
        }

        popupResultados.setVisible(false);
    }

    private void actualizarResultados() {
        boolean esModoCreacion = modoActual == ModoFormulario.CREACION;

        if (!esModoCreacion) {
            popupResultados.setVisible(false);
        } else {
            String texto = barraBuscarPacienteCI.getText().trim();
            modeloResultados.clear();

            if (!texto.isEmpty()) {
                for (Paciente paciente : cmf.getPacientes()) {
                    if (paciente.getCI().contains(texto)) {
                        modeloResultados.addElement(paciente.getCI());
                    }
                }
            }

            if (!modeloResultados.isEmpty()) {
                listaResultados.setSelectedIndex(0);

                if (!popupResultados.isVisible()) {
                    popupResultados.show(barraBuscarPacienteCI, 0, barraBuscarPacienteCI.getHeight());
                }
            } else {
                popupResultados.setVisible(false);
            }
        }
    }

    public ModoFormulario getModoActual() {
        return modoActual;
    }

    public void setModoActual(ModoFormulario modo) {
        this.modoActual = modo;
        switch (modo) {
            case CREACION:
                setTitle("Crear visita");
                activarEdicion(esMedico());
                botonGuardar.setVisible(esMedico());
                botonEditar.setVisible(false);
                botonAtras.setText("ATR\u00C1S");
                break;
            case EDICION:
                setTitle("Editar visita");
                activarEdicion(esMedico());
                botonGuardar.setVisible(esMedico());
                botonEditar.setVisible(false);
                botonAtras.setText("CANCELAR");
                break;
            case VISUALIZACION:
                setTitle("Informaci\u00F3n de la visita");
                activarEdicion(false);
                botonGuardar.setVisible(false);
                botonEditar.setVisible(esMedico());
                botonAtras.setText("ATR\u00C1S");
                break;
        }
        popupResultados.setVisible(false);
    }

    public void activarEdicion(boolean activo) {
        boolean editar = activo;
        if (!esMedico()) {
            editar = false;
        }
        boolean esCreacion = (modoActual == ModoFormulario.CREACION);
        barraBuscarPacienteCI.setEditable(esCreacion && esMedico());
        campoDireccion.setEditable(editar);
        textDiagnostico.setEditable(editar);
        textTratamiento.setEditable(editar);

        boolean mostrarBotones = editar && (modoActual != ModoFormulario.VISUALIZACION);
        botonAgregarEspecialidad.setVisible(mostrarBotones);
        botonEliminarEspecialidad.setVisible(mostrarBotones);
        botonAgregarAnalisis.setVisible(mostrarBotones);
        botonEliminarAnalisis.setVisible(mostrarBotones);

        if (modoActual == ModoFormulario.CREACION) {
            botonGuardar.setText("AGREGAR");
        } else {
            botonGuardar.setText("GUARDAR");
        }
    }

    public void cargarDatosVisita() {
        boolean hayVisita = visita != null;

        if (!hayVisita) {
            cargarDatosPaciente(null);
            campoFecha.setText("");
            campoDireccion.setText("");
            textDiagnostico.setText("");
            textTratamiento.setText("");
            modeloAnalisis.clear();
            modeloEspecialidades.clear();
        } else {
            Paciente pacienteEncontrado = cmf.getPacientePorId(visita.getPacienteHistoriaClinicaID());
            cargarDatosPaciente(pacienteEncontrado);

            campoFecha.setText(visita.getFecha().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")));
            campoDireccion.setText(visita.getDireccion());
            textDiagnostico.setText(visita.getDiagnostico());
            textTratamiento.setText(visita.getTratamiento());

            modeloAnalisis.clear();
            List<Analisis> listaAnalisis = visita.getAnalisis();
            if (listaAnalisis != null) {
                for (Analisis a : listaAnalisis) {
                    modeloAnalisis.addElement(a.getTipoDeAnalisis());
                }
            }

            modeloEspecialidades.clear();
            List<String> listaEsp = visita.getEspecialidadesRemitidas();
            if (listaEsp != null) {
                for (String esp : listaEsp) {
                    modeloEspecialidades.addElement(esp);
                }
            }
        }
    }

    private void cargarDatosPaciente(Paciente paciente) {
        if (paciente != null) {
            campoNombre.setText(paciente.getNombreYApellidos());
            barraBuscarPacienteCI.setText(paciente.getCI());
            cartelHistoriaClinica.setText("Historia Clínica #" + paciente.getHistoriaClinica().getId());
        } else {
            campoNombre.setText("Paciente no encontrado");
            barraBuscarPacienteCI.setText("");
            cartelHistoriaClinica.setText("Historia Clínica #");
        }
    }

    private void mostrarVisitaGuardada() {
        setModoActual(ModoFormulario.VISUALIZACION);
        cargarDatosVisita();
        popupResultados.setVisible(false);
    }

    private void limpiarDatosSiNoCoincide() {
        boolean enModoCreacion = (modoActual == ModoFormulario.CREACION);
        boolean tieneFoco = barraBuscarPacienteCI.hasFocus();

        if (enModoCreacion && tieneFoco) {
            Paciente pac = cmf.getPacientePorCI(barraBuscarPacienteCI.getText().trim());
            if (pac == null) {
                campoNombre.setText("");
                cartelHistoriaClinica.setText("Historia Clínica #");
            }
        }
    }

    @Override
    public void dispose() {
        popupResultados.setVisible(false);
        super.dispose();
    }
}
