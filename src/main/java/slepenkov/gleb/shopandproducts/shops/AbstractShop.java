package slepenkov.gleb.shopandproducts.shops;

import slepenkov.gleb.shopandproducts.hr.HRService;
import slepenkov.gleb.shopandproducts.hr.MyHR;
import slepenkov.gleb.shopandproducts.products.Product;
import slepenkov.gleb.shopandproducts.search.Index;
import slepenkov.gleb.shopandproducts.search.SearchIndex;

import java.io.*;
import java.util.List;
import java.util.Objects;

public abstract class AbstractShop<PT extends Product> implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractShop<?> that = (AbstractShop<?>) o;
        return Objects.equals(shopName, that.shopName) && Objects.equals(index, that.index) && Objects.equals(hr, that.hr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopName, index, hr);
    }

    private final String shopName;
    protected final Index<PT> index;
    protected final HRService hr = new MyHR(this);

    public AbstractShop(String shopName, int limit) {
        this.shopName = shopName;
        this.index = new SearchIndex<>(limit);
    }


    public Index<PT> getIndex() {
        return index;
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

    public abstract boolean buyProduct(Product product);

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

    public HRService getHr() {
        return hr;
    }

    public String getShopName() {
        return shopName;
    }
}
