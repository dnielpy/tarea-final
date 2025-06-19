package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConstantesEspecialidades {

    public static final List<String> ESPECIALIDADES_REMITIDAS = Collections.unmodifiableList(Arrays.asList(
        "Medicina General",
        "Neurolog�a",
        "Neumolog�a",
        "Gastroenterolog�a",
        "Cardiolog�a",
        "Ortopedia",
        "Obstetricia",
        "Alergolog�a",
        "Rehabilitaci�n"
    ));

    private ConstantesEspecialidades() {
        // No instanciable
    }
}
