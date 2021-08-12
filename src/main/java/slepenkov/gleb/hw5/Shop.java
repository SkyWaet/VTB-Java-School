package slepenkov.gleb.hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Shop {
    private final ArrayList<Product> listOfGoods = new ArrayList<>();

    public void addProducts(Product product) {
        listOfGoods.add(product);
    }

    public int countGoods() {
        return listOfGoods.size();
    }

    private <T extends Product> T[] filterBy(Predicate<Product> condition, IntFunction<T[]> constructor) {
        List<Product> result = new ArrayList<>();
        for (var elem : listOfGoods) {
            if (condition.test(elem)) {
                result.add(elem);
            }
        }
        return result.toArray(constructor.apply(result.size()));
    }

    public Product[] filterByPrice(int price) {
        return filterBy(elem -> elem.getPrice() < price, Product[]::new);
    }

    public Product[] filterByPattern(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();
        return filterBy(elem -> elem.getName().toLowerCase().contains(lowerCasePattern)
                , Product[]::new);
    }

    public Product[] filterByShelfLife(int limit) {
        return filterBy(elem -> (elem.shelfLife >= limit) ||
                (elem instanceof Book) || (elem instanceof Soap), Product[]::new);
    }

    public Book[] getAllBooks() {
        return filterBy(elem -> elem instanceof Book, Book[]::new);
    }

    public Soap[] getAllSoap() {
        return filterBy(elem -> elem instanceof Soap, Soap[]::new);
    }

    public Cake[] getAllCakes() {
        return filterBy(elem -> elem instanceof Cake, Cake[]::new);
    }
}
