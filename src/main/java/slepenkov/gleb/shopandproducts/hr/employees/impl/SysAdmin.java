package slepenkov.gleb.shopandproducts.hr.employees.impl;

import slepenkov.gleb.shopandproducts.errors.Error;
import slepenkov.gleb.shopandproducts.hr.employees.Support;
import slepenkov.gleb.shopandproducts.products.Product;

public class SysAdmin implements Support {
    @Override
    public void work(String msg) {
        System.out.println("Пью кофе");
        fix();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public boolean sell(Product product, Error er) {
        return er.fix();
    }


    @Override
    public void fix() {
        System.out.println("Чиню");
    }
}
