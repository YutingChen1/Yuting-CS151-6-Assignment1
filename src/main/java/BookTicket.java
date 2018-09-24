import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the booking function class.
 */
public class BookTicket {

    public void addGroup(Airplane plane, Scanner reader) {
        String nameOfGroup;
        String classPref;
        String groupMembers;

        do {
            System.out.println("Enter a group name: ");
            nameOfGroup = reader.nextLine();
            if (nameOfGroup.contains(",")) {
                System.out.println("Invalid. Group names cannot contain commas");
            }
        } while (nameOfGroup.contains(","));

        System.out.println("Enter the names of people in the group separated by commas: ");
        groupMembers = reader.nextLine();

        do {
            System.out.println("Enter a service class: [E]conomy or [F]irst: ");
            classPref = reader.nextLine();

            if ((classPref.charAt(0) != 'F') && (classPref.charAt(0) != 'E')) {
                System.out.println("Invalid. Please enter a valid service class.");
            }

        } while ((classPref.charAt(0) != 'F') && (classPref.charAt(0) != 'E'));


        int totalNumOfNames = 1;
        for (int x = 0; x < groupMembers.length(); x++) {
            if (groupMembers.charAt(x) == ',') {
                totalNumOfNames++;
            }
        }

        String [] arrayOfNames = groupMembers.split(",");

        ArrayList<Passenger> groupToAdd = new ArrayList<>();

        for (int x = 0; x < totalNumOfNames; x++) {
            arrayOfNames[x] = arrayOfNames[x].trim();
            groupToAdd.add(new Passenger(arrayOfNames[x], classPref, nameOfGroup));
        }

        String returnStr = plane.add_Group(groupToAdd);

        if (returnStr != null) {
            System.out.println(returnStr);
        } else {
            System.out.println("The service class is full. \n");
        }
    }

    public void addPassenger(Airplane plane, Scanner reader) {

        String name;
        String classPref;
        String seatPref;
        String returnStr;

        do {
            System.out.println("Enter your name: ");
            name = reader.nextLine();
            if (name.contains(",")) {
                System.out.println("Please enter your name without a comma");
            }

        } while(name.contains(","));

        do  {
            System.out.println("Please enter a desired class: (First or Economy)");
            classPref = reader.nextLine().toUpperCase();

            if (!classPref.equals("FIRST") && !classPref.equals("ECONOMY")) {
                System.out.println("Please enter a valid service class");
            }

        } while ((classPref.charAt(0) != 'F') && (classPref.charAt(0) != 'E'));

        do {
            if (classPref.substring(0, 1).equals("E")) {
                System.out.println("Enter a seat preference: [W]indow, [A]isle, or [C]enter");
            } else if (classPref.substring(0,1).equals("F")) {
                System.out.println("Enter a seat preference: [W]indow, or [A]isle");
            }

            seatPref = reader.nextLine().toUpperCase();

            Passenger p = new Passenger(name, classPref, null);
            returnStr = plane.addPassenger(p, seatPref);

            if (!returnStr.equals("")) {
                if (returnStr.equals("Sorry, there are no seats left in this plane!")) {
                    System.out.println("Sorry, plane is full!");
                } else {
                    System.out.println("Passenger seated at: " + returnStr + "\n");
                }
            } else {
                System.out.println("Sorry, no seats available for your given preference. Please try a different preference");
            }
        } while (returnStr.equals(""));

    }
}
