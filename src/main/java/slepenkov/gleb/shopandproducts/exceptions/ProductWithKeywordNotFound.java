package slepenkov.gleb.shopandproducts.exceptions;

/**
 * Данное исключение является непроверяемым, т.к. причина его возникновения -
 * логика написания программы, а не некая системная ошибка вроде тех, которые
 * возникают при операциях ввода-вывода.
 */
public class ProductWithKeywordNotFound extends RuntimeException {

    public ProductWithKeywordNotFound(String keyword) {
        super(String.format("Товар с ключевым словом \"%s\" в названии не найден.", keyword));
    }
}
