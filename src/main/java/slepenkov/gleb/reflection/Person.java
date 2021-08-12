package slepenkov.gleb.reflection;

import java.util.Objects;

public class Person {
    private String name;
    private Passport passport;
    private int age;

    public Person() {
    }

    public Person(String name, int age, Passport passport) {
        this.name = name;
        this.age = age;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equals(person.name) && passport.equals(person.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, passport);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", passport=" + passport +
                ", age=" + age +
                '}';
    }
}
