package frontend.formularios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entidades.CMF;
import entidades.personal.Paciente;
import entidades.registros.Analisis;
import entidades.registros.Visita;

public class FormularioVisitas extends JDialog {
    private JTextField barraBusqueda; // Barra de búsqueda para IDs de pacientes
    private JList<String> listaResultados; // Lista para mostrar resultados de búsqueda
    private DefaultListModel<String> modeloResultados;
    private JTextField txtHistoriaClinica; // Campo no editable para mostrar el ID seleccionado
    private JTextField txtDiagnostico;
    private JTextField txtTratamiento;
    private JComboBox<String> cbEspecialidadRemitida; // Lista desplegable para especialidades
    private JTextField txtDireccion;
    private JComboBox<String> cbTipoAnalisis;
    private JButton btnGuardar;
    private JButton btnCancelar;
    CMF cmf;

    private Visita visita; // Variable de instancia para almacenar la visita

    private JList<String> listaAnalisis; // Lista para mostrar análisis agregados
    private DefaultListModel<String> modeloAnalisis; // Modelo para gestionar los análisis
    private JButton btnAgregarAnalisis; // Botón para agregar análisis

    public FormularioVisitas(Window owner, Visita visita) {
        super(owner, "Formulario de Visitas", ModalityType.APPLICATION_MODAL);
        this.visita = visita; // Asignar la visita pasada al constructor
        setLayout(null);
        setSize(600, 650); // Ajustar altura de la ventana
        setResizable(false);
        setLocationRelativeTo(owner);

        cmf = CMF.getInstance();

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(0, 0, 600, 650);
        panelPrincipal.setLayout(null);
        add(panelPrincipal);

        JLabel tituloFormulario = new JLabel("Formulario de Visitas");
        tituloFormulario.setFont(new Font("Arial Black", Font.BOLD, 24));
        tituloFormulario.setHorizontalAlignment(SwingConstants.CENTER);
        tituloFormulario.setBounds(0, 20, 600, 40);
        panelPrincipal.add(tituloFormulario);

        JLabel lblBusqueda = new JLabel("Buscar Paciente ID:");
        lblBusqueda.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBusqueda.setBounds(50, 70, 150, 30); // Ajustar posición
        panelPrincipal.add(lblBusqueda);

        barraBusqueda = new JTextField();
        barraBusqueda.setFont(new Font("Arial", Font.PLAIN, 16));
        barraBusqueda.setBounds(200, 70, 350, 30); // Ajustar posición
        panelPrincipal.add(barraBusqueda);

        modeloResultados = new DefaultListModel<>();
        listaResultados = new JList<>(modeloResultados);
        listaResultados.setFont(new Font("Arial", Font.PLAIN, 14));
        listaResultados.setBounds(200, 110, 350, 100);
        listaResultados.setVisible(false); // Ocultar inicialmente
        panelPrincipal.add(listaResultados);

        barraBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarResultados();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarResultados();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarResultados();
            }
        });

        // Ajustar la lógica de selección en la barra de búsqueda
        listaResultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String seleccionado = listaResultados.getSelectedValue();
                    Paciente pacienteSeleccionado = null;

                    for (Paciente paciente : cmf.getPacientes()) {
                        if (paciente.getCI().equals(seleccionado)) {
                            pacienteSeleccionado = paciente;
                            break;
                        }
                    }

                    if (pacienteSeleccionado != null) {
                        txtHistoriaClinica.setText(String.valueOf(pacienteSeleccionado.getHistoriaClinica().getId()));
                    }

                    listaResultados.setVisible(false); // Ocultar lista tras selección
                }
            }
        });

        JLabel lblHistoriaClinica = new JLabel("H. Clínica:");
        lblHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 18));
        lblHistoriaClinica.setBounds(50, 120, 150, 30); // Ajustar posición
        panelPrincipal.add(lblHistoriaClinica);

        txtHistoriaClinica = new JTextField(
                visita != null ? String.valueOf(visita.getPacienteHistoriaClinicaID()) : "");
        txtHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 16));
        txtHistoriaClinica.setBounds(200, 120, 350, 30); // Ajustar posición
        txtHistoriaClinica.setEditable(false); // No se puede editar manualmente
        panelPrincipal.add(txtHistoriaClinica);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 18));
        lblFecha.setBounds(50, 160, 150, 30); // Uniforme separación
        panelPrincipal.add(lblFecha);

        JTextField txtFecha = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 16));
        txtFecha.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtFecha.setBounds(200, 160, 350, 30); // Uniforme separación
        txtFecha.setEditable(false); // Deshabilitar edición
        panelPrincipal.add(txtFecha);

        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        lblDiagnostico.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDiagnostico.setBounds(50, 200, 150, 30); // Uniforme separación
        panelPrincipal.add(lblDiagnostico);

        txtDiagnostico = new JTextField(visita != null ? visita.getDiagnostico() : "");
        txtDiagnostico.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDiagnostico.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtDiagnostico.setBounds(200, 200, 350, 30); // Uniforme separación
        panelPrincipal.add(txtDiagnostico);

        JLabel lblTratamiento = new JLabel("Tratamiento:");
        lblTratamiento.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTratamiento.setBounds(50, 240, 150, 30); // Uniforme separación
        panelPrincipal.add(lblTratamiento);

        txtTratamiento = new JTextField(visita != null ? visita.getTratamiento() : "");
        txtTratamiento.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTratamiento.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtTratamiento.setBounds(200, 240, 350, 30); // Uniforme separación
        panelPrincipal.add(txtTratamiento);

        JLabel lblEspecialidadRemitida = new JLabel("Especialidad Remitida:");
        lblEspecialidadRemitida.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEspecialidadRemitida.setBounds(50, 280, 200, 30); // Uniforme separación
        panelPrincipal.add(lblEspecialidadRemitida);

        cbEspecialidadRemitida = new JComboBox<>(new String[] {
                "Medicina General",
                "Neurología",
                "Neumología",
                "Gastroenterología",
                "Cardiología",
                "Ortopedia",
                "Obstetricia",
                "Alergología",
                "Rehabilitación"
        });
        cbEspecialidadRemitida.setFont(new Font("Arial", Font.PLAIN, 16));
        cbEspecialidadRemitida.setBounds(250, 280, 300, 30); // Uniforme separación
        cbEspecialidadRemitida.setSelectedItem(visita != null ? visita.getEspecialidadRemitida() : "Medicina General");
        panelPrincipal.add(cbEspecialidadRemitida);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDireccion.setBounds(50, 320, 150, 30); // Uniforme separación
        panelPrincipal.add(lblDireccion);

        txtDireccion = new JTextField(visita != null ? visita.getDireccion() : "");
        txtDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDireccion.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtDireccion.setBounds(200, 320, 350, 30); // Uniforme separación
        panelPrincipal.add(txtDireccion);

        JLabel lblAnalisis = new JLabel("Tipo de Análisis:");
        lblAnalisis.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAnalisis.setBounds(50, 360, 150, 30); // Uniforme separación
        panelPrincipal.add(lblAnalisis);

        cbTipoAnalisis = new JComboBox<>(new String[] { "(Ninguno)", "Sangre", "Orina", "Radiografía", "Otro" });
        cbTipoAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        cbTipoAnalisis.setBounds(200, 360, 350, 30); // Uniforme separación
        panelPrincipal.add(cbTipoAnalisis);

        modeloAnalisis = new DefaultListModel<>();
        listaAnalisis = new JList<>(modeloAnalisis);
        listaAnalisis.setFont(new Font("Arial", Font.PLAIN, 14));
        listaAnalisis.setBorder(new LineBorder(Color.GRAY, 1, true));
        listaAnalisis.setBounds(200, 400, 350, 100); // Ajustar posición
        panelPrincipal.add(listaAnalisis);

        btnAgregarAnalisis = new JButton("Agregar Análisis");
        btnAgregarAnalisis.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregarAnalisis.setBackground(new Color(0, 123, 255));
        btnAgregarAnalisis.setForeground(Color.WHITE);
        btnAgregarAnalisis.setBounds(50, 400, 150, 40); // Ajustar posición
        btnAgregarAnalisis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAnalisis();
            }
        });
        panelPrincipal.add(btnAgregarAnalisis);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0, 123, 255));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(200, 520, 150, 40); // Uniforme separación
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarVisita();
            }
        });
        panelPrincipal.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(400, 520, 150, 40); // Uniforme separación
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelPrincipal.add(btnCancelar);
    }

    private void actualizarResultados() {
        String texto = barraBusqueda.getText().trim();
        modeloResultados.clear();

        if (!texto.isEmpty()) {
            for (Paciente paciente : cmf.getPacientes()) {
                if (paciente.getCI().startsWith(texto)) {
                    modeloResultados.addElement(paciente.getCI());
                }
            }
        }

        listaResultados.setVisible(!modeloResultados.isEmpty());
    }

    private void agregarAnalisis() {
        String tipoAnalisis = (String) cbTipoAnalisis.getSelectedItem();
        if (tipoAnalisis != null && !tipoAnalisis.equals("(Ninguno)") && !tipoAnalisis.trim().isEmpty()) {
            modeloAnalisis.addElement(tipoAnalisis);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de análisis válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarVisita() {
        try {
            LocalDate fecha = LocalDate.now();
            String diagnostico = txtDiagnostico.getText().trim();
            String tratamiento = txtTratamiento.getText().trim();
            String especialidadRemitida = (String) cbEspecialidadRemitida.getSelectedItem(); // Obtener especialidad
                                                                                             // seleccionada
            String direccion = txtDireccion.getText().trim();
            Analisis analisis = null;
            if (!modeloAnalisis.isEmpty()) {
                String[] analisisArray = new String[modeloAnalisis.size()];
                for (int i = 0; i < modeloAnalisis.size(); i++) {
                    analisisArray[i] = modeloAnalisis.getElementAt(i);
                }
                analisis = new Analisis(String.join(", ", analisisArray), null);
            }

            Visita nuevaVisita = new Visita(
                    visita != null ? visita.getId() : cmf.obtenerListaVisitas().size() + 1,
                    Integer.parseInt(txtHistoriaClinica.getText().trim()),
                    fecha,
                    diagnostico,
                    tratamiento,
                    analisis,
                    especialidadRemitida,
                    direccion);

            if (visita != null) {
                cmf.eliminarVisita(visita.getId()); // Eliminar la visita anterior
            }

            cmf.agregarVisita(nuevaVisita); // Agregar la nueva visita

            JOptionPane.showMessageDialog(this, "Visita guardada exitosamente.");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo 'H. Clínica' debe contener un número válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la visita: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
