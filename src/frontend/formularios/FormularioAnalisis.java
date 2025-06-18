package frontend.formularios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

import entidades.registros.Visita;

public class FormularioAnalisis extends JDialog {
    private JTextField txtHistoriaClinica;
    private JTextField txtEspecialidad;
    private JTextField txtTipoDeAnalisis;
    private JTextArea txtResultados;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private Visita visita;

    public FormularioAnalisis(Window owner, Visita visita) {
        super(owner, "Formulario de Análisis", ModalityType.APPLICATION_MODAL);
        this.visita = visita;

        setLayout(null);
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(owner);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(0, 0, 500, 400);
        panelPrincipal.setLayout(null);
        add(panelPrincipal);

        JLabel lblTitulo = new JLabel("Formulario de Análisis");
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 20, 500, 30);
        panelPrincipal.add(lblTitulo);

        JLabel lblHistoriaClinica = new JLabel("Historia Clínica:");
        lblHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 16));
        lblHistoriaClinica.setBounds(30, 70, 150, 25);
        panelPrincipal.add(lblHistoriaClinica);

        txtHistoriaClinica = new JTextField(String.valueOf(visita.getPacienteHistoriaClinicaID()));
        txtHistoriaClinica.setFont(new Font("Arial", Font.PLAIN, 14));
        txtHistoriaClinica.setBounds(200, 70, 250, 25);
        txtHistoriaClinica.setEditable(false);
        txtHistoriaClinica.setBorder(new LineBorder(Color.GRAY, 1, true));
        panelPrincipal.add(txtHistoriaClinica);

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEspecialidad.setBounds(30, 110, 150, 25);
        panelPrincipal.add(lblEspecialidad);

        txtEspecialidad = new JTextField(visita.getEspecialidadRemitida());
        txtEspecialidad.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEspecialidad.setBounds(200, 110, 250, 25);
        txtEspecialidad.setEditable(false);
        txtEspecialidad.setBorder(new LineBorder(Color.GRAY, 1, true));
        panelPrincipal.add(txtEspecialidad);

        JLabel lblTipoDeAnalisis = new JLabel("Tipo de Análisis:");
        lblTipoDeAnalisis.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTipoDeAnalisis.setBounds(30, 150, 150, 25);
        panelPrincipal.add(lblTipoDeAnalisis);

        txtTipoDeAnalisis = new JTextField(visita.getAnalisis().getTipoDeAnalisis());
        txtTipoDeAnalisis.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTipoDeAnalisis.setBounds(200, 150, 250, 25);
        txtTipoDeAnalisis.setEditable(false);
        txtTipoDeAnalisis.setBorder(new LineBorder(Color.GRAY, 1, true));
        panelPrincipal.add(txtTipoDeAnalisis);

        JLabel lblResultados = new JLabel("Resultados:");
        lblResultados.setFont(new Font("Arial", Font.PLAIN, 16));
        lblResultados.setBounds(30, 190, 150, 25);
        panelPrincipal.add(lblResultados);

        txtResultados = new JTextArea(
                visita.getAnalisis().getResultados() != null ? visita.getAnalisis().getResultados() : "");
        txtResultados.setFont(new Font("Arial", Font.PLAIN, 14));
        txtResultados.setBounds(200, 190, 250, 100);
        txtResultados.setBorder(new LineBorder(Color.GRAY, 1, true));
        panelPrincipal.add(txtResultados);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(0, 123, 255));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(100, 320, 120, 30);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarResultados();
            }
        });
        panelPrincipal.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(280, 320, 120, 30);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelPrincipal.add(btnCancelar);
    }

    private void guardarResultados() {
        try {
            String resultados = txtResultados.getText().trim();
            if (resultados.isEmpty()) {
                throw new IllegalArgumentException("El campo de resultados no puede estar vacío.");
            }
            visita.getAnalisis().setResultados(resultados);
            JOptionPane.showMessageDialog(this, "Resultados guardados exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los resultados: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
