package pl.mg.tradingservice;


public class TickerNotFoundException extends RuntimeException {

    public TickerNotFoundException(String message) {
        super(message);
    }
}
