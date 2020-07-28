package net.avalith.city_pass.paypal;

public class PayPalApiException extends RuntimeException{
    public PayPalApiException(String localizedMessage) {
        super(localizedMessage);
    }
}
