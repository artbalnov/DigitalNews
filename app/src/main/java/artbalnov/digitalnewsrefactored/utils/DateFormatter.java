package artbalnov.digitalnewsrefactored.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Util that used for formatting or converting date
 */

public class DateFormatter {

    public static final String DD_MM_YYYY_HH_MM = "dd.MM.yyyy HH:mm";

    public static String formatDateFromMs(String format, long ms) {
        if (ms == 0)
            ms = Calendar.getInstance().getTimeInMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return simpleDateFormat.format(new Date(ms));
    }

}
