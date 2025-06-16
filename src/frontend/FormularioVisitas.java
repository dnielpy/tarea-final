package frontend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;

import service.Validations;
import entidades.Analisis;
import entidades.CMF;
import entidades.Visita;

public class FormularioVisitas extends JDialog {
    private JTextField txtPacienteID;
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
        setSize(600, 500); // Ajustar altura tras eliminar resultados
        setResizable(false);
        setLocationRelativeTo(owner);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(0, 0, 600, 500);
        panelPrincipal.setLayout(null);
        add(panelPrincipal);

        JLabel tituloFormulario = new JLabel("Formulario de Visitas");
        tituloFormulario.setFont(new Font("Arial Black", Font.BOLD, 24));
        tituloFormulario.setHorizontalAlignment(SwingConstants.CENTER);
        tituloFormulario.setBounds(0, 20, 600, 40);
        panelPrincipal.add(tituloFormulario);

        JLabel lblPacienteID = new JLabel("Paciente ID:");
        lblPacienteID.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPacienteID.setBounds(50, 80, 150, 30);
        panelPrincipal.add(lblPacienteID);

        txtPacienteID = new JTextField(visita != null ? visita.getPacienteID() : "");
        txtPacienteID.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPacienteID.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtPacienteID.setBounds(200, 80, 350, 30);
        panelPrincipal.add(txtPacienteID);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 18));
        lblFecha.setBounds(50, 120, 150, 30);
        panelPrincipal.add(lblFecha);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = dateFormat.format(new Date());
        JTextField txtFecha = new JTextField(fechaActual);
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 16));
        txtFecha.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtFecha.setBounds(200, 120, 350, 30);
        txtFecha.setEditable(false); // Deshabilitar edición
        panelPrincipal.add(txtFecha);

        JLabel lblDiagnostico = new JLabel("Diagnóstico:");
        lblDiagnostico.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDiagnostico.setBounds(50, 160, 150, 30);
        panelPrincipal.add(lblDiagnostico);

        txtDiagnostico = new JTextField(visita != null ? visita.getDiagnostico() : "");
        txtDiagnostico.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDiagnostico.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtDiagnostico.setBounds(200, 160, 350, 30);
        panelPrincipal.add(txtDiagnostico);

        JLabel lblTratamiento = new JLabel("Tratamiento:");
        lblTratamiento.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTratamiento.setBounds(50, 200, 150, 30);
        panelPrincipal.add(lblTratamiento);

        txtTratamiento = new JTextField(visita != null ? visita.getTratamiento() : "");
        txtTratamiento.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTratamiento.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtTratamiento.setBounds(200, 200, 350, 30);
        panelPrincipal.add(txtTratamiento);

        JLabel lblEspecialidadRemitida = new JLabel("Especialidad Remitida:");
        lblEspecialidadRemitida.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEspecialidadRemitida.setBounds(50, 240, 200, 30);
        panelPrincipal.add(lblEspecialidadRemitida);

        txtEspecialidadRemitida = new JTextField(visita != null ? visita.getEspecialidadRemitida() : "");
        txtEspecialidadRemitida.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEspecialidadRemitida.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtEspecialidadRemitida.setBounds(250, 240, 300, 30);
        panelPrincipal.add(txtEspecialidadRemitida);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDireccion.setBounds(50, 280, 150, 30);
        panelPrincipal.add(lblDireccion);

        txtDireccion = new JTextField(visita != null ? visita.getDireccion() : "");
        txtDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDireccion.setBorder(new LineBorder(Color.GRAY, 1, true));
        txtDireccion.setBounds(200, 280, 350, 30);
        panelPrincipal.add(txtDireccion);

        JLabel lblAnalisis = new JLabel("Tipo de Análisis:");
        lblAnalisis.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAnalisis.setBounds(50, 320, 150, 30);
        panelPrincipal.add(lblAnalisis);

        cbTipoAnalisis = new JComboBox<>(new String[] { "Sangre", "Orina", "Radiografía", "Otro" });
        cbTipoAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        cbTipoAnalisis.setBounds(200, 320, 350, 30);
        cbTipoAnalisis.setSelectedItem(
                visita != null && visita.getAnalisis() != null ? visita.getAnalisis().getTipoDeAnalisis() : "Sangre");
        panelPrincipal.add(cbTipoAnalisis);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0, 123, 255));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(200, 400, 150, 40);
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
        btnCancelar.setBounds(400, 400, 150, 40);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelPrincipal.add(btnCancelar);
    }

    private void guardarVisita() {
    	cmf = CMF.getInstance();
    	try {
            String pacienteID = txtPacienteID.getText().trim();
            Date fecha = new Date(); // Guardar como objeto Date
            String diagnostico = txtDiagnostico.getText().trim();
            String tratamiento = txtTratamiento.getText().trim();
            String especialidadRemitida = txtEspecialidadRemitida.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String tipoAnalisis = (String) cbTipoAnalisis.getSelectedItem();

            if (pacienteID.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty() || especialidadRemitida.isEmpty()
                    || direccion.isEmpty() || tipoAnalisis.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            if (!Validations.isValidCI(pacienteID)) {
                throw new IllegalArgumentException("El ID del paciente no es válido.");
            }

            Analisis analisis = new Analisis(tipoAnalisis, null);
            Visita nuevaVisita = new Visita(cmf.obtenerListaVisitas().size() + 1, pacienteID, fecha, diagnostico,
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
