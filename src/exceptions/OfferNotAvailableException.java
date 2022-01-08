package exceptions;

public class OfferNotAvailableException extends RuntimeException{
    public OfferNotAvailableException(String message) {
        super(message);
    }
}
