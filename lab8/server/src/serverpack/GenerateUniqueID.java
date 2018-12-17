package serverpack;

import java.util.Calendar;
import java.util.Date;

public class GenerateUniqueID {
    private static Integer idEnd = 0;

    /*
        should be changed to something more... useful
     */
    public static String generate() {
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        Integer month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Integer day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String id = year.toString();
        id += String.format("%02d", month);
        id += String.format("%02d", day);
        id += String.format("%02d", idEnd);
        ++idEnd;

        return id;
    }
}
