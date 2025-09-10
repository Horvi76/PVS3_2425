package fileworks.serials;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionSerialize {



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Coordinates> coordinates = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            coordinates.add(new Coordinates(r.nextInt(10000), r.nextInt(10000), r.nextInt(10000)));
        }

        System.out.println(coordinates);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("coordsList.ser"));
        oos.writeObject(coordinates);
        oos.close();

        List<Coordinates> deserializedCoordinates = null;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("coordsList.ser"));
        deserializedCoordinates = (List<Coordinates>) ois.readObject();
        ois.close();
        System.out.println(deserializedCoordinates);
        System.out.println(deserializedCoordinates.size() == coordinates.size());
    }
}
