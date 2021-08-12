package slepenkov.gleb.reflection;

import java.util.Objects;

public class Worker extends Person {
    private int salary;

    public Worker() {
        super();
    }


    public Worker(int age, String name, Passport passport, int salary) {
        super(name, age, passport);
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return super.equals(o) && salary == worker.salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "salary=" + salary +
                '}' + super.toString();
    }
}
