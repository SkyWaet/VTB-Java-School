package slepenkov.gleb.hw6;

public interface SoapShop extends IShop {
    default Soap[] getAllSoap() {
        return filterBy(elem -> elem instanceof Soap).toArray(Soap[]::new);
    }
}
