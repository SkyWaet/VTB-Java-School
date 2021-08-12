package slepenkov.gleb.shopandproducts.errors;

public class PurchaseRejection implements Error{
    private String message = "Отмена покупки";

    public String getMessage() {
        return this.message;
    }
}
