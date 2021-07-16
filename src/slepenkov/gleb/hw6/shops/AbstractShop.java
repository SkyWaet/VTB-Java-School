package slepenkov.gleb.hw6.shops;

import slepenkov.gleb.hw6.exceptions.ProductLimitReachedException;
import slepenkov.gleb.hw6.exceptions.ProductWithKeywordNotFound;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public abstract class AbstractShop<PT> {
    protected final Set<PT> listOfGoods = new HashSet<>();
    private final int limit;

    public AbstractShop(int limit) {
        this.limit = limit;
    }

    public void addProducts(PT product) {
        if (listOfGoods.size() < limit) {
            listOfGoods.add(product);
        } else {
            throw new ProductLimitReachedException();
        }
    }

    public void printProducts() {
        for (var elem : listOfGoods) {
            System.out.println(elem);
        }
    }

    public void deleteByKey(String key) {
        PT[] found = filterByPattern(key);
        if (found.length == 0) {
            throw new ProductWithKeywordNotFound(key);
        }
        for (var elem : found) {
            listOfGoods.remove(elem);
        }
    }

    public int countGoods() {
        return listOfGoods.size();
    }

    protected <RT> RT[] filterBy(Predicate<PT> condition, IntFunction<RT[]> constructor) {
        List<PT> result = new ArrayList<>();
        for (var elem : listOfGoods) {
            if (condition.test(elem)) {
                result.add(elem);
            }
        }
        return result.toArray(constructor.apply(result.size()));
    }

    public abstract PT[] filterByPrice(int price);

    public abstract PT[] filterByPattern(String pattern);

    public abstract PT[] filterByShelfLife(int limit);


}
