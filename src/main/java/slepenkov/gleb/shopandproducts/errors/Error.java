package slepenkov.gleb.shopandproducts.errors;

import java.util.Random;

public interface Error {
    default boolean fix() {
        Random random = new Random();
        int digit = random.nextInt(1000);
        return digit % 13 == 0;
    }

    String getMessage();
}
