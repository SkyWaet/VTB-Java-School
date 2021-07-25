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
        return filterBy(elem -> elem instanceof Book);
    }

    @Override
    public List<Soap> getAllSoap() {
        return filterBy(elem -> elem instanceof Soap);
    }

    @Override
    public List<Cake> getAllCakes() {
        return filterBy(elem -> elem instanceof Cake);
    }

    @Override
    public List<Product> filterByPrice(int price) {
        return filterBy(elem -> elem.getPrice() < price);
    }

    @Override
    public List<Product> filterByPattern(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();
        return filterBy(elem -> elem.getName().toLowerCase().contains(lowerCasePattern));
    }

    @Override
    public List<Product> filterByShelfLife(int limit) {
        return filterBy(elem -> !(elem instanceof LimitedShelfLifeProduct) ||
                ((LimitedShelfLifeProduct) elem).getShelfLife() >= limit);
    }
}
