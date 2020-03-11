import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;

import java.io.IOException;
import java.time.Instant;
import java.util.Calendar;

public class Event {
    String name;
    JewishCalendar jDate;
    boolean hebrew;

    public Event(String name, int day, int month, int year, boolean hebrew) {
        this.name = name;
        this.hebrew = hebrew;

        if (isHebrew()) {
            // if hebrew save hebrew and english
            this.jDate = new JewishCalendar();
            this.jDate.setJewishDate(year, month, day);
        } else {
            // if not hebrew, find hebrew
            this.jDate = new JewishCalendar();
            this.jDate.setGregorianDate(year, month - 1, day);
        }
    }

    @Override
    public String toString() {
        return "Event {" +
                "name='" + name + "' " +
                "date='" + this.getRawDate()
                + "'}";
    }

    public String getRawDate() {
        return String.valueOf(this.jDate);
    }

    /******
     * Calculate English date, for a given year, of a Hebrew date (or Gregorian date in a specific year)
     * @return
     */
    public void hebFallsOn(int numYears) {
        // set gregorian date
        JewishCalendar gDate = new JewishCalendar(); // defaults to current date
        int currentYear = gDate.getJewishYear();
//        System.out.println(this.jDate.getJewishMonth());

        // todo deal with stray adars...

        JewishCalendar temp = new JewishCalendar();

        for (int i = 0; i < numYears; i++) {
            int year = currentYear + (i + 1);
            temp.setJewishDate(year, dealWithAdar(this.jDate.getJewishMonth(), year), this.jDate.getJewishDayOfMonth());
            System.out.println(temp + " - " + temp.getGregorianCalendar().getTime());
        }
    }

    private JewishCalendar getDatesForYear(int jewishYear) {
        JewishCalendar temp = new JewishCalendar();

        int year = jewishYear;
        temp.setJewishDate(year, dealWithAdar(this.jDate.getJewishMonth(), year), this.jDate.getJewishDayOfMonth());

        return temp;
    }

    private String formatCalendar(Calendar cal) {
        int day = cal.getTime().getDay();
        int month = cal.getTime().getMonth();
        int year = cal.getTime().getYear();

        return String.format("{}/{}/{}", day, month, year);
    }

    private int dealWithAdar(int month, int year) {
        try {
            JewishCalendar jc = new JewishCalendar(year, month, 1);
        } catch (IllegalArgumentException e) {
            if (month == 13) return 12;
        }
        return month;
    }

    public boolean isHebrew() {
        return hebrew;
    }

    public String createICSEvent() {
        String timestamp = Instant.now().toString().replaceAll("-|:|\\..+", "") + "Z";
        String summary = this.name + " (" + String.valueOf(this.jDate).replaceAll(",.+", "") + ")";
        String uid = Utils.hash();
        JewishCalendar jd = getDatesForYear(new JewishCalendar().getJewishYear());
        String start_date = "" + jd.getGregorianYear() +
                String.format("%02d", jd.getGregorianMonth() + 1) +
                String.format("%02d", jd.getGregorianDayOfMonth());
        jd.forward();
        String end_date = "" + jd.getGregorianYear() +
                String.format("%02d", jd.getGregorianMonth() + 1) +
                String.format("%02d", jd.getGregorianDayOfMonth());

        String evt = "BEGIN:VEVENT\n" +
                "DTSTAMP:" +
                timestamp + "\n" +
                "CATEGORIES:Holiday\n" +
                "CLASS:PUBLIC\n" +
                "SUMMARY:" +
                summary + "\n" +
                "DTSTART;VALUE=DATE:" +
                start_date + "\n" +
                "DTEND;VALUE=DATE:" +
                end_date + "\n" +
                "TRANSP:TRANSPARENT\n" +
                "X-MICROSOFT-CDO-BUSYSTATUS:FREE\n" +
                "UID:" + uid + "\n" +
                "BEGIN:VALARM\n" +
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:REMINDER\n" +
                "TRIGGER;RELATED=START:-PT12H\n" +
                "END:VALARM\n" +
                "END:VEVENT";

        System.out.println(evt);

        return evt;
    }

    private static final String begin_cal = "BEGIN:VCALENDAR\nVERSION:0.1\nPRODID:-//S Margulies - Tzeit\n" +
            "CALSCALE:GREGORIAN\nMETHOD:PUBLISH\nX-LOTUS-CHARSET:UTF-8\nX-PUBLISHED-TTL:PT7D\n" +
            "X-WR-CALNAME:Hebrew Dates for the years\n" +
            "X-WR-CALDESC:Yahrzeits + Anniversaries from www.hebcal.com";

    private static final String end_cal = "END:VCALENDAR";

    public void saveToFile(String fileName) throws IOException {
//        String outputLine = String.valueOf(getTableRows().get(0).size()) + "," + getTableRows().size() + ","/* + "\n"*/;
//
//        for (ArrayList<String> row : getTableRows()) {
//            for (int i = 0; i < row.size(); i++) {
//                outputLine += row.get(i) + ",";
//            }
//        }

//        PrintWriter saveFile = new PrintWriter(new FileWriter(fileName + ".ics"));
//        saveFile.println(outputLine);
//        saveFile.close();
    }

}
