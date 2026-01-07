package threads.synced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Banking {

    public static void unsafeTransfer(BankAccountUnsafe from, BankAccountUnsafe to, int amount){
        if (from.getBalance() >= amount){
            from.withdraw(amount);
            to.deposit(amount);
        }
    }

    public static void safeTransfer(BankAccountSafe from, BankAccountSafe to, int amount){
        if (from.getBalance() >= amount){
            from.withdraw(amount);
            to.deposit(amount);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccountUnsafe unsafeA = new BankAccountUnsafe(1000);
        BankAccountUnsafe unsafeB = new BankAccountUnsafe(2000);

        System.out.println("A: " + unsafeA.getBalance());
        System.out.println("B: " + unsafeB.getBalance());

        int threadCount = 5;
        ExecutorService executors = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executors.submit(() -> {
                for (int j = 0; j < 1000000; j++) {
                    if (Math.random() > 0.5){
                        unsafeTransfer(unsafeA, unsafeB, 1);
                    } else {
                        unsafeTransfer(unsafeB, unsafeA, 1);
                    }
                }
            });
        }

        executors.shutdown();
        executors.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        System.out.println("A: " + unsafeA.getBalance());
        System.out.println("B: " + unsafeB.getBalance());
        System.out.println("Total: " + (unsafeB.getBalance() + unsafeA.getBalance()));
        System.out.println("_____");

        BankAccountSafe safeA = new BankAccountSafe(1000);
        BankAccountSafe safeB = new BankAccountSafe(2000);
        System.out.println("A: " + safeA.getBalance());
        System.out.println("B: " + safeB.getBalance());

        executors = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executors.submit(() -> {
                for (int j = 0; j < 10000; j++) {
                    if (Math.random() > 0.5){
                        safeTransfer(safeA, safeB, 1);
                    } else {
                        safeTransfer(safeB, safeA, 1);
                    }
                }
            });
        }

        executors.shutdown();
        executors.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        System.out.println("A: " + safeA.getBalance());
        System.out.println("B: " + safeB.getBalance());
        System.out.println("Total: " + (safeB.getBalance() + safeA.getBalance()));

    }
}
class BankAccountUnsafe {
    private int balance;

    public BankAccountUnsafe(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }

    public void deposit(int amount){
        balance+=amount;
    }
    public void withdraw(int amount){
        balance-=amount;
    }
}
class BankAccountSafe{
    private int balance;


    public BankAccountSafe(int balance) {
        this.balance = balance;
    }

    public synchronized int getBalance() {
        return balance;
    }
    public synchronized void deposit(int amount){
        balance+=amount;
    }
    public synchronized void withdraw(int amount){
        balance-=amount;
    }
}
