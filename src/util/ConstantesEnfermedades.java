package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ConstantesEnfermedades {

    private ConstantesEnfermedades() {
        // Evita instanciaci�n
    }

    public static final List<String> ENFERMEDADES = Collections.unmodifiableList(Arrays.asList(
        "Asma",
        "Diabetes",
        "Hipertensi�n",
        "Epilepsia",
        "Cardiopat�a",
        "Enfermedad renal cr�nica",
        "C�ncer",
        "Artritis",
        "Tuberculosis",
        "VIH/SIDA"
    ));
}
