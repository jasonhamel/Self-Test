package main.model.stock;

public class Stock {
    private double priceOfShare;

    public Stock(double priceOfShare) {
        if (priceOfShare < 0) {
            throw new IllegalArgumentException("Price of share cannot be less than $0");
        }
        this.priceOfShare = priceOfShare;
    }

    public Stock(Stock source) {
        this.priceOfShare = source.priceOfShare;
    }

    public double getPriceOfShare() {
        return priceOfShare;
    }

    public boolean setPrice(double newPrice) {
        if (newPrice < 0) {
            return false;
        }
        this.priceOfShare = newPrice;
        return true;
    }
}
