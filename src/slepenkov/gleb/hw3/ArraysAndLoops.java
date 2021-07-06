package slepenkov.gleb.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArraysAndLoops {
    public static void main(String[] args) {

        int[] sevens = new int[10];
        Arrays.fill(sevens, 7);
        System.out.println("Массив семерок: " + Arrays.toString(sevens));

        sevens[0] = 0;
        for (int i = 1; i < sevens.length; i++) {
            sevens[i] = 2 * i - 1;
        }
        System.out.println("Массив с элементами a[i] = 2i-1: " + Arrays.toString(sevens));

        for (int i = 0; i < sevens.length; i++) {
            sevens[i] = new Random().nextInt(10);
        }
        System.out.println("Массив случайных значений: " + Arrays.toString(sevens));

        for (int i = sevens.length - 1, j = 0; j < i; i--, j++) {
            int temp = sevens[i];
            sevens[i] = sevens[j];
            sevens[j] = temp;
        }
        System.out.println("Обращенный массив: " +
                "" + Arrays.toString(sevens));

        int countElems = 0;
        for (var elem : sevens) {
            if (elem < 5) {
                countElems++;
            }
        }
        int[] newArr = new int[countElems];
        int index = 0;
        for (var elem : sevens) {
            if (elem < 5) {
                newArr[index] = elem;
                index++;
            }
        }
        System.out.println("Отфильтрованный массив: " + Arrays.toString(newArr));

    }
}
