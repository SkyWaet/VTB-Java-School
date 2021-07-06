package slepenkov.gleb.hw6;

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
}
