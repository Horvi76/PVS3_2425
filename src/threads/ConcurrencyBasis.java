package threads;

public class ConcurrencyBasis {

    static int a = 0;
    static int b = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread aThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                a = 5;
                System.out.println(a);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread bThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                b=10;
                System.out.println(b);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        aThread.start();
        bThread.start();
        System.out.println(Thread.currentThread());
        System.out.println(Thread.activeCount());
        //takhle jinak ne
//        while (aThread.isAlive()){
//            System.out.println("Waiting...");
//        }

        //v tenhle bod, cekam, nez konci bThread
        bThread.join();
        //cekas, nez bude hotove B, az potom jdou nasledujici radky
        System.out.println(a);
        System.out.println(b);
        System.out.println("Happy end");
    }
}
