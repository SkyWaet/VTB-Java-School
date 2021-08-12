package slepenkov.gleb.reflection;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
    private String name;
    private int code;


    public Department(String name, int code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return code == that.code && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
