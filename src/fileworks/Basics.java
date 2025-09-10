package fileworks;

import java.io.File;
import java.util.Arrays;

public class Basics {


    static void tree(String filepath){
        File f = new File(filepath);
        if (f.exists()){
            if (f.isDirectory()){
                System.out.println(f.getPath());
                File[] files = f.listFiles();
                for (File file : files){
                    tree(file.getPath());
                }
            } else {
                System.out.println(f.getPath());
            }
        } else {
            System.out.println("Wrong tree call!");
        }
    }
    public static void main(String[] args) {
        File file = new File("src\\exams\\GreatTale.java");
        File folder = new File("src");

        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());

        System.out.println(file.isFile());
        System.out.println(file.isDirectory());

        System.out.println("Velikost: " + file.length());
        System.out.println("Velikost (kB): " + (file.length()/1000));

        System.out.println("Je? " + file.exists());

        System.out.println(file.getParent());

        System.out.println(folder.getPath());
        System.out.println(folder.isDirectory());
        System.out.println(Arrays.toString(folder.list()));
        System.out.println(Arrays.toString(folder.listFiles()));
    }
}
