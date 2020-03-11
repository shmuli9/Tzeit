import net.sourceforge.zmanim.hebrewcalendar.HebrewDateFormatter;
import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;

import java.util.Calendar;

public class LocalCalendar {
    public LocalCalendar(Ingest dates) {
        JewishCalendar jd = new JewishCalendar();
        HebrewDateFormatter hdf = new HebrewDateFormatter();

        int currentJYear = jd.getJewishYear();

        jd.setGregorianDate(2020, Calendar.MARCH, 9);

        JewishCalendar jd2 = new JewishCalendar(currentJYear + 1, jd.getJewishMonth(), jd.getJewishDayOfMonth());
    }
}
