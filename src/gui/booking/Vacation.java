package gui.booking;

public class Vacation {
    String applicant, phoneNum;
    Destinations dest;
    int days;
    boolean discount;

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