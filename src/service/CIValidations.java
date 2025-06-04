package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class CIValidations {

    public static boolean isValidCI(String ci) {
        if (ci == null || ci.length() != 11 || !ci.matches("\\d{11}")) {
            return false;
        }
        try {
            String birthDateStr = ci.substring(0, 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            return !birthDate.isAfter(LocalDate.now());
        } catch (Exception e) {
            return false;
        }
    }

    public static int getAgeFromCI(String ci) {
        if (!isValidCI(ci)) {
            throw new IllegalArgumentException("CI inválido");
        }
        String birthDateStr = ci.substring(0, 6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static String getGenderFromCI(String ci) {
        if (!isValidCI(ci)) {
            throw new IllegalArgumentException("CI inválido");
        }
        int genderDigit = Character.getNumericValue(ci.charAt(6));
        return (genderDigit % 2 == 0) ? "Femenino" : "Masculino";
    }
}