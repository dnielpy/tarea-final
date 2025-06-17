package frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import service.Validations;
import entidades.Analisis;
import entidades.CMF;
import entidades.Paciente;
import entidades.Visita;

public class FormularioVisitas extends JDialog {
    private JTextField barraBusqueda; // Barra de búsqueda para IDs de pacientes
    private JList<String> listaResultados; // Lista para mostrar resultados de búsqueda
    private DefaultListModel<String> modeloResultados;
    private JTextField txtPacienteID; // Campo no editable para mostrar el ID seleccionado
    private JTextField txtDiagnostico;
    private JTextField txtTratamiento;
    private JTextField txtEspecialidadRemitida;
    private JTextField txtDireccion;
    private JComboBox<String> cbTipoAnalisis;
    private JButton btnGuardar;
    private JButton btnCancelar;
    CMF cmf;

    public FormularioVisitas(Window owner, Visita visita) {
        super(owner, "Formulario de Visitas", ModalityType.APPLICATION_MODAL);
        setLayout(null);
        setSize(600, 550); // Ajustar altura de la ventana
        setResizable(false);
        setLocationRelativeTo(owner);

        cmf = CMF.getInstance();

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(0, 0, 600, 550);
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

        listaResultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String seleccionado = listaResultados.getSelectedValue();
                    txtPacienteID.setText(seleccionado); // Actualizar el campo PacienteID
                    listaResultados.setVisible(false); // Ocultar lista tras selección
                }
            }
        });

        JLabel lblPacienteID = new JLabel("Paciente ID:");
        lblPacienteID.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPacienteID.setBounds(50, 120, 150, 30); // Ajustar posición
        panelPrincipal.add(lblPacienteID);

        txtPacienteID = new JTextField();
        txtPacienteID.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPacienteID.setBounds(200, 120, 350, 30); // Ajustar posición
        txtPacienteID.setEditable(false); // No se puede editar manualmente
        panelPrincipal.add(txtPacienteID);

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

        txtEspecialidadRemitida = new JTextField(visita != null ? visita.getEspecialidadRemitida() : "");
        txtEspecialidadRemitida.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEspecialidadRemitida.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtEspecialidadRemitida.setBounds(250, 280, 300, 30); // Uniforme separación
        panelPrincipal.add(txtEspecialidadRemitida);

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

        cbTipoAnalisis = new JComboBox<>(new String[] { "Sangre", "Orina", "Radiografía", "Otro" });
        cbTipoAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        cbTipoAnalisis.setBounds(200, 360, 350, 30); // Uniforme separación
        panelPrincipal.add(cbTipoAnalisis);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0, 123, 255));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(200, 420, 150, 40); // Uniforme separación
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
        btnCancelar.setBounds(400, 420, 150, 40); // Uniforme separación
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

    private void guardarVisita() {
        try {
            String pacienteID = txtPacienteID.getText().trim();

            if (pacienteID.isEmpty()) {
                throw new IllegalArgumentException("Debe seleccionar un paciente desde la barra de búsqueda.");
            }

            Paciente pacienteSeleccionado = null;

            for (Paciente paciente : cmf.getPacientes()) {
                if (paciente.getCI().equals(pacienteID)) {
                    pacienteSeleccionado = paciente;
                    break;
                }
            }

            if (pacienteSeleccionado == null) {
                throw new IllegalArgumentException("El ID del paciente no coincide con ningún paciente registrado.");
            }

            int historiaClinicaID = pacienteSeleccionado.getHistoriaClinica().getId(); // Obtener historia clínica

            Date fecha = new Date(); // Guardar como objeto Date
            String diagnostico = txtDiagnostico.getText().trim();
            String tratamiento = txtTratamiento.getText().trim();
            String especialidadRemitida = txtEspecialidadRemitida.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String tipoAnalisis = (String) cbTipoAnalisis.getSelectedItem();

            if (diagnostico.isEmpty() || tratamiento.isEmpty() || especialidadRemitida.isEmpty()
                    || direccion.isEmpty() || tipoAnalisis.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            Analisis analisis = new Analisis(tipoAnalisis, null);
            Visita nuevaVisita = new Visita(historiaClinicaID, pacienteID, fecha, diagnostico,
                    tratamiento, analisis, especialidadRemitida, direccion);
            cmf.agregarVisita(nuevaVisita);
            JOptionPane.showMessageDialog(this, "Visita guardada exitosamente.");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la visita: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
