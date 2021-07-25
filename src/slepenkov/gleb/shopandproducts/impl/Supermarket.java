package slepenkov.gleb.shopandproducts.impl;


import slepenkov.gleb.shopandproducts.products.*;
import slepenkov.gleb.shopandproducts.shops.AbstractShop;
import slepenkov.gleb.shopandproducts.shops.BookShop;
import slepenkov.gleb.shopandproducts.shops.CakeShop;
import slepenkov.gleb.shopandproducts.shops.SoapShop;

import java.util.List;

public class Supermarket extends AbstractShop<Product> implements CakeShop, SoapShop, BookShop {

    public Supermarket(String shopName, int limit) {
        super(shopName, limit);
    }

    @Override
    public List<Book> getAllBooks() {
        return index.filterBy(elem -> elem instanceof Book);
    }

    @Override
    public List<Soap> getAllSoap() {
        return index.filterBy(elem -> elem instanceof Soap);
    }

    @Override
    public List<Cake> getAllCakes() {
        return index.filterBy(elem -> elem instanceof Cake);
    }

    @Override
    public List<Product> filterByPrice(int price) {
        return index.filterBy(elem -> elem.getPrice() < price);
    }

    @Override
    public List<Product> filterByShelfLife(int limit) {
        return index.filterBy(elem -> !(elem instanceof LimitedShelfLifeProduct) ||
                ((LimitedShelfLifeProduct) elem).getShelfLife() >= limit);
    }
}
