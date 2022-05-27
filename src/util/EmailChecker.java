package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailChecker {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isCorrect(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

}
