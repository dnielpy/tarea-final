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
			try {
				String yearStr = ci.substring(0, 2);
				String monthStr = ci.substring(2, 4);
				String dayStr = ci.substring(4, 6);

				int year = Integer.parseInt(yearStr);
				int month = Integer.parseInt(monthStr);
				int day = Integer.parseInt(dayStr);

				int currentYear = LocalDate.now().getYear();
				int currentCentury = (currentYear / 100) * 100;
				if (year > currentYear % 100) {
					year += currentCentury - 100; // Siglo anterior
				} else {
					year += currentCentury; // Siglo actual
				}

				int currentMonth = LocalDate.now().getMonthValue();
				int currentDay = LocalDate.now().getDayOfMonth();
				age = currentYear - year;

				if (currentMonth < month || (currentMonth == month && currentDay < day)) {
					age--;
				}
			} catch (Exception e) {
				System.err.println("Error al calcular la edad desde el CI: " + e.getMessage());
			}
		}

		return age;
	}

	public static boolean isFemale(String ci) {
		boolean isValid = isValidCI(ci);
		boolean isFemale = false;

		if (isValid) {
			int genderDigit = Character.getNumericValue(ci.charAt(6));
			isFemale = (genderDigit % 2 == 0) ? true : false;
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