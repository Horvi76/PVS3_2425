package gui.booking;

public class Vacation {
    String applicant, phoneNum;
    Destinations dest;
    int days;
    boolean discount;

    public Vacation(String applicant, String phoneNum, int destOption, int days, boolean discount) {
        this.applicant = applicant;
        this.phoneNum = phoneNum;
        this.days = days;
        this.discount = discount;
    }

    public String[] getTableRow() {

        return null;
    }
}
enum Destinations{
    BEACH(0),
    CITY(1),
    MOUNTAINS(2);

    Destinations(int i) {
    }
}