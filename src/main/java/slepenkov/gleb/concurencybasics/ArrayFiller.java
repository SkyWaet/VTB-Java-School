package slepenkov.gleb.concurencybasics;

public class ArrayFiller {
    private final MyInteger[] inputArray;
    private final MyInteger[] target;

    public ArrayFiller(MyInteger[] inputArray) {
        this.inputArray = inputArray;
        this.target = new MyInteger[inputArray.length];
        for (int i = 0; i < target.length; i++) {
            target[i] = new MyInteger(0);
        }
    }

    private int findMaxForSkip(int skip) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < inputArray.length; i += skip) {
            if (inputArray[i].getValue() > max) {
                max = inputArray[i].getValue();
            }
        }
        return max;
    }

    private boolean dividesByhOtherSkip(int index, int skip, int[] other) {
        for (var elem : other) {
            if (elem != skip && index % elem == 0) {
                return true;
            }
        }
        return false;
    }

    private Runnable taskFactory(int skip, int[] other) {
        return () -> {
            final int max = findMaxForSkip(skip);
            for (int i = 0; i < inputArray.length; i += skip) {
                if (dividesByhOtherSkip(i, skip, other)) {
                    synchronized (ArrayFiller.class) {
                        target[i].setValue(Math.max(max, target[i].getValue()));
                    }
                } else {
                    target[i].setValue(max);
                }
            }
        };
    }

    public MyInteger[] fillArray(int... skips) throws InterruptedException {
        Thread[] threads = new Thread[skips.length];
        for (int i = 0; i < skips.length; i++) {
            threads[i] = new Thread(taskFactory(skips[i], skips));
            threads[i].start();
        }
        for (var thread : threads) {
            thread.join();
        }
        return target;
    }
}
