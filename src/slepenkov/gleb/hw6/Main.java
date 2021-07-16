package slepenkov.gleb.hw6;

import slepenkov.gleb.hw6.exceptions.ProductLimitReachedException;
import slepenkov.gleb.hw6.exceptions.ProductWithKeywordNotFound;
import slepenkov.gleb.hw6.impl.Supermarket;
import slepenkov.gleb.hw6.products.Book;
import slepenkov.gleb.hw6.products.Cake;
import slepenkov.gleb.hw6.products.Soap;

public class Main {
    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket(6);
        Book studentsBook = new Book("Англ яз", 20, 0.2);
        Book warAndPeace = new Book("Война и мир", 20, 0.8);
        Soap regularSoap = new Soap("Обычное мыло", 3, 0.1);
        Soap liquidSoap = new Soap("Жидкое мыло", 6, 0.5);
        Cake napoleonCake = new Cake("торт Наполеон", 50, 1.0, 5);
        Cake pragueCake = new Cake("торт Прага", 65, 1.5, 3);

        supermarket.addProducts(studentsBook);
        supermarket.addProducts(warAndPeace);
        supermarket.addProducts(regularSoap);
        supermarket.addProducts(liquidSoap);
        supermarket.addProducts(napoleonCake);
        supermarket.addProducts(pragueCake);

        System.out.println("Исходный список товаров: ");
        supermarket.printProducts();
        System.out.println();

        System.out.println("Пытаемся добавить еще один товар: ");
        try {
            Book javaBook = new Book("Учебник по Java", 20, 1);
            supermarket.addProducts(javaBook);
        } catch (ProductLimitReachedException ex) {
            ex.printStackTrace(System.out);
        }
        System.out.println();
        System.out.println("Удаляем мыло: ");
        supermarket.deleteByKey("мыло");
        supermarket.printProducts();
        System.out.println();

        System.out.println("Пытаемся еще раз удалить мыло: ");
        try {
            supermarket.deleteByKey("мыло");
        } catch (ProductWithKeywordNotFound ex) {
            ex.printStackTrace(System.out);
        }
    }
}
