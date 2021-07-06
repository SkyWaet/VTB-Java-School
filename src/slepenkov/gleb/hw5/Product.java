package slepenkov.gleb.hw5;

import java.util.Objects;

public class Product {
    private String name;
    private int price;
    protected int shelfLife = -1;
    protected String category;
    protected double weight;


    public Product(String name, int price, String category, double weight) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("\n Название: %s; Цена: %d; Категория: %s; Вес: %.3f; Срок годности: %d",
                name, price, category, weight, shelfLife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, shelfLife);
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public String getCategory() {
        return category;
    }

    public double getWeight() {
        return weight;
    }

}
