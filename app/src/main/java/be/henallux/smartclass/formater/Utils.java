package be.henallux.smartclass.formater;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String formattedPercent(double percent) {
        NumberFormat formatter = new DecimalFormat("#.0");
        return formatter.format(percent)+"%";
    }

    public static String formattedOneDecimalWithoutZero(double percent) {
        NumberFormat formatter = new DecimalFormat("#.#");
        return formatter.format(percent);
    }

    public static String formattedDate(Date date) {
        return new SimpleDateFormat("EEE dd MMM yyyy", Locale.FRANCE).format(date);
    }
}
