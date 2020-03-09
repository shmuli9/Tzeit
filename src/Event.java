import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event {
    String name;
    int day;
    int month;
    int year;
    LocalDate date;
    boolean hebrew;

    public Event(String name, int day, int month, int year, boolean hebrew) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hebrew = hebrew;
        this.date = LocalDate.of(year, month, day);
    }

    @Override
    public String toString() {
        return "Event {" +
                "name='" + name + "' " +
                "date='" + this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
                + "'}";
    }

    public String getRawDate() {
        return this.date.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
//        return day + "/" + month + "/" + year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isHebrew() {
        return hebrew;
    }
}
