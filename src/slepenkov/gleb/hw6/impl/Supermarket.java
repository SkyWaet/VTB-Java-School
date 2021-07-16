package slepenkov.gleb.hw6.impl;


import slepenkov.gleb.hw6.products.*;
import slepenkov.gleb.hw6.shops.AbstractShop;
import slepenkov.gleb.hw6.shops.BookShop;
import slepenkov.gleb.hw6.shops.CakeShop;
import slepenkov.gleb.hw6.shops.SoapShop;

public class Supermarket extends AbstractShop<Product> implements CakeShop, SoapShop, BookShop {

    public Supermarket(int limit) {
        super(limit);
    }

    @Override
    public Book[] getAllBooks() {
        return filterBy(elem -> elem instanceof Book, Book[]::new);
    }

    @Override
    public Soap[] getAllSoap() {
        return filterBy(elem -> elem instanceof Soap, Soap[]::new);
    }

    @Override
    public Cake[] getAllCakes() {
        return filterBy(elem -> elem instanceof Cake, Cake[]::new);
    }

    @Override
    public Product[] filterByPrice(int price) {
        return filterBy(elem -> elem.getPrice() < price, Product[]::new);
    }

    @Override
    public Product[] filterByPattern(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();
        return filterBy(elem -> elem.getName().toLowerCase().contains(lowerCasePattern)
                , Product[]::new);
    }

    @Override
    public Product[] filterByShelfLife(int limit) {
        return filterBy(elem -> !(elem instanceof LimitedShelfLifeProduct) ||
                ((LimitedShelfLifeProduct) elem).getShelfLife() >= limit, Product[]::new);
    }
}
