package slepenkov.gleb.shopandproducts.errors;

import java.util.Random;


public class ErrorGenerator {

    private static final ErrorGenerator generator = new ErrorGenerator();

    public static Error generateError() {
        Random random = new Random(3);
        switch (random.nextInt()) {
            case 0:
                return new NotEnoughMoney();
            case 1:
                return new PurchaseRejection();
            default:
                return new ITBUg();
        }
    }

    public ErrorGenerator getGenerator() {
        return generator;
    }

    private ErrorGenerator() {

    }
}
