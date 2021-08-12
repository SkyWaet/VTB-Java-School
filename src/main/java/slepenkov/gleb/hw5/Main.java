package slepenkov.gleb.hw5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shop supermarket = new Shop();
        Book studentsBook = new Book("Англ яз", 20, 0.2);
        Book warAndPeace = new Book("Война и мир", 20, 0.8);
        Soap regularSoap = new Soap("Обычное мыло", 3, 0.1);
        Soap liquidSoap = new Soap("Жидкое мыло", 6, 0.5);
        Cake napoleonCake = new Cake("Наполеон", 50, 1.0, 5);
        Cake pragueCake = new Cake("Прага", 65, 1.5, 3);

        System.out.println("Вес товаров: ");
        System.out.println(studentsBook.getName() + ": " + studentsBook.getWeight());
        System.out.println(regularSoap.getName() + ": " + regularSoap.getWeight());
        System.out.println(napoleonCake.getName() + ": " + napoleonCake.getWeight());
        System.out.println();

        System.out.println("Срок годности товаров: ");
        System.out.println(studentsBook.getName() + ": " + regularSoap.getShelfLife());
        System.out.println(regularSoap.getName() + ": " + studentsBook.getShelfLife());
        System.out.println(napoleonCake.getName() + ": " + napoleonCake.getShelfLife());
        System.out.println();

        System.out.println("Категории товаров: ");
        System.out.println(studentsBook.getName() + ": " + studentsBook.getCategory());
        System.out.println(regularSoap.getName() + ": " + regularSoap.getCategory());
        System.out.println(napoleonCake.getName() + ": " + napoleonCake.getCategory());
        System.out.println();

        supermarket.addProducts(studentsBook);
        supermarket.addProducts(warAndPeace);
        supermarket.addProducts(regularSoap);
        supermarket.addProducts(liquidSoap);
        supermarket.addProducts(napoleonCake);
        supermarket.addProducts(pragueCake);

        System.out.println("Дешевые товары: " + Arrays.toString(supermarket.filterByPrice(15)));
        System.out.println("Содержат мыло: " + Arrays.toString(supermarket.filterByPattern("Мыло")));
        System.out.println("Товары с хорошим сроком годности: "
                + Arrays.toString(supermarket.filterByShelfLife(5)));
        System.out.println("Все книги: " + Arrays.toString(supermarket.getAllBooks()));
        System.out.println("Все торты: " + Arrays.toString(supermarket.getAllCakes()));
        System.out.println("Всё мыло: " + Arrays.toString(supermarket.getAllSoap()));

    }
}
