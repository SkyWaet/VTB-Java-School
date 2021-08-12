package slepenkov.gleb.shopandproducts.products;

import java.util.Objects;

public class Cake extends Product implements LimitedShelfLifeProduct {
    private int shelfLife;

    public Cake(String name, int price, double weight, int shelfLife) {
        super(name, price, "торты", weight);
        this.shelfLife = shelfLife;
    }

    @Override
    public int getShelfLife() {
        return shelfLife;
    }

    @Override
    public String toString() {
        return String.format("%s Срок годности: %d;", super.toString(), shelfLife);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && shelfLife == ((LimitedShelfLifeProduct) obj).getShelfLife();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getCategory(), getWeight(), getShelfLife());
    }
}
