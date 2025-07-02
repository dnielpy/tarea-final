package frontend;

import java.awt.BorderLayout;

import javax.swing.*;

import util.UtilSonido;

import java.awt.*;

public class AcercaDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AcercaDialog(Window parent) {
        super(parent, "Acerca de nosotros", ModalityType.APPLICATION_MODAL);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/fotos/Logo peque.png")));
        setSize(796, 673);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        UtilSonido.reproducir("sonidos/ventana.wav");

        getContentPane().add(crearTextoPrincipal(), BorderLayout.NORTH);
        getContentPane().add(crearPanelCentralQRs(), BorderLayout.CENTER);

        setLocationRelativeTo(parent);
    }

    private JLabel crearTextoPrincipal() {
        JLabel textoRepo = new JLabel(
            "<html><div style='text-align: center;'>"
            + "Este proyecto fue desarrollado como parte de la tarea final de la asignatura "
            + "<b>Diseño de Programación Orientada a Objetos</b>. Al momento de su entrega, "
            + "el repositorio estará disponible públicamente y abierto a contribuciones de la comunidad. "
            + "Se invita a los usuarios a realizar forks, sugerir mejoras y proponer cambios. "
            + "</div></html>", 
            SwingConstants.CENTER
        );
        textoRepo.setFont(new Font("Arial", Font.PLAIN, 16));
        return textoRepo;
    }

    private JPanel crearPanelCentralQRs() {
        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentral.setBackground(Color.WHITE);

        // Primer QR - Repositorio
        panelCentral.add(crearEtiquetaImagen("/fotos/uDX0l6.jpg", 200, 200));
        panelCentral.add(crearEtiquetaTexto(
            "<html><div style='text-align: left;'>"
            + "<b>Repositorio del Proyecto:</b><br>"
            + "Escanee este código QR para acceder al repositorio del proyecto y contribuir con mejoras."
            + "</div></html>", 14
        ));

        // Segundo QR - Documentación
        panelCentral.add(crearEtiquetaImagen("/fotos/web.jpg", 200, 200));
        panelCentral.add(crearEtiquetaTexto(
            "<html><div style='text-align: left;'>"
            + "<b>Documentación del Proyecto:</b><br>"
            + "Escanee este código QR para acceder a la documentación completa del proyecto."
            + "</div></html>", 16
        ));

        return panelCentral;
    }

    private JLabel crearEtiquetaImagen(String recursoPath, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(getClass().getResource(recursoPath));
        Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        return etiquetaImagen;
    }

    private JLabel crearEtiquetaTexto(String htmlTexto, int tamanoFuente) {
        JLabel etiquetaTexto = new JLabel(htmlTexto, SwingConstants.LEFT);
        etiquetaTexto.setFont(new Font("Arial", Font.PLAIN, tamanoFuente));
        return etiquetaTexto;
    }

    public void mostrar() {
        setVisible(true);
    }
}
