package slepenkov.gleb.shopandproducts.shops;

import slepenkov.gleb.shopandproducts.exceptions.ProductLimitReachedException;
import slepenkov.gleb.shopandproducts.exceptions.ProductWithKeywordNotFound;
import slepenkov.gleb.shopandproducts.products.*;


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public abstract class AbstractShop<PT extends Product> implements Serializable {
    private final String shopName;
    protected final Set<PT> listOfGoods = new HashSet<>();
    private final int limit;

    public AbstractShop(String shopName, int limit) {
        this.shopName = shopName;
        this.limit = limit;
    }

    public void save() {
        try (var out = new ObjectOutputStream(new FileOutputStream(shopName + ".txt"))) {
            out.writeObject(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static AbstractShop loadFromFile(String filename) {
        try (var in = new ObjectInputStream(new FileInputStream(filename))) {
            return (AbstractShop) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
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
        List<PT> found = filterByPattern(key);
        if (found.size() == 0) {
            throw new ProductWithKeywordNotFound(key);
        }
        for (var elem : found) {
            listOfGoods.remove(elem);
        }
    }

    public int countGoods() {
        return listOfGoods.size();
    }

    protected <RT extends Product> List<RT> filterBy(Predicate<PT> condition) {
        List<RT> result = new ArrayList<>();
        for (var elem : listOfGoods) {
            if (condition.test(elem)) {
                result.add((RT) elem);
            }
        }
        return result;
    }

    public abstract List<PT> filterByPrice(int price);

    public abstract List<PT> filterByPattern(String pattern);

    public abstract List<PT> filterByShelfLife(int limit);

    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append(String.format("Магазин %s. Ассортимент: \n", shopName));
        for (var elem : listOfGoods) {
            buff.append(elem.toString() + "\n");
        }
        return buff.toString();
    }

}
