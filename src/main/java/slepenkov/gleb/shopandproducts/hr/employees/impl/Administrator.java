package slepenkov.gleb.shopandproducts.hr.employees.impl;

import slepenkov.gleb.shopandproducts.errors.Error;
import slepenkov.gleb.shopandproducts.errors.PurchaseRejection;
import slepenkov.gleb.shopandproducts.hr.employees.Employee;
import slepenkov.gleb.shopandproducts.hr.employees.Manager;
import slepenkov.gleb.shopandproducts.products.Product;
import slepenkov.gleb.shopandproducts.shops.AbstractShop;

public class Administrator extends Seller implements Manager {

    public Administrator(String name, String surname, AbstractShop placeOfWork) {
        super(name, surname, placeOfWork);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void work(String msg) {
        System.out.println("Командую");
    }

    @Override
    public boolean sell(Product product, Error er) {
        if (er instanceof PurchaseRejection || er.fix()) {
            return true;
        } else {
            return placeOfWork.getHr().getSupport().sell(product, er);
        }
    }


    @Override
    public void command(Employee e) {
        System.out.println("Работай!");
        e.work("Работаю");
    }
}
