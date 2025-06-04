package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class Validations {

    public static boolean validateName(String nombre) {
        boolean isValid = true;

        if (nombre == null) {
            isValid = false;
        } else if (nombre.trim().isEmpty()) {
            isValid = false;
        } else if (nombre.length() > 100) {
            isValid = false;
        }

        return isValid;
    }

    public static boolean isValidCI(String ci) {
        boolean isValid = true;
    
        if (ci == null || ci.length() != 11 || !ci.matches("\\d{11}")) {
            isValid = false;
        } else {
            String year = ci.substring(0, 2);
            String month = ci.substring(2, 4);
            String day = ci.substring(4, 6);
    
            int yearInt = Integer.parseInt(year);
            if (yearInt < 0 || yearInt > 99) {
                isValid = false;
            }
    
            int monthInt = Integer.parseInt(month);
            if (monthInt < 1 || monthInt > 12) {
                isValid = false;
            }
    
            int dayInt = Integer.parseInt(day);
            if (dayInt < 1 || dayInt > 31) {
                isValid = false;
            }
    
            if (monthInt == 2 && dayInt > 29) {
                isValid = false;
            } else if ((monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11) && dayInt > 30) {
                isValid = false;
            }
        }
    
        return isValid;
    }

    public static int getAgeFromCI(String ci) {
        boolean isValid = isValidCI(ci);
        int age = -1;

        if (isValid) {
            String birthDateStr = ci.substring(0, 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            age = Period.between(birthDate, LocalDate.now()).getYears();
        }

        return age;
    }

    public static String getGenderFromCI(String ci) {
        boolean isValid = isValidCI(ci);
        String gender = "Desconocido";

        if (isValid) {
            int genderDigit = Character.getNumericValue(ci.charAt(6));
            gender = (genderDigit % 2 == 0) ? "Femenino" : "Masculino";
        }

        return gender;
    }
}