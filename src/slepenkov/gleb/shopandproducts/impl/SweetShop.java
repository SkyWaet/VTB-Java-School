package slepenkov.gleb.shopandproducts.impl;

import slepenkov.gleb.shopandproducts.products.Cake;
import slepenkov.gleb.shopandproducts.shops.AbstractShop;
import slepenkov.gleb.shopandproducts.shops.CakeShop;

import java.util.ArrayList;
import java.util.List;

public class SweetShop extends AbstractShop<Cake> implements CakeShop {
    public SweetShop(int limit, String shopName) {
        super(shopName, limit);
    }

    @Override
    public List<Cake> filterByPrice(int price) {
        return filterBy(elem -> elem.getPrice() < price);
    }


    @Override
    public List<Cake> filterByShelfLife(int limit) {
        return filterBy(elem -> elem.getShelfLife() >= limit);
    }

    @Override
    public List<Cake> getAllCakes() {
        List<Cake> cakes = new ArrayList<>();
        cakes.addAll(listOfGoods);
        return cakes;
    }
}
