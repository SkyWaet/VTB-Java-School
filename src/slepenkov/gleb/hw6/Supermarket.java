package slepenkov.gleb.hw6;

import slepenkov.gleb.hw5.Book;
import slepenkov.gleb.hw5.Soap;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Supermarket implements CakeShop, BookShop, SoapShop {
    private final List<Product> listOfGoods = new ArrayList<>();

    @Override
    public int countGoods() {
        return listOfGoods.size();
    }

    @Override
    public void addProducts(Product product) {
        listOfGoods.add(product);
    }

    @Override
    public List<Product> filterBy(Predicate<Product> condition) {
        List<Product> result = new ArrayList<>();
        for (var elem : listOfGoods) {
            if (condition.test(elem)) {
                result.add(elem);
            }
        }
        return result;
    }

    @Override
    public Product[] filterByPrice(int price) {
        return filterBy(elem -> elem.getPrice() < price).toArray(Product[]::new);
    }

    @Override
    public Product[] filterByPattern(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();
        return filterBy(elem -> elem.getName().toLowerCase().contains(lowerCasePattern))
                .toArray(Product[]::new);
    }

    @Override
    public Product[] filterByShelfLife(int limit) {
        return filterBy(elem -> !(elem instanceof LimitedShelfLifeProduct) ||
                ((LimitedShelfLifeProduct) elem).getShelfLife() >= limit)
                .toArray(Product[]::new);
    }

}
