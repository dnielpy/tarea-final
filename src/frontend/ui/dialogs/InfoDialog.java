package frontend.ui.dialogs;

import javax.swing.*;

import util.ConstantesFrontend;
import util.UtilSonido;
import frontend.ui.botones.BotonBlanco;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class InfoDialog extends JDialog implements ConstantesFrontend {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Estado {
        INFORMACION,
        ERROR,
        WARNING,
        EXITO
    }

    private static final HashMap<Estado, String> ICONOS = new HashMap<>();
    static {
        ICONOS.put(Estado.INFORMACION, "/fotos/info-35x35.png");
        ICONOS.put(Estado.ERROR, "/fotos/error-35x35.png");
        ICONOS.put(Estado.WARNING, "/fotos/warning-35x35.png");
        ICONOS.put(Estado.EXITO, "/fotos/exito-35x35.png");
    }
    
    private static final HashMap<Estado, String> SONIDOS = new HashMap<>();
    static {
        SONIDOS.put(Estado.INFORMACION, "sonidos/info.wav");
        SONIDOS.put(Estado.ERROR, "sonidos/error.wav");
        SONIDOS.put(Estado.WARNING, "sonidos/warning.wav");
        SONIDOS.put(Estado.EXITO, "sonidos/exito.wav");
    }

    public InfoDialog(Window parent, String titulo, String mensaje, Estado estado) {
        super(parent, titulo, DEFAULT_MODALITY_TYPE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/fotos/Logo peque.png")));
        initUI(mensaje, estado);
        reproducirSonido(estado);
    }

    private void initUI(String mensajeTexto, Estado estado) {
        setSize(500, 200);
        setLocationRelativeTo(getParent());
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(new BorderLayout(10, 10));

        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel iconoLabel = new JLabel(getIconoPorEstado(estado));
        iconoLabel.setBounds(55, 20, 35, 82);
        iconoLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel mensaje = new JLabel("<html><div style='text-align: center;'>" + mensajeTexto.replaceAll("\n", "<br>") + "</div></html>");
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        mensaje.setBounds(91, 20, 305, 82);
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16));
        mensaje.setForeground(Color.BLACK);
        panelCentral.setLayout(null);

        panelCentral.add(iconoLabel);
        panelCentral.add(mensaje);

        getContentPane().add(panelCentral, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setBackground(Color.WHITE);

        BotonBlanco btnAceptar = new BotonBlanco("ACEPTAR");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        botones.add(btnAceptar);

        getContentPane().add(botones, BorderLayout.SOUTH);
    }

    private ImageIcon getIconoPorEstado(Estado estado) {
        String ruta = ICONOS.get(estado);
        return new ImageIcon(getClass().getResource(ruta));
    }
    
    private void reproducirSonido(Estado estado) {
        String ruta = SONIDOS.get(estado);
        if (ruta != null) {
            UtilSonido.reproducir(ruta);
        }
    }
}
