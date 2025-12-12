package threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Basics {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("neco se stane za 3 vteriny");
        Thread.sleep(3000);
        System.out.println("Neco");
        System.out.println(Thread.activeCount());
        System.out.println(Thread.currentThread());

//        Thread a = new Thread(); samotne ne

        FirstThread thread = new FirstThread();
        thread.setName("Prvni");
        thread.start();
        System.out.println(Thread.activeCount());
        FirstThread another = new FirstThread();
        another.setName("necoDalsiho");
//        another.setPriority(9);
        another.start();

        IThread th = new IThread();
        Thread implemented = new Thread(th);
        implemented.start();
        System.out.println("Happy end :)");
    }
}
class FirstThread extends Thread{
    @Override
    public void run() {
        System.out.println(getName());
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(getName()+".txt")));
            for (int i = 1; i <= 100000; i++) {
                pw.println(getName() + ": " + i);
            }
            pw.close();
        } catch (IOException e){
            System.out.println(":(");
        }
        System.out.println(getName() + " finished");
    }
}
class IThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(Thread.currentThread().getName()+".txt")));
            for (int i = 1; i <= 100000; i++) {
                pw.println(Thread.currentThread().getName() + ": " + i);
            }
            pw.close();
        } catch (IOException e){
            System.out.println(":(");
        }
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}