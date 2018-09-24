import java.util.ArrayList;

/**
 * This is the airplane class.
 */
public class Airplane {

    private static ArrayList<Seat> firstClassSeats;
    private static ArrayList<Seat> economySeats;

    private int economyRemainingSeats = 120;
    private int firstRemainingSeats = 8;

    public static int FILE_PARAMETER = 4;

    public Airplane() {

        firstClassSeats = populateFirst();

        economySeats = populateEconomy();

    }

    private static ArrayList<Seat> populateEconomy() {

        economySeats = new ArrayList<Seat>();

        String seat = "";

        for (int i = 10; i <= 30; i++) {

            for (int k = 1; k <= 6; k++) {
                seat += i;

                if (k == 6)
                    seat += "F";

                else if (k == 5)
                    seat += "E";

                else if (k == 4)
                    seat += "D";

                else if (k == 3)
                    seat += "C";

                else if (k == 2)
                    seat += "B";

                else if (k == 1)
                    seat += "A";

                economySeats.add(new Seat(seat, "E"));
                seat = "";
            }
        }

        return economySeats;

    }

    private static ArrayList<Seat> populateFirst() {

        firstClassSeats = new ArrayList<Seat>();

        String seat = "";

        for (int i = 1; i <= 2; i++) {

            for (int k = 1; k <= 4; k++) {
                seat += i;

                if (k == 4)
                    seat += "D";

                else if (k == 3)
                    seat += "C";

                else if (k == 2)
                    seat += "B";

                else if (k == 1)
                    seat += "A";

                //add the seats to the arrayList
                firstClassSeats.add(new Seat(seat, "F"));
                //reset the seat
                seat = "";
            }
        }

        return firstClassSeats;

    }

    public void addInfo(Passenger p, int seat) {

        if (p.getClassPref().equalsIgnoreCase("E")) {

            economySeats.get(seat).setPassenger(p);
            economyRemainingSeats--;

        } else if (p.getClassPref().equalsIgnoreCase("F")) {

            firstClassSeats.get(seat).setPassenger(p);
            firstRemainingSeats--;

        }

    }

    public String getPlaneManifest(String classPref) {
        String returnManifest = "";
        int x = 0;

        if (classPref.equalsIgnoreCase("Economy")) {

            returnManifest += "Economy\n";

            for (x = 0; x < 120; x++) {

                if (economySeats.get(x).getPassenger() != null) {

                    returnManifest += economySeats.get(x).getSeatNumber() + ": ";
                    returnManifest += economySeats.get(x).getPassenger().getPassengerName() + "\n";
                }
            }

        } else if (classPref.equalsIgnoreCase("First")) {

            returnManifest += "First\n";

            for (x = 0; x < 8; x++) {

                if (firstClassSeats.get(x).getPassenger() != null) {

                    returnManifest += firstClassSeats.get(x).getSeatNumber() + ": ";
                    returnManifest += firstClassSeats.get(x).getPassenger().getPassengerName() + "\n";

                }
            }
        }

        return returnManifest;
    }

    public String getAvail(String classPref) {
        boolean emptySeat;
        String returnStr = "";
        int x = 0;

        if (classPref.equalsIgnoreCase("First")) {

            returnStr = "First:";

            for (int row = 1; row <= 2; row++) {

                emptySeat = false;

                //loop over the arrayList and find empty seats
                for (int column = 1; column <= 4; column++) {

                    if (firstClassSeats.get(x).getPassenger() == null) {

                        if (!emptySeat) {

                            returnStr += ("\n" + row + ": ");
                            emptySeat = true;

                        } else {

                            returnStr += ",";
                        }

                        returnStr += firstClassSeats.get(x).getSeatNumber().charAt(1); //adds availability to the return string
                    }

                    x++;
                }
            }

        } else if (classPref.equalsIgnoreCase("Economy")) {

            returnStr += "Economy:";
            x = 0;

            for (int row = 10; row < 30; row++) {

                emptySeat = false;

                for (int column = 1; column <= 6; column++) {

                    if (economySeats.get(x).getPassenger() == null) {

                        if (emptySeat == false) {

                            returnStr += ("\n" + row + ": ");
                            emptySeat = true;

                        } else {

                            returnStr += ",";

                        }

                        returnStr += economySeats.get(x).getSeatNumber().charAt(2); //adds availability to the return string
                    }

                    x++;
                }
            }

            returnStr += "\n";
        }

        return returnStr;
    }

