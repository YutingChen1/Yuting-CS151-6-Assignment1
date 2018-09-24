import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the main system class.
 */

public class Reservation {

    Airplane plane;
    BookTicket bookTicket;
    CancelTicket cancelTicket;

    Reservation(){
        plane = new Airplane();
        bookTicket = new BookTicket();
        cancelTicket = new CancelTicket();
    }

    public void starter(String[] args){

        if(args.length == 1) {
            Util.readFile (plane, args[0]);
        }
        Scanner in = new Scanner(System.in);
        String read = "";

        while (true) {

            System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart,\n" +
                    "Print [M]anifest, [Q]uit ");
            System.out.print("Please enter a letter as shown above: ");
            read = in.nextLine().toUpperCase();

            if(read.length() == 0) {
                System.out.println("Invalid input. Please try again.");
            } else if (read.charAt(0) == 'G') {
                bookTicket.addGroup(plane, in);
            } else if (read.charAt(0) == 'P') {
                bookTicket.addPassenger(plane, in);
            } else if (read.charAt(0) == 'A') {
                System.out.print("Economy or First?");
                read = in.nextLine();
                if(read.equalsIgnoreCase("Economy")) {
                    System.out.println(plane.getAvail("Economy"));
                } else if (read.equalsIgnoreCase("First")) {
                    System.out.println(plane.getAvail("First"));
                } else {
                    System.out.println("Invalid input.");
                }
            } else if (read.charAt(0) == 'C') {
                cancelTicket.cancel(plane, in);
            } else if (read.charAt(0) == 'M') {
                Util.getManifest(plane, in);
            } else if (read.charAt(0) == 'Q') {
                if (args.length == 1) {
                    Util.saveInformation(plane, args[0]);
                } else {
                    System.out.println("Unable to save flight data.");
                }
                System.exit(0);
            } else {
                System.out.println("Please enter a valid input.");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Reservation reservation = new Reservation();
        reservation.starter(args);
    }
}