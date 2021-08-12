package slepenkov.gleb.shopandproducts.hr.employees.impl;

import slepenkov.gleb.shopandproducts.errors.Error;
import slepenkov.gleb.shopandproducts.errors.ErrorGenerator;
import slepenkov.gleb.shopandproducts.errors.NotEnoughMoney;
import slepenkov.gleb.shopandproducts.hr.employees.Employee;
import slepenkov.gleb.shopandproducts.products.Product;
import slepenkov.gleb.shopandproducts.shops.AbstractShop;

import java.util.Objects;

public class Seller implements Employee {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(name, seller.name) && Objects.equals(surname, seller.surname) && Objects.equals(placeOfWork, seller.placeOfWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, placeOfWork);
    }

    private final String name;
    private final String surname;
    protected final AbstractShop placeOfWork;


    public Seller(String name, String surname, AbstractShop placeOfWork) {
        this.name = name;
        this.surname = surname;
        this.placeOfWork = placeOfWork;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void work(String msg) {
        System.out.println(msg);
    }

    @Override
    public boolean sell(Product product, Error error) {
        Error er = ErrorGenerator.generateError();
        if (er instanceof NotEnoughMoney || er.fix()) {
            return true;
        } else {
            return placeOfWork.getHr().getManager().sell(product, er);
        }
    }

}
