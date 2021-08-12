package slepenkov.gleb.shopandproducts.hr;

import slepenkov.gleb.shopandproducts.hr.employees.Employee;
import slepenkov.gleb.shopandproducts.hr.employees.Manager;
import slepenkov.gleb.shopandproducts.hr.employees.Support;
import slepenkov.gleb.shopandproducts.hr.employees.impl.Administrator;
import slepenkov.gleb.shopandproducts.shops.AbstractShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyHR implements HRService {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyHR myHR = (MyHR) o;
        return Objects.equals(staff, myHR.staff) && Objects.equals(manager, myHR.manager) && Objects.equals(shop, myHR.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staff, manager, shop);
    }

    private final List<Employee> staff = new ArrayList<>();
    private Manager manager;
    private final AbstractShop shop;

    public MyHR(AbstractShop shop) {
        this.shop = shop;
    }

    public Manager getManager() {
        return manager;
    }

    public Employee getEmployee() {
        return staff.stream().findAny().get();
    }

    public Support getSupport() {
        return (Support) staff.stream().filter(e -> e instanceof Support).findAny().get();
    }

    @Override
    public void hire(Employee e) {
        staff.add(e);
        if (e instanceof Manager && Objects.isNull(manager)) {
            manager = (Manager) e;
        }
    }

    @Override
    public void fire(Employee e) {
        staff.remove(e);
        if (Objects.equals(manager, e)) {
            Employee buff = staff.stream().filter(empl -> empl instanceof Manager).findAny().orElse(null);
            if (Objects.isNull(buff)) {
                manager = null;
            } else {
                manager = (Manager) buff;
            }
        }
    }

    @Override
    public void promote(Employee e) {
        if (Objects.isNull(manager)) {
            manager = (Manager) e;
        }
    }
}
