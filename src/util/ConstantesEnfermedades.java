package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ConstantesEnfermedades {

    private ConstantesEnfermedades() {
        // Evita instanciación
    }

    public static final List<String> ENFERMEDADES = Collections.unmodifiableList(Arrays.asList(
        "Asma",
        "Diabetes",
        "Hipertensión",
        "Epilepsia",
        "Cardiopatía",
        "Enfermedad renal crónica",
        "Cáncer",
        "Artritis",
        "Tuberculosis",
        "VIH/SIDA"
    ));
}
