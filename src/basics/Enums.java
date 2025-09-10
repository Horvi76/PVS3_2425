package basics;

public class Enums {
    public static final int FIXED_NUMBER = 5;


    public static void main(String[] args) {
        User basicUser = new User("Karel", 15, Access.BASE_USER);
    }
}
class SpecificLocation{
    double x, y;
    Geo region;
}
enum Geo {
    NORTH,
    SOUTH,
    EAST,
    WEST
}
enum Access{
    BASE_USER(3),
    SUPERVISOR(2),
    ADMIN(1);

    Access(int i) {
    }
} class User {
    String name;
    int id;
    Access accessLevel;

    public User(String name, int id, Access accessLevel) {
        this.name = name;
        this.id = id;
        this.accessLevel = accessLevel;
    }

    void grantAccess(User user, Access accessLevel){
        if (this.accessLevel == Access.ADMIN){
            user.accessLevel = accessLevel;
        } else {
            System.out.println("Forbidden");
        }
    }
}