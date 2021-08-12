package slepenkov.gleb.shopandproducts.shops;

import org.junit.jupiter.api.Test;
import slepenkov.gleb.shopandproducts.exceptions.ProductLimitReachedException;
import slepenkov.gleb.shopandproducts.exceptions.ProductWithKeywordNotFound;
import slepenkov.gleb.shopandproducts.impl.Supermarket;
import slepenkov.gleb.shopandproducts.products.Cake;
import slepenkov.gleb.shopandproducts.products.Product;
import slepenkov.gleb.shopandproducts.products.Soap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractShopTest {
    AbstractShop shop = new Supermarket("Supermarket", 10);

    @Test
    public void addProductShouldOk() {
        Product prod = new Soap("name", 1, 1.0);
        shop.addProducts(prod);
        assertEquals(shop.countGoods(), 1);
    }

    @Test
    public void addProductsShouldFail() {
        for (int i = 0; i <= 9; i++) {
            Product prod = new Soap("name" + i, 1, 1.0);
            shop.addProducts(prod);
        }
        assertThrows(ProductLimitReachedException.class, () -> {
            Product prod = new Soap("name" + 9, 1, 1.0);
            shop.addProducts(prod);
        });
    }

    @Test
    public void removeProductShouldOk() {
        Product prod = new Soap("name", 1, 1.0);
        shop.addProducts(prod);
        shop.deleteByKey("name");
        assertEquals(shop.countGoods(), 0);
    }

    @Test
    public void removeProductShouldFail() {
        Product prod = new Soap("name" + 8, 1, 1.0);
        assertThrows(ProductWithKeywordNotFound.class, () -> shop.deleteByKey(prod.getName()));
    }

    @Test
    public void filterByShelfLifeShouldOk() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                Cake cake = new Cake("cake" + i, 10, 1.0, 1);
                shop.addProducts(cake);
            } else {
                Product prod = new Soap("name" + i, 1, 1.0);
                shop.addProducts(prod);
            }
        }
        assertEquals(5, shop.filterByShelfLife(5).size());

    }

}