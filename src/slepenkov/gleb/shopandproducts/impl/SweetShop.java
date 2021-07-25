package slepenkov.gleb.shopandproducts.impl;

import slepenkov.gleb.shopandproducts.products.Cake;
import slepenkov.gleb.shopandproducts.shops.AbstractShop;
import slepenkov.gleb.shopandproducts.shops.CakeShop;

import java.util.List;

public class SweetShop extends AbstractShop<Cake> implements CakeShop {
    public SweetShop(String shopName, int limit) {
        super(shopName, limit);
    }

    @Override
    public List<Cake> filterByPrice(int price) {
        return index.filterBy(elem -> elem.getPrice() < price);
    }


    @Override
    public List<Cake> filterByShelfLife(int limit) {
        return index.filterBy(elem -> elem.getShelfLife() >= limit);
    }

    @Override
    public List<Cake> getAllCakes() {
        return index.filterBy(elem -> elem instanceof Cake);
    }
}
