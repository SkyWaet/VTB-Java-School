package slepenkov.gleb.hw6;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public interface IShop<T extends Product> {

    int countGoods();

    void addProducts(Product product);

    List<Product> filterBy(Predicate<Product> condition);

    Product[] filterByPrice(int price);

    Product[] filterByPattern(String pattern);

    Product[] filterByShelfLife(int limit);
}
