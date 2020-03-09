public class Tzeit {

    public static void main(String[] args) {
        //    read in from file (csv)
        Ingest in = new Ingest("");
        //    convert dates
        LocalCalendar cal = new LocalCalendar(in);
        //    output to ics (or similar) for next ... years
        cal.out("ics", 20);
    }

}
