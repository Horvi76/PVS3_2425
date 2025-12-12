package threads.coords;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CounterSyncing {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        int threadIncreaseLimit = 100_000;

        CounterUnsafe counterUnsafe = new CounterUnsafe();

        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            exec.submit(() -> {
                for (int j = 0; j < threadIncreaseLimit; j++) {
                    counterUnsafe.increase();
                }
            });
        }

        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        int expected = threadIncreaseLimit * threadCount;
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + counterUnsafe.getCount());

        CounterSafe counterSafe = new CounterSafe();
        exec = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            exec.submit(() -> {
                for (int j = 0; j < threadIncreaseLimit; j++) {
                    counterSafe.increase();
                }
            });
        }

        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        expected = threadIncreaseLimit * threadCount;
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + counterSafe.getCount());
    }
}

class CounterUnsafe {
    private int count = 0;

    public void increase() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class CounterSafe {
    private int count = 0;

    public synchronized void increase() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}