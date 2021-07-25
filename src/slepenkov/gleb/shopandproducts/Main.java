package slepenkov.gleb.shopandproducts;

import slepenkov.gleb.shopandproducts.impl.Supermarket;
import slepenkov.gleb.shopandproducts.products.Book;
import slepenkov.gleb.shopandproducts.products.Cake;
import slepenkov.gleb.shopandproducts.products.Product;
import slepenkov.gleb.shopandproducts.products.Soap;
import slepenkov.gleb.shopandproducts.shops.AbstractShop;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket("Супермаркет", 100);
        Book studentsBook = new Book("Английский яз", 20, 0.2);
        Book warAndPeace = new Book("Война и мир", 20, 0.8);
        Soap regularSoap = new Soap("Обычное мыло", 3, 0.1);
        Soap liquidSoap = new Soap("Жидкое мыло c ароматом яблока", 6, 0.5);
        Cake napoleonCake = new Cake("торт Наполеон", 50, 1.0, 5);
        Cake pragueCake = new Cake("торт Прага", 65, 1.5, 3);
        Cake engCake = new Cake("торт Английский", 65, 1.5, 3);
        Cake appleCake = new Cake("яблочный пирог", 65, 1.5, 2);
        Book yablo = new Book("ябло", 65, 1.5);

        supermarket.addProducts(studentsBook);
        supermarket.addProducts(warAndPeace);
        supermarket.addProducts(regularSoap);
        supermarket.addProducts(liquidSoap);
        supermarket.addProducts(engCake);
        supermarket.addProducts(napoleonCake);
        supermarket.addProducts(pragueCake);
        supermarket.addProducts(appleCake);
        supermarket.addProducts(yablo);

        System.out.println("Исходный список товаров: ");
        supermarket.printProducts();
        System.out.println();

        System.out.println("Найдем все товары, содержащие в навзвании слово \"ябло\"");
        System.out.println(supermarket.filterByPattern("*"));

    }
}
