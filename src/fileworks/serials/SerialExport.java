package fileworks.serials;

import java.io.*;

public class SerialExport {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //serialization
        Coordinates coords = new Coordinates(5,Integer.MAX_VALUE,20);
        System.out.println("Exportuju: " + coords);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("single_coord.ser"));
        oos.writeObject(coords);
        oos.close();

        //deserialization
        Coordinates deserialized = null;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("single_coord.ser"));
        deserialized = (Coordinates) ois.readObject();

        System.out.println("Importovano: " + deserialized);
    }
}
