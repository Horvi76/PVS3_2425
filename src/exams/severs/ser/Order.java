package exams.severs.ser;
import java.io.Serializable;

public record Order(String itemName, int qty) implements Serializable {
    public Order {
        if (itemName == null) itemName = "";
    }
}

