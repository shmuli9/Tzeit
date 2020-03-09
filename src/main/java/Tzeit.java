public class Tzeit {

    public static void main(String[] args) {
        //    read in from file (csv)
        //    convert dates
        //    output to ics (or similar) for next ... years
        Ingest in = new Ingest("");
        Calendar cal = new Calendar(in);

        cal.out("ics", 20);
    }

}
