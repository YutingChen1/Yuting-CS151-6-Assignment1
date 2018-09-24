/**
 * This is the utility class.
 */
import java.io.*;
import java.util.Scanner;

public class Util {
    public static void readFile(Airplane plane, String string) {
        File myFile = new File(string);

        if (myFile.exists()) {
            String read;
            String theGroup;
            int seatNumber;
            String[] dataPerLine = new String[4];

            try {
                Scanner readTheFile = new Scanner(myFile);
                readTheFile.nextLine();

                while (readTheFile.hasNextLine()) {
                    read = readTheFile.nextLine();
                    dataPerLine = read.split(",");
                    seatNumber = Integer.parseInt(dataPerLine[0].substring(0, 1));

                    if (dataPerLine.length == plane.FILE_PARAMETER-1) {
                        theGroup = null;
                    } else {
                        theGroup = dataPerLine[plane.FILE_PARAMETER-1];
                    }
                    plane.addInfo(new Passenger(dataPerLine[2], dataPerLine[0], theGroup), seatNumber);
                }
            } catch (FileNotFoundException notFound) {
                System.out.println("File not found!");
            }
        }
    }

    public static void getManifest(Airplane plane, Scanner reader) {
        String classPref;

        System.out.println("Which class' manifest would you like? ");
        classPref = reader.nextLine();

        System.out.println(plane.getPlaneManifest(classPref));
    }

    public static void saveInformation(Airplane plane, String file) {

        File output = new File(file);
        try {

            PrintWriter saver = new PrintWriter(new FileWriter(file));
            saver.println("First 1-2, Left: A-B, Right: C-D; Economy 10-29, Left: A-C, Right: D-F");

            String flightInformation = plane.getFlightInformation();
            saver.print(flightInformation);
            saver.close();

        }
        catch (IOException e) {
            System.out.println("Sorry, file not found!");
        }

        System.out.println("\n Flight data saved to: " + file);

    }
}
