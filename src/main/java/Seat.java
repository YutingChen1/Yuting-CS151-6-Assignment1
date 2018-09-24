/**
 * This is the seat class.
 */
public class Seat {

    private String seatType;
    private String seatNumber;
    private Passenger passenger;

    public Seat (String number, String classPref) {

        passenger = null;
        this.seatNumber = number;

        if (classPref.equalsIgnoreCase("E")) {
            if (number.contains ("D") || number.contains("C")) {
                this.seatType = "A";
            } else if (number.contains("A") || number.contains("F")) {
                this.seatType = "W";
            } else  {
                this.seatType = "C";
            }
        } else if (classPref.equalsIgnoreCase("F")) {
            if (number.contains("D") || number.contains("A")) {
                this.seatType = "W";
            } else {
                this.seatType = "A";
            }
        }
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger occupant) {
        passenger = occupant;
    }
}