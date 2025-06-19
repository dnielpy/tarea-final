package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConstantesAnalisis {

    public static final List<String> TIPOS_ANALISIS = Collections.unmodifiableList(Arrays.asList(
        "Sangre",
        "Orina",
        "Radiografía",
        "Ultrasonido",
        "Otro"
    ));

    private ConstantesAnalisis() {
        // Clase de utilidades, no instanciable
    }
}
