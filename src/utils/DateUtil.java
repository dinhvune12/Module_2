package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final DateTimeFormatter F = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parse(String s) {
        return LocalDate.parse(s, F);
    }
    public static String format(LocalDate d) {
        return d.format(F);
    }
}
