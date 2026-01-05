package threads.synced;

public class DeadlockShowcase {
    private static void unsafeTransfer(BankAccount from, BankAccount to, int amount) {
        //zamknu, od koho penize beru
        synchronized (from) {
            System.out.println("Thread : " + Thread.currentThread() +
                    " ma lock na ucet: " + from.getId());
            try {
                //probiha nejaky prevod
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(":(");
            }

            System.out.println("Thread : " + Thread.currentThread() +
                    " ma lock na ucet: " + to.getId());
            //tady nejspise zamrzne:
            synchronized (to){
                System.out.println("Thread : " + Thread.currentThread() +
                        " ma lock na ucet: " + to.getId());
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Presun penez od " + from.getId() + " k " + to.getId());
            }
        }
    }

    public static void main(String[] args) {
        BankAccount a = new BankAccount(1, 1000);
        BankAccount b = new BankAccount(2, 2000);

        Thread t1 = new Thread(() -> unsafeTransfer(a, b, 100));
        Thread t2 = new Thread(() -> unsafeTransfer(b, a, 200));

        t1.start();
        t2.start();
    }
}

class BankAccount {
    private final int id;
    private int balance;

    public BankAccount(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    void deposit(int amount) {
        balance += amount;
    }

    void withdraw(int amount) {
        balance -= amount;
    }
}
