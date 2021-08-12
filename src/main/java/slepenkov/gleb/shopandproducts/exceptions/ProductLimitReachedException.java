package slepenkov.gleb.shopandproducts.exceptions;

/**
 * Данное исключение является непроверяемым, т.к. причина его возникновения -
 * логика написания программы, а не некая системная ошибка вроде тех, которые
 * возникают при операциях ввода-вывода.
 */
public class ProductLimitReachedException extends RuntimeException {
    public ProductLimitReachedException() {
        super("Невозможно добавить новый товар: достигнут лимит хранения.");
    }
}
