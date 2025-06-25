package frontend.formularios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import util.ConstantesFrontend;
import entidades.CMF;
import entidades.registros.Analisis;
import frontend.ui.PlaceholderTextField;
import frontend.ui.PlaceholderTextField.InputFormat;
import frontend.ui.ScrollPaneModerno;
import frontend.ui.botones.BotonBlanco;
import frontend.ui.dialogs.InfoDialog;
import frontend.ui.dialogs.QuestionDialog;

import java.time.format.DateTimeFormatter;

public class FormularioAnalisis extends JDialog implements ConstantesFrontend {

    private PlaceholderTextField campoFechaOrientado;
    private PlaceholderTextField campoNombre;
    private PlaceholderTextField campoTipoAnalisis;
    private PlaceholderTextField campoEstado;
    private PlaceholderTextField campoFechaActualizacion;

    private JTextArea textResultados;
    private JLabel cartelHistoriaClinica;

    private BotonBlanco botonGuardar;
    private BotonBlanco botonEditar;
    private BotonBlanco botonCancelar;

    private Analisis analisis;
    private CMF cmf;

    private ModoFormulario modoActual;

    public enum ModoFormulario {
        VISUALIZACION,
        EDICION
    }

    public FormularioAnalisis(Window owner, Analisis analisis, ModoFormulario modo) {
        super(owner, "Formulario de Análisis", ModalityType.APPLICATION_MODAL);
        setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioAnalisis.class.getResource("/fotos/Logo peque.png")));
        this.analisis = analisis;
        this.modoActual = modo;
        this.cmf = CMF.getInstance();

        setSize(645, 600);
        setResizable(false);
        setLocationRelativeTo(owner);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (modoActual == ModoFormulario.EDICION) {
                    QuestionDialog confirmacion = new QuestionDialog(FormularioAnalisis.this,
                            "Cancelar edición", "¿Está seguro de que desea cerrar el formulario? Se perderán los cambios.");
                    confirmacion.setVisible(true);

                    if (confirmacion.esConfirmado()) {
                        dispose();
                    }
                } else {
                    dispose();
                }
            }
        });
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);

        initComponents();
        cargarDatos();
        configurarModo();
    }

    private void initComponents() {
        JLabel cartelResultados = new JLabel("Resultados");
        cartelResultados.setOpaque(true);
        cartelResultados.setHorizontalAlignment(SwingConstants.CENTER);
        cartelResultados.setFont(new Font("Arial", Font.BOLD, 18));
        cartelResultados.setBorder(BORDE_PEQUENO);
        cartelResultados.setBackground(Color.WHITE);
        cartelResultados.setBounds(45, 210, 167, 26);
        getContentPane().add(cartelResultados);

        JPanel panelResultados = new JPanel();
        panelResultados.setBounds(25, 225, 593, 271);
        panelResultados.setLayout(null);
        panelResultados.setBorder(BORDE_GRANDE);
        panelResultados.setBackground(Color.WHITE);
        getContentPane().add(panelResultados); 

        textResultados = new JTextArea();
        textResultados.setLineWrap(true);
        textResultados.setWrapStyleWord(true);
        textResultados.setBorder(null);
        textResultados.setFont(new Font("Arial", Font.PLAIN, 16));
        ScrollPaneModerno resultadosScroll = new ScrollPaneModerno(textResultados);
        resultadosScroll.setBounds(24, 45, 537, 197);
        resultadosScroll.setViewportView(textResultados);
        panelResultados.add(resultadosScroll);
       
        JLabel cartelFechaActualizacion = new JLabel("Fecha de actualizado los resultados:");
        cartelFechaActualizacion.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelFechaActualizacion.setBounds(201, 16, 253, 19);
        panelResultados.add(cartelFechaActualizacion);

        campoFechaActualizacion = new PlaceholderTextField();
        campoFechaActualizacion.setInputFormat(InputFormat.ANY);
        campoFechaActualizacion.setFont(new Font("Arial", Font.PLAIN, 16));
        campoFechaActualizacion.setEditable(false);
        campoFechaActualizacion.setCharacterLimit(11);
        campoFechaActualizacion.setBounds(463, 13, 98, 25);
        panelResultados.add(campoFechaActualizacion);

        JLabel cartelInformaciónGeneral = new JLabel("Información general");
        cartelInformaciónGeneral.setBounds(45, 25, 232, 26);
        cartelInformaciónGeneral.setOpaque(true);
        cartelInformaciónGeneral.setHorizontalAlignment(SwingConstants.CENTER);
        cartelInformaciónGeneral.setFont(new Font("Arial", Font.BOLD, 18));
        cartelInformaciónGeneral.setBorder(BORDE_PEQUENO);
        cartelInformaciónGeneral.setBackground(Color.WHITE);
        getContentPane().add(cartelInformaciónGeneral);

        JPanel panelGeneral = new JPanel();
        panelGeneral.setBackground(Color.WHITE);
        panelGeneral.setBorder(BORDE_GRANDE);
        panelGeneral.setBounds(25, 40, 593, 150);
        panelGeneral.setLayout(null);
        getContentPane().add(panelGeneral);

        JLabel cartelPaciente = new JLabel("Paciente:");
        cartelPaciente.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelPaciente.setBounds(30, 30, 76, 19);
        panelGeneral.add(cartelPaciente);

        campoNombre = new PlaceholderTextField();
        campoNombre.setEditable(false);
        campoNombre.setBounds(104, 27, 460, 25);
        panelGeneral.add(campoNombre);

        cartelHistoriaClinica = new JLabel("Historia Clínica #");
        cartelHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelHistoriaClinica.setBounds(30, 65, 191, 19);
        panelGeneral.add(cartelHistoriaClinica);

        JLabel cartelTipoAnalisis = new JLabel("Tipo de análisis:");
        cartelTipoAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelTipoAnalisis.setBounds(223, 65, 116, 19);
        panelGeneral.add(cartelTipoAnalisis);

        campoTipoAnalisis = new PlaceholderTextField();
        campoTipoAnalisis.setInputFormat(InputFormat.ANY);
        campoTipoAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        campoTipoAnalisis.setEditable(false);
        campoTipoAnalisis.setCharacterLimit(20);
        campoTipoAnalisis.setBounds(351, 62, 213, 25);
        panelGeneral.add(campoTipoAnalisis);

        JLabel cartelFechaOrientado = new JLabel("Fecha de orientado:");
        cartelFechaOrientado.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelFechaOrientado.setBounds(30, 100, 146, 19);
        panelGeneral.add(cartelFechaOrientado);

        campoFechaOrientado = new PlaceholderTextField();
        campoFechaOrientado.setEditable(false);
        campoFechaOrientado.setInputFormat(InputFormat.ANY);
        campoFechaOrientado.setFont(new Font("Arial", Font.PLAIN, 16));
        campoFechaOrientado.setCharacterLimit(11);
        campoFechaOrientado.setBounds(175, 97, 95, 25);
        panelGeneral.add(campoFechaOrientado);

        JLabel cartelEstado = new JLabel("Estado:");
        cartelEstado.setFont(new Font("Arial", Font.PLAIN, 16));
        cartelEstado.setBounds(282, 100, 63, 19);
        panelGeneral.add(cartelEstado);

        campoEstado = new PlaceholderTextField();
        campoEstado.setInputFormat(InputFormat.ALPHABETIC);
        campoEstado.setFont(new Font("Arial", Font.PLAIN, 16));
        campoEstado.setEditable(false);
        campoEstado.setCharacterLimit(20);
        campoEstado.setBounds(351, 94, 213, 25);
        panelGeneral.add(campoEstado);

        JPanel panelAzul = new JPanel();
        panelAzul.setBackground(COLOR_AZUL);
        panelAzul.setBounds(0, 400, 645, 182);
        panelAzul.setLayout(null);
        getContentPane().add(panelAzul);
        
        JPanel panelVerde = new JPanel();
        panelVerde.setBackground(COLOR_VERDE);
        panelVerde.setBounds(0, 0, 645, 110);
        panelVerde.setLayout(null);
        getContentPane().add(panelVerde);

        botonGuardar = new BotonBlanco("GUARDAR");
        botonGuardar.setFont(new Font("Arial", Font.PLAIN, 18));
        botonGuardar.setBounds(165, 111, 130, 30);
        botonGuardar.setToolTipText("Guardar resultados del análisis");
        panelAzul.add(botonGuardar);
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarResultados();
            }
        });

        botonEditar = new BotonBlanco("EDITAR");
        botonEditar.setFont(new Font("Arial", Font.PLAIN, 18));
        botonEditar.setBounds(245, 111, 130, 30);
        botonEditar.setToolTipText("Editar resultados del análisis");
        panelAzul.add(botonEditar);
        botonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QuestionDialog confirmacion = new QuestionDialog(FormularioAnalisis.this,
                        "Confirmar edición", "¿Desea habilitar la edición de los resultados?");
                confirmacion.setVisible(true);

                if (confirmacion.esConfirmado()) {
                    setModo(ModoFormulario.EDICION);

                    InfoDialog info = new InfoDialog(FormularioAnalisis.this, "Modo edición",
                            "Ahora puede editar el campo de resultados.\nHaga clic en GUARDAR para confirmar los cambios.");
                    info.setVisible(true);
                }
            }
        });

        botonCancelar = new BotonBlanco("CANCELAR");
        botonCancelar.setFont(new Font("Arial", Font.PLAIN, 18));
        botonCancelar.setBounds(335, 111, 130, 30);
        panelAzul.add(botonCancelar);
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (modoActual == ModoFormulario.EDICION) {
                    QuestionDialog confirmacion = new QuestionDialog(FormularioAnalisis.this,
                            "Cancelar edición", "¿Está seguro de que desea cancelar los cambios?");
                    confirmacion.setVisible(true);

                    if (confirmacion.esConfirmado()) {
                        setModo(ModoFormulario.VISUALIZACION);
                        cargarDatos();
                    }
                } else {
                    dispose();
                }
            }
        });
        
        JPanel panelGris = new JPanel();
        panelGris.setLayout(null);
        panelGris.setBackground(SystemColor.menu);
        panelGris.setBounds(0, 120, 645, 271);
        getContentPane().add(panelGris);
        
    }

    private void cargarDatos() {
        campoNombre.setText(cmf.getPacientePorId(analisis.getHistoriaClinicaId()).getNombreYApellidos());
        campoFechaOrientado.setText(analisis.getFechaOrientado() != null ? analisis.getFechaOrientado().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")) : "");
        campoTipoAnalisis.setText(analisis.getTipoDeAnalisis());
        campoEstado.setText(analisis.getFechaResultado() != null ? "Completado" : "Pendiente");
        campoFechaActualizacion.setText(analisis.getFechaResultado() != null ? analisis.getFechaResultado().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")) : "");
        textResultados.setText(analisis.getResultados() != null ? analisis.getResultados() : "");
        cartelHistoriaClinica.setText("Historia Clínica #" + analisis.getHistoriaClinicaId());
    }

    private void configurarModo() {
        boolean esEdicion = modoActual == ModoFormulario.EDICION;

        textResultados.setEditable(esEdicion);
        botonGuardar.setVisible(esEdicion);
        botonCancelar.setVisible(esEdicion);

        if (!esEdicion) {
            String rol = cmf.getUsuario().getRole().toString();
            botonEditar.setVisible("ENFERMERA".equalsIgnoreCase(rol));
        } else {
            botonEditar.setVisible(false);
        }

        textResultados.setBackground(esEdicion ? Color.WHITE : UIManager.getColor("TextArea.disabledBackground"));
    }

    private void setModo(ModoFormulario nuevoModo) {
        if (modoActual != nuevoModo) {
            if (modoActual == ModoFormulario.EDICION && nuevoModo == ModoFormulario.VISUALIZACION) {
                textResultados.setText(analisis.getResultados() != null ? analisis.getResultados() : "");
            }
            modoActual = nuevoModo;
            configurarModo();
        }
    }

    private void guardarResultados() {
        String texto = textResultados.getText().trim();
        boolean puedeGuardar = true;
        boolean hayCambios = false;

        if (texto.isEmpty()) {
            InfoDialog errorDialog = new InfoDialog(this, "Error", "El campo de resultados no puede estar vacío.");
            errorDialog.setVisible(true);
            puedeGuardar = false;
        }

        String anteriores = analisis.getResultados() != null ? analisis.getResultados().trim() : "";
        if (texto.equals(anteriores)) {
            InfoDialog sinCambiosDialog = new InfoDialog(this, "Sin cambios", "No se han realizado modificaciones en los resultados.");
            sinCambiosDialog.setVisible(true);
            puedeGuardar = false;
        } else {
            hayCambios = true;
        }

        if (puedeGuardar && hayCambios) {
            QuestionDialog confirmacion = new QuestionDialog(this, "Confirmar guardado", "¿Está seguro de que desea guardar los resultados?");
            confirmacion.setVisible(true);

            if (confirmacion.esConfirmado()) {
                cmf.editarResultadosDeAnalisis(analisis, texto);

                InfoDialog exitoDialog = new InfoDialog(this, "Resultados guardados", "Los resultados se han guardado exitosamente.");
                exitoDialog.setVisible(true);
            }
        }

        setModo(ModoFormulario.VISUALIZACION);
        cargarDatos();
    }
}
