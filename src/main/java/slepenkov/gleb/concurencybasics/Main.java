package slepenkov.gleb.concurencybasics;

import java.util.Arrays;
import java.util.Random;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        MyInteger[] inputArray = new MyInteger[30];
        Random generator = new Random();
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = new MyInteger(generator.nextInt(90) + 10);
        }
        ArrayFiller filler = new ArrayFiller(inputArray);
        MyInteger[] result = filler.fillArray(2, 3, 5);
        System.out.println(Arrays.toString(inputArray));
        System.out.println(Arrays.toString(result));

    }
}
