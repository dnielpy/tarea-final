package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ConstantesVacunas {

    private ConstantesVacunas() {
        // Evita instanciación
    }

    public static final List<String> VACUNAS = Collections.unmodifiableList(Arrays.asList(
        "Antitetánica",
        "BCG",
        "Hepatitis B",
        "Neumococo",
        "Poliomielitis",
        "Sarampión",
        "COVID-19",
        "Influenza",
        "Varicela",
        "Rotavirus"
    ));
}
