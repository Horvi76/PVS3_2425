package gui.booking;

public class Vacation {
    String name, phoneNumber;
    boolean isStudent;
    Destinations dest;
    int days;

    public Vacation(String name, String phoneNumber, boolean isStudent, Destinations dest, int days) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isStudent = isStudent;
        this.dest = dest;
        this.days = days;
    }
}
enum Destinations{
    BEACH("beach"),
    CITY("city"),
    MOUNTAINS("mountains");

    Destinations(String destination) {
    }

}
