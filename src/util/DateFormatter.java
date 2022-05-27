package util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@UtilityClass
public class DateFormatter {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static boolean isCorrect(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    public static Optional<LocalDate> getDateFrom(String date) {
        if (isCorrect(date)) {
            return Optional.of(LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)));
        } else {
            return Optional.empty();
        }
    }
}
