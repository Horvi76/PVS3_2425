package gui.booking;

import java.io.Serial;
import java.io.Serializable;

public class Vacation implements Serializable {
    String applicant, phoneNum;
    Destinations dest;
    int days;
    boolean discount;
    @Serial
    private static final long serialVersionUID = 42L;

    public Vacation(String applicant, String phoneNum, int destOption, int days, boolean discount) {
        this.applicant = applicant;
        this.phoneNum = phoneNum;
        this.dest = Destinations.getDestByCode(destOption);
        this.days = days;
        this.discount = discount;
    }

    public String[] getTableRow() {
        return new String[]{
                applicant,
                phoneNum,
                dest.toString(),
                String.valueOf(days),
                discount ? "Yes" : "No"
        };
    }

    @Override
    public String toString() {
        return "applicant='" + applicant + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", dest=" + dest +
                ", days=" + days +
                ", discount=" + discount +
                '}';
    }
}
enum Destinations{
    BEACH("Beach"),
    CITY("City"),
    MOUNTAINS("Mountains");

    private final String destinationName;

    Destinations(String destinationName) {
        this.destinationName = destinationName;
    }

    public static Destinations getDestByCode(int code){
        return switch (code) {
            case 0 -> BEACH;
            case 2 -> MOUNTAINS;
            default -> CITY;
        };
    }

    @Override
    public String toString() {
        return destinationName;
    }
}