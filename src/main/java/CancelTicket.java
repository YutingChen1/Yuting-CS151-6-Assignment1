import java.util.Scanner;

/**
 * This is the cancel function class.
 */
public class CancelTicket {

    public void cancel(Airplane plane, Scanner reader) {
        String read;
        String isReserved = "";

        System.out.println("Which cancellation would you like? [I]ndividual or [G]roup? ");
        read = reader.nextLine().toUpperCase();

        if (read.charAt(0) == 'G') {
            System.out.println("Enter the name of the group: ");
            read = reader.nextLine();
            isReserved = plane.cancelGroup(read);
        } else if (read.charAt(0) == 'I') {
            System.out.println("Enter your name: ");
            read = reader.nextLine();
            isReserved = plane.cancelReservation(read);
        }

        if (isReserved.equals("Your reservation has been cancelled.")) {
            System.out.println("Reservation cancelled for: " + read + ".\n");
        } else {
            System.out.println("Sorry. Cancelling failed.");
        }
    }
}
