package slepenkov.gleb.parallelprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DivisorsCounter {

    public static int findDivisors() throws ExecutionException, InterruptedException {
        Random generator = new Random();
        int[] container = new int[100];
        for (int i = 0; i < 100; i++) {
            container[i] = generator.nextInt(990) + 10;
        }

        System.out.println(Arrays.toString(container));
        ExecutorService pool = Executors.newWorkStealingPool();
        Map<Integer, Future<Integer>> futures = new HashMap<>();

        for (int number = 10; number < 100; number++) {
            int finalNumber = number;
            futures.put(number, pool.submit(() -> (int) Arrays.stream(container)
                    .filter(elem -> elem % finalNumber == 0)
                    .count()));
        }
        int result = 0;
        int currentMax = 0;
        for (var entry : futures.entrySet()) {
            var value = entry.getValue().get();
            if (value > currentMax) {
                result = entry.getKey();
                currentMax = value;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            System.out.println(DivisorsCounter.findDivisors());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
