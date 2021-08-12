package slepenkov.gleb.reflection;

import java.time.LocalDate;
import java.util.Objects;

public class Passport {
    private int series;
    private int number;
    private LocalDate releaseDate;
    private Department department;

    public Passport() {
    }

    public Passport(int series, int number, LocalDate releaseDate, Department department) {
        this.series = series;
        this.number = number;
        this.releaseDate = releaseDate;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "series=" + series +
                ", number=" + number +
                ", releaseDate=" + releaseDate +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return series == passport.series && number == passport.number && Objects.equals(releaseDate, passport.releaseDate) && Objects.equals(department, passport.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, number, releaseDate, department);
    }
}
