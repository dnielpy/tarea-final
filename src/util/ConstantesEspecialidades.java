package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConstantesEspecialidades {

    public static final List<String> ESPECIALIDADES_REMITIDAS = Collections.unmodifiableList(Arrays.asList(
        "Medicina General",
        "Neurología",
        "Neumología",
        "Gastroenterología",
        "Cardiología",
        "Ortopedia",
        "Obstetricia",
        "Alergología",
        "Rehabilitación"
    ));

    private ConstantesEspecialidades() {
        // No instanciable
    }
}
