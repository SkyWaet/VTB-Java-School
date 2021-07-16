package slepenkov.gleb.hw6.impl;

import slepenkov.gleb.hw6.products.Cake;
import slepenkov.gleb.hw6.shops.AbstractShop;
import slepenkov.gleb.hw6.shops.CakeShop;

public class SweetShop extends AbstractShop<Cake> implements CakeShop {
    public SweetShop(int limit) {
        super(limit);
    }

    @Override
    public Cake[] filterByPrice(int price) {
        return filterBy(elem -> elem.getPrice() < price, Cake[]::new);
    }

    @Override
    public Cake[] filterByPattern(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();
        return filterBy(elem -> elem.getName().toLowerCase().contains(lowerCasePattern)
                , Cake[]::new);
    }

    @Override
    public Cake[] filterByShelfLife(int limit) {
        return filterBy(elem -> elem.getShelfLife() >= limit, Cake[]::new);
    }

    @Override
    public Cake[] getAllCakes() {
        return listOfGoods.toArray(Cake[]::new);
    }
}
