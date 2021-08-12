package slepenkov.gleb.shopandproducts.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Товары, удовлетворяющие условию, не найдены.");
    }
}
