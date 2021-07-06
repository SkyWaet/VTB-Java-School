package slepenkov.gleb.hw6;

public interface BookShop extends IShop {
    default Book[] getAllBooks() {
        return filterBy(elem -> elem instanceof Book).toArray(Book[]::new);
    }
}
