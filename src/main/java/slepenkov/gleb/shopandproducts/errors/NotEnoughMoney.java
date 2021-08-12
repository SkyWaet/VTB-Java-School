package slepenkov.gleb.shopandproducts.errors;

public class NotEnoughMoney implements Error {
    private String message = "На вашем счету недостаточно средств";

    public String getMessage() {
        return this.message;
    }

}
