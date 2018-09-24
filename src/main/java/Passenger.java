/**
 * This is the passenger class.
 */
public class Passenger {

    private String passengerName;
    private String classPref;
    private String groupMembers;

    public Passenger(String name, String classPreference, String group) {
        this.passengerName = name;
        this.classPref = classPreference;
        this.groupMembers = group;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getClassPref() {
        return classPref;
    }

    public String getGroup() {
        return groupMembers;
    }
}