package slepenkov.gleb.hw6;

import slepenkov.gleb.hw5.Book;
import slepenkov.gleb.hw5.Product;
import slepenkov.gleb.hw5.Soap;

import java.util.List;
import java.util.function.IntFunction;

public interface CakeShop extends IShop {

    default Cake[] getAllCakes() {
        return filterBy(elem -> elem instanceof Cake).toArray(Cake[]::new);
    }
}
