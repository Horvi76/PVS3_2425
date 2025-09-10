package fileworks.serials;

import java.io.*;

public class MultipleSerial {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Coordinates c1 = new Coordinates(1,2,3);
        Coordinates c2 = new Coordinates(123,32,59);
        Coordinates c3 = new Coordinates(98,33,56);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("multiples.ser"));
        oos.writeObject(c1);
        oos.writeObject(c2);
        oos.writeObject(c3);
        oos.close();


        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("multiples.ser"));
        Coordinates d1 = (Coordinates) ois.readObject();
        Coordinates d2 = (Coordinates) ois.readObject();
        Coordinates d3 = (Coordinates) ois.readObject();
        ois.close();

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
    }
}
