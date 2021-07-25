package slepenkov.gleb.shopandproducts.shops;

import slepenkov.gleb.shopandproducts.exceptions.ProductLimitReachedException;
import slepenkov.gleb.shopandproducts.exceptions.ProductWithKeywordNotFound;
import slepenkov.gleb.shopandproducts.products.*;
import slepenkov.gleb.shopandproducts.search.SearchIndex;


import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public abstract class AbstractShop<PT extends Product> implements Serializable {
    private final String shopName;
    protected final SearchIndex<PT> index;


    public AbstractShop(String shopName, int limit) {
        this.shopName = shopName;
        this.index = new SearchIndex<>(limit);
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
        index.add(product);
    }

    public void printProducts() {
        System.out.println(index.printList());
    }

    public void deleteByKey(String key) {
        index.delete(key.toLowerCase());
    }

    public int countGoods() {
        return index.size();
    }

    public abstract List<PT> filterByPrice(int price);

    public List<PT> filterByPattern(String pattern) {
        return index.findByPattern(pattern.toLowerCase());
    }

    public List<PT> filterByKey(String pattern) {
        return index.find(pattern.toLowerCase());
    }

    public abstract List<PT> filterByShelfLife(int limit);

    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append(String.format("Магазин %s. Ассортимент: \n", shopName));
        buff.append(index.printList() + "\n");
        buff.append(index.printIndex());
        return buff.toString();
    }

}
