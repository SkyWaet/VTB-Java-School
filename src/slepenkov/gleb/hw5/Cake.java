package slepenkov.gleb.hw5;

public class Cake extends Product {
    public Cake(String name, int price, double weight, int shelfLife) {
        super(name, price, "торты", weight);
        this.shelfLife = shelfLife;
    }
}
