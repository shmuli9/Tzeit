import net.sourceforge.zmanim.hebrewcalendar.HebrewDateFormatter;
import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;
import net.sourceforge.zmanim.hebrewcalendar.JewishDate;

import java.time.Month;
import java.util.Calendar;

public class LocalCalendar {
    public LocalCalendar(Ingest dates) {
        JewishCalendar jd = new JewishCalendar();
        HebrewDateFormatter hdf = new HebrewDateFormatter();

        jd.setGregorianDate(2020, Calendar.MARCH, 9);

        System.out.println(jd);
        System.out.println(jd.getGregorianCalendar().getTime());

        hdf.setHebrewFormat(true); // change formatting to Hebrew
        System.out.println(hdf.format(jd)); // date formatted in Hebrew
    }

    public void out(String type, int numYears) {

    }
}
