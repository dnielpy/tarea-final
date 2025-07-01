package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ConstantesVacunas {

    private ConstantesVacunas() {
        // Evita instanciaci�n
    }

    public static final List<String> VACUNAS = Collections.unmodifiableList(Arrays.asList(
        "Antitet�nica",
        "BCG",
        "Hepatitis B",
        "Neumococo",
        "Poliomielitis",
        "Sarampi�n",
        "COVID-19",
        "Influenza",
        "Varicela",
        "Rotavirus"
    ));
}