    public String addPassenger(Passenger p, String seatPref) {
        int x;

        if (p.getClassPref().equalsIgnoreCase("Economy")) {
            if (economyRemainingSeats == 0) {

                return "Sorry, there are no seats left in this plane!"; //if no seats are left in given class

            }

            for (x = 0; x < 120; x++) {

                if (economySeats.get(x).getPassenger() == null && economySeats.get(x).getSeatType().equals(seatPref)) {
                    economyRemainingSeats--;
                    economySeats.get(x).setPassenger(p);

                    return "Seat Given: " + economySeats.get(x).getSeatNumber();

                }
            }

            return "Sorry no seat available for your given preference";

        } else if (p.getClassPref().equalsIgnoreCase("First")) {
            if (firstRemainingSeats == 0) {

                return "Sorry, there are no seats left in this plane!";

            }

            for (x = 0; x < 8; x++) {

                if (firstClassSeats.get(x).getPassenger() == null && firstClassSeats.get(x).getSeatType().equalsIgnoreCase(seatPref)) {
                    firstRemainingSeats--;
                    firstClassSeats.get(x).setPassenger(p);

                    return "Seat Given: " + firstClassSeats.get(x).getSeatNumber();
                }
            }

            return "Sorry no seat available for your given preference.";
        }

        return "Sorry you entered a wrong class selection.";
    }

    public String add_Group(ArrayList<Passenger> groupToAdd) {

        int a, b;
        int c = 0;

        int maxEmpty = 0;
        int adjacentEmpty = 0;

        ArrayList<Integer> list = new ArrayList<Integer>();

        String whereGroupSeated = "";

        if (groupToAdd.get(0).getClassPref().equals("Economy") && economyRemainingSeats <= groupToAdd.size()) {

            return "Sorry, no seats available.";

        } else if (groupToAdd.get(0).getClassPref().equalsIgnoreCase("First") && firstRemainingSeats <= groupToAdd.size()) {

            return "Sorry, no seats available!";

        }

        if (groupToAdd.get(0).getClassPref().equalsIgnoreCase(("Economy"))) {

            for (a = groupToAdd.size(); a > 0; a--) {

                for (b = 0; b < 120; b++) {

                    if ((b % 6 == 0) || (b == 120 - 1)) {

                        if (adjacentEmpty > maxEmpty) {
                            maxEmpty = adjacentEmpty;

                            for (c = maxEmpty; c >= 1; c--)
                                list.add(Math.abs(c - b));

                        }

                        if (maxEmpty >= a) {

                            while (!list.isEmpty() && !groupToAdd.isEmpty()) {

                                economySeats.get(list.get(0)).setPassenger(groupToAdd.get(0));
                                whereGroupSeated += (groupToAdd.get(0).getPassengerName() + " is seated at: " + economySeats.get(list.get(0)).getSeatNumber() + "\n");
                                list.remove(0);
                                groupToAdd.remove(0);

                            }
                        }

                        maxEmpty = 0;
                        adjacentEmpty = 0;
                        list.clear();

                    }

                    if (economySeats.get(b).getPassenger() != null) {

                        if (adjacentEmpty > maxEmpty) {

                            list.clear(); //clear the list
                            maxEmpty = adjacentEmpty;

                            for (c = 1; c <= maxEmpty; c++)
                                list.add(b - c);
                        }

                        adjacentEmpty = 0;

                    } else {

                        adjacentEmpty++;

                    }
                }
            }

        } else if (groupToAdd.get(0).getClassPref().equalsIgnoreCase("First")) {

            for (a = groupToAdd.size(); a > 0; a--) {

                for (b = 0; b < 8; b++) {

                    if ((b == 8 - 1) || ((b % 4) == 0)) {

                        if (adjacentEmpty > maxEmpty) {
                            maxEmpty = adjacentEmpty;

                            for (c = maxEmpty; c >= 1; c--)
                                list.add(Math.abs(c - b));

                        }
                        if (maxEmpty >= a) {

                            while (!list.isEmpty() && !groupToAdd.isEmpty()) {

                                firstClassSeats.get(list.get(0)).setPassenger(groupToAdd.get(0));
                                whereGroupSeated += (groupToAdd.get(0).getPassengerName() + " is seated at: " + firstClassSeats.get(list.get(0)).getSeatNumber() + "\n");
                                groupToAdd.remove(0);
                                list.remove(0);

                            }
                        }

                        list.clear();
                        maxEmpty = 0;
                        adjacentEmpty = 0;

                    }

                    if (firstClassSeats.get(b).getPassenger() != null) {
                        if (adjacentEmpty > maxEmpty) {

                            list.clear();
                            maxEmpty = adjacentEmpty;

                            for (c = 1; c <= maxEmpty; c++) {
                                list.add(b - c);
                            }
                        }
                        adjacentEmpty = 0;
                    } else {
                        adjacentEmpty++;
                    }
                }
            }
        }

        return whereGroupSeated;
    }

