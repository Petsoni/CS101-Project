package exceptions;

public class PriceToLowException extends RuntimeException{
    public PriceToLowException(String message) {
        super(message);
    }
}
