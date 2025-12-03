package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utilidades generales para manejo de fechas.
 */
public class DateUtils {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Convierte texto "yyyy-MM-dd" → LocalDate
     */
    public static LocalDate parse(String dateText) {
        try {
            return LocalDate.parse(dateText, FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Convierte LocalDate → "yyyy-MM-dd"
     */
    public static String format(LocalDate date) {
        return date.format(FORMAT);
    }

    /**
     * Verifica que fechaInicio <= fechaFin
     */
    public static boolean validarRango(LocalDate inicio, LocalDate fin) {
        return inicio != null && fin != null && !inicio.isAfter(fin);
    }
}
