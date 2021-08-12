package slepenkov.gleb.shopandproducts.search;

import slepenkov.gleb.shopandproducts.products.Product;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Storage<PT extends Product> extends Serializable {
    String printList();

    int size();

    void add(PT product);

    void delete(String key);

    List<PT> find(String key);
}
