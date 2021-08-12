package slepenkov.gleb.shopandproducts.hr.employees;

import slepenkov.gleb.shopandproducts.errors.Error;
import slepenkov.gleb.shopandproducts.products.Product;

public interface Employee {
    void work(String msg);

    boolean sell(Product product, Error error);
}
