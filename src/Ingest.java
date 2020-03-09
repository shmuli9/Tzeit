import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Ingest {
    String[][] info;
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

            String[] meta = read.nextLine().split(",");
            int numRows = Integer.parseInt(meta[0]);
            int numCols = Integer.parseInt(meta[1]);

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

            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public static void main(String[] args) {
        Ingest in = new Ingest("");
    }

    public void initInfo(int rows, int cols) {
        this.info = new String[rows][cols];
    }

    public String[][] getInfo() {
        return info;
    }

    public void print2DArray(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
        }
    }
}
