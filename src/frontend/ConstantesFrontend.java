package frontend;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public interface ConstantesFrontend {
	 final Color COLOR_AZUL = new Color(0, 171, 227);
	 final Color COLOR_VERDE = new Color(109, 163, 67);
	 final Color COLOR_GRIS = SystemColor.controlShadow;
	 final Color COLOR_GRIS_CLARO = SystemColor.menu;
	 final Color COLOR_GRIS_CURSOR = SystemColor.controlHighlight;
	 final Border BORDE_COMPONENTE = new LineBorder(COLOR_GRIS, 1, true);
	 final Color COLOR_PRESIONADO = Color.LIGHT_GRAY;
	 final Border BORDE_SOMBRA_PEQUENA = BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(0, 0, 0, 50));
	 final Border BORDE_SOMBRA_GRANDE = BorderFactory.createMatteBorder(0, 0, 3, 3, new Color(0, 0, 0, 50));
	 final Border BORDE_PEQUENO = BorderFactory.createCompoundBorder(BORDE_SOMBRA_PEQUENA, BORDE_COMPONENTE);
	 final Border BORDE_GRANDE = BorderFactory.createCompoundBorder(BORDE_SOMBRA_GRANDE, BORDE_COMPONENTE);
}
