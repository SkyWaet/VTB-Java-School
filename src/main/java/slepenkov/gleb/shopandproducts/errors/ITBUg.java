package slepenkov.gleb.shopandproducts.errors;

public class ITBUg implements Error {
    private String message = "Баг программы";

    public String getMessage() {
        return this.message;
    }

}
