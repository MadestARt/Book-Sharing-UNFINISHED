package util;

import lombok.experimental.UtilityClass;


import java.time.LocalTime;


@UtilityClass
public class HelloMessageManager {

    public static String buildHelloMessage() {
        var localTime = LocalTime.now();
        return getTimeByLocal(localTime);
    }

    private static String getTimeByLocal(LocalTime localTime) {
        var hour = localTime.getHour();
        if (hour > 5 && hour < 12) {
            return "Доброе утро";
        } else if (hour > 11 && hour < 18) {
            return "Добрый день";
        } else if (hour > 17 && hour < 23) {
            return "Добрый вечер";
        }
        return "Доброй ночи";
    }
}