    public String cancelReservation(String name) {

        boolean isReserved = false;
        String returnStr = "";

        for (int x = 0; x < 8; x++) {

            if (firstClassSeats.get(x).getPassenger() != null) {

                if (name.equalsIgnoreCase(firstClassSeats.get(x).getPassenger().getPassengerName())) {
                    firstRemainingSeats++;
                    firstClassSeats.get(x).setPassenger(null);
                    isReserved = true;

                }
            }
        }

        for (int x = 0; x < 120; x++) {

            if (economySeats.get(x).getPassenger() != null) {

                if (name.equalsIgnoreCase(economySeats.get(x).getPassenger().getPassengerName())) {

                    economyRemainingSeats++;
                    economySeats.get(x).setPassenger(null);
                    isReserved = true;

                }
            }
        }

        if (isReserved) {
            returnStr = "Your reservation has been cancelled.";
        } else {
            returnStr = "Sorry, reservation not found";
        }
        return returnStr;
    }

    public String cancelGroup(String groupName) {
        String returnStr = "";
        boolean isReserved = false;

        for (int i = 0; i < 120; i++) {

            if (economySeats.get(i).getPassenger() != null) {
                if (groupName.equalsIgnoreCase(economySeats.get(i).getPassenger().getGroup())) {
                    economySeats.get(i).setPassenger(null);
                    economyRemainingSeats++;
                    isReserved = true;
                }
            }
        }

        for (int i = 0; i < 8; i++) {

            if (firstClassSeats.get(i).getPassenger() != null) {

                if (groupName.equalsIgnoreCase(firstClassSeats.get(i).getPassenger().getGroup())) {

                    firstClassSeats.get(i).setPassenger(null);
                    firstRemainingSeats++;
                    isReserved = true;

                }
            }
        }

        if (isReserved) {
            returnStr = "Your reservation has been cancelled.";
        } else {
            returnStr = "Sorry, reservation not found";
        }

        return returnStr;
    }

    public String getFlightInformation() {

        String returnStr = "";

        for (int x = 0; x < 8; x++) {

            if (firstClassSeats.get(x).getPassenger() != null) {

                returnStr += (firstClassSeats.get(x).getSeatNumber() + ", ");

                if (firstClassSeats.get(x).getPassenger().getGroup() == null) {
                    returnStr += ("I");

                } else {

                    returnStr += ("G, ");
                    returnStr += (firstClassSeats.get(x).getPassenger().getGroup());
                }

                returnStr += (", " + firstClassSeats.get(x).getPassenger().getPassengerName());

                returnStr += "\n";
            }
        }

        for (int x = 0; x < 120; x++) {

            if (economySeats.get(x).getPassenger() != null) {
                returnStr += (economySeats.get(x).getSeatNumber() + ", ");

                if (economySeats.get(x).getPassenger().getGroup() == null) {

                    returnStr += ("I");
                } else {

                    returnStr += ("G, ");
                    returnStr += (economySeats.get(x).getPassenger().getGroup());
                }

                returnStr += (", " + economySeats.get(x).getPassenger().getPassengerName());

                returnStr += "\n";
            }
        }
        return returnStr;
    }
}