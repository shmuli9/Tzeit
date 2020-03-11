import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Ingest {
    ArrayList<Event> events = new ArrayList<Event>();

    public Ingest(String filename) {
        if (filename.equals("")) {
            filename = "C:\\Users\\shmul\\Coding\\tzeit\\csv\\dates.csv";
        }
        System.out.println("Reading from: " + filename);

        try {
            File file = new File(filename);
            Scanner read = new Scanner(file);
            read.useDelimiter(",");

            for (int i = 0; read.hasNextLine(); i++) {
                String line = read.nextLine();
                Scanner read2 = new Scanner(line);
                read2.useDelimiter(",");

                String name = read2.next();
                int day = read2.nextInt();
                int month = read2.nextInt();
                int year = read2.nextInt();
                boolean heb = read2.nextBoolean();

                events.add(new Event(name, day, month, year, heb));
            }

            for (Event e : events) {
                System.out.println(e);
            }
            for (Event e : events) {
//                e.hebFallsOn(10);
                e.createICSEvent();
            }


            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

}
