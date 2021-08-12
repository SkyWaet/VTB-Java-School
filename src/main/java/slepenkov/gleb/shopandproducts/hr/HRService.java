package slepenkov.gleb.shopandproducts.hr;

import slepenkov.gleb.shopandproducts.hr.employees.Employee;
import slepenkov.gleb.shopandproducts.hr.employees.Manager;
import slepenkov.gleb.shopandproducts.hr.employees.Support;

import java.io.Serializable;

public interface HRService extends Serializable {
    void hire(Employee e);

    void fire(Employee e);

    void promote(Employee e);

    Manager getManager();

    Employee getEmployee();

    Support getSupport();

}
