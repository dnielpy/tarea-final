package service;

import java.time.LocalDate;
import java.time.Period;

import excepciones.Excepciones.IllegalCiException;

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
			throw new IllegalCiException("El CI debe tener 11 dígitos y solo contener números");
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

	public static int getYearsFromString(String string) {
		int years = -1;
		try {
			if (string.length() >= 6) {
				int year = Integer.parseInt(string.substring(0, 2));
				int month = Integer.parseInt(string.substring(2, 4));
				int day = Integer.parseInt(string.substring(4, 6));
				int currentYear = LocalDate.now().getYear();
				int currentCentury = (currentYear / 100) * 100;
				// Determinar el siglo (asume personas con menos de 120 anios)
				int fullYear = (year > currentYear % 100) ? (currentCentury - 100 + year) : (currentCentury + year);
				// Validar que la fecha sea valida
				LocalDate nacimiento = LocalDate.of(fullYear, month, day);
				LocalDate hoy = LocalDate.now();
				years = Period.between(nacimiento, hoy).getYears();
			}
		} catch (Exception e) {
			// years queda en -1 si hay error
		}
		return years;
	}

	public static int getAgeFromCI(String ci) {
		boolean isValid = isValidCI(ci);
		int age = -1;

		if (isValid) {
			age = getYearsFromString(ci);
		}

		return age;
	}

	public static boolean isFemale(String ci) {
		boolean isValid = isValidCI(ci);
		boolean isFemale = false;

		if (isValid) {
			int genderDigit = Character.getNumericValue(ci.charAt(9));
			isFemale = (genderDigit % 2 == 0) ? false : true;
		}

		return isFemale;
	}


	public static String capitalize(String text) {        
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("El texto no puede ser vacio o nulo.");
		}
		String[] words = text.split("\\s+");
		StringBuilder capitalizedText = new StringBuilder();
		for (String word : words) {
			if (!word.isEmpty()) {
				capitalizedText.append(Character.toUpperCase(word.charAt(0)))
				.append(word.substring(1).toLowerCase())
				.append(" ");
			}
		}
		return capitalizedText.toString().trim();
	}

}