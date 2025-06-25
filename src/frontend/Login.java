package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import runner.Auth;
import util.ConstantesFrontend;
import entidades.CMF;
import entidades.personal.Usuario;
import frontend.ui.botones.BotonBlanco;
import frontend.ui.botones.ImageButtonLabel;
import frontend.ui.dialogs.CopyDialog;
import frontend.ui.dialogs.InfoDialog;
import frontend.ui.dialogs.QuestionDialog;
import frontend.ui.placeholders.PlaceholderAndToggle;
import frontend.ui.placeholders.PlaceholderTextField;
import frontend.ui.placeholders.PlaceholderTextField.InputFormat;

public class Login extends JDialog implements ConstantesFrontend {

    private PlaceholderTextField campoUsuario;
    private PlaceholderAndToggle campoContrasenna;
    private boolean auth;

    public Login(Window parent) {
        super(parent, "Autenticación", ModalityType.APPLICATION_MODAL);
        this.auth = false;

        setResizable(false);
        setType(Type.POPUP);
        setBackground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/fotos/Logo peque.png")));
        setTitle("Autenticación");
        setBounds(100, 100, 800, 510);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                QuestionDialog confirmacion = new QuestionDialog(Login.this, "Confirmar salida", "¿Seguro de que desea cerrar la aplicación?");
                confirmacion.setVisible(true);
                if (confirmacion.esConfirmado()) {
                    System.exit(0);
                }
            }
        });

        inicializarInterfaz();
    }

    public boolean authenticado() {
        return auth;
    }

    public void iniciarSesion() {
        InfoDialog dialogo = new InfoDialog(this.getOwner(), "Inicio de sesión", "Inicio de sesión exitoso");
        dialogo.setVisible(true);
        dispose();
    }

    public void autenticar() {
        String usuario = campoUsuario.getText();
        String contrasenna = new String(campoContrasenna.getPassword());

        boolean campoVacio = contrasenna.trim().isEmpty();

        if (campoVacio) {
            InfoDialog dialogo = new InfoDialog(this.getOwner(), "Error de autenticación", "La contraseña no puede estar vacía");
            dialogo.setVisible(true);
        } else {
            Auth authManager = new Auth();
            try {
                Usuario usuarioAutenticado = authManager.authUser(usuario, contrasenna);
                CMF.getInstance().setUsuario(usuarioAutenticado);
                auth = true;
                iniciarSesion();
            } catch (Auth.AuthenticationException ex) {
                InfoDialog dialogo = new InfoDialog(this.getOwner(), "Error de autenticación", ex.getMessage());
                dialogo.setVisible(true);
            }
        }
    }

    public void mostrarInformacion() {
        Usuario usuarioMedico = CMF.getInstance().getMedico().getUser();
        Usuario usuarioEnfermera = CMF.getInstance().getEnfermera().getUser();

        String datosMedico = usuarioMedico.getUserName() + " / " + usuarioMedico.getPassword();
        String datosEnfermera = usuarioEnfermera.getUserName() + " / " + usuarioEnfermera.getPassword();

        String mensaje = "Para probar el programa inicie sesión con alguno de estos usuarios:<br><br>"
                + "<b>Médico:</b> " + datosMedico + "<br>"
                + "<b>Enfermera:</b> " + datosEnfermera;

        new CopyDialog(this, "Información", mensaje).setVisible(true);
    }

    private void inicializarInterfaz() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(0, 0, 794, 534);
        getContentPane().setLayout(null);
        getContentPane().add(panelPrincipal);
        panelPrincipal.setLayout(null);

        JPanel panelLateral = new JPanel();
        panelLateral.setBackground(COLOR_AZUL);
        panelLateral.setBounds(0, 232, 250, 289);
        panelLateral.setLayout(null);
        panelPrincipal.add(panelLateral);

        JLabel cartelUsuario = new JLabel("USUARIO");
        cartelUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        cartelUsuario.setBounds(325, 139, 92, 26);
        panelPrincipal.add(cartelUsuario);

        campoUsuario = new PlaceholderTextField("Ingrese el nombre de usuario");
        campoUsuario.setInputFormat(InputFormat.ANY);
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        campoUsuario.setPermitirEspacios(false);
        campoUsuario.setBackground(Color.WHITE);
        campoUsuario.setBounds(325, 172, 379, 26);
        campoUsuario.setColumns(10);
        campoUsuario.setBorder(null);
        panelPrincipal.add(campoUsuario);

        campoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        JLabel cartelContrasenna = new JLabel("CONTRASEÑA");
        cartelContrasenna.setFont(new Font("Arial", Font.PLAIN, 18));
        cartelContrasenna.setBounds(325, 249, 144, 26);
        panelPrincipal.add(cartelContrasenna);

        campoContrasenna = new PlaceholderAndToggle("Ingrese su contraseña");
        campoContrasenna.setBounds(325, 281, 379, 32);
        panelPrincipal.add(campoContrasenna);

        campoContrasenna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        JLabel cartelIniciarSesion = new JLabel("INICIAR SESIÓN");
        cartelIniciarSesion.setFont(new Font("Arial Black", Font.BOLD, 21));
        cartelIniciarSesion.setBounds(325, 82, 210, 26);
        panelPrincipal.add(cartelIniciarSesion);

        BotonBlanco botonAceptar = new BotonBlanco("ACEPTAR");
        botonAceptar.setToolTipText("Clic para iniciar sesión con los datos introducidos");
        botonAceptar.setBounds(325, 360, 129, 33);
        botonAceptar.setLayout(null);
        panelPrincipal.add(botonAceptar);

        botonAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        ImageButtonLabel botonInfo = new ImageButtonLabel((ImageIcon) null);
        botonInfo.setIcon(new ImageIcon(Login.class.getResource("/fotos/Info.png")));
        botonInfo.setBounds(466, 360, 33, 33);
        botonInfo.setText("");
        panelPrincipal.add(botonInfo);

        botonInfo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                mostrarInformacion();
            }
        });

        JPanel panelLogo = new JPanel();
        panelLogo.setLayout(null);
        panelLogo.setBackground(new Color(109, 163, 67));
        panelLogo.setBounds(0, 0, 250, 225);
        panelPrincipal.add(panelLogo);

        JLabel cartelLogo = new JLabel("");
        cartelLogo.setIcon(new ImageIcon(Login.class.getResource("/fotos/Logo peque.png")));
        cartelLogo.setHorizontalAlignment(SwingConstants.CENTER);
        cartelLogo.setBounds(0, 15, 250, 120);
        panelLogo.add(cartelLogo);

        JLabel cartelTipografia = new JLabel();
        cartelTipografia.setIcon(new ImageIcon(Login.class.getResource("/fotos/Tipografia-Peque.png")));
        cartelTipografia.setHorizontalAlignment(SwingConstants.CENTER);
        cartelTipografia.setBounds(0, 129, 250, 62);
        panelLogo.add(cartelTipografia);

        JPanel panelBlanco = new JPanel();
        panelBlanco.setBorder(new LineBorder(Color.WHITE, 1, true));
        panelBlanco.setBackground(Color.WHITE);
        panelBlanco.setBounds(21, 15, 208, 189);
        panelLogo.add(panelBlanco);
    }
}
