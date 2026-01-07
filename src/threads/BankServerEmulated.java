package threads;

import java.util.Scanner;

public class BankServerEmulated {

    private static boolean running = true;

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        Thread display = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Current balance: " + account.getBalance());
            }
            System.out.println("Vlakno [display] konci...");
        });

        Thread commandThread = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (running) {
                System.out.println("Zadej prikaz v podobe: /deposit <amount>; /withdraw <amount>; /quit");
                String input = sc.nextLine().trim();
                String[] splitInput = input.split(" ");
                if (splitInput.length > 2) {
                    System.out.println("Zadano spatny pocet parametru");
                } else {
                    switch (splitInput[0]) {
                        case "/deposit":
                            if (splitInput.length != 2) {
                                System.out.println("U deposit chybi castka!");
                            } else {
                                try {
                                    int amount = Integer.parseInt(splitInput[1]);
                                    System.out.println("Na ucet se uklada castka " + amount);
                                    account.deposit(amount);
                                } catch (NumberFormatException ne){
                                    System.out.println("Zadany spatny format cisla pro castku");
                                }
                            }
                            break;
                        case "/withdraw":
                            if (splitInput.length != 2) {
                                System.out.println("U withdraw chybi castka!");
                            } else {
                                try {
                                    int amount = Integer.parseInt(splitInput[1]);
                                    System.out.println("Z uctu se vybira castka " + amount);
                                    String out = account.withdraw(amount) ? "Castka odebrana" : "Nedostatecny zustatek na ucte";
                                    System.out.println(out);
                                } catch (NumberFormatException ne){
                                    System.out.println("Zadany spatny format cisla pro castku");
                                }
                            }
                            break;
                        case "/quit":
                            System.out.println("Ukoncuji program...");
                            running = false;
                            break;
                        default:
                            System.out.println("Neznamy prikaz...");
                            break;
                    }

                }
            }
        });
    }
}

class BankAccount {
    private int balance;


    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(int amount) {
        if (balance >= amount){
            balance -= amount;
            return true;
        }
        return false;
    }
}
