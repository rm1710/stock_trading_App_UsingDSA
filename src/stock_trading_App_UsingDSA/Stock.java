package stock_trading_App_UsingDSA;

public class Stock {
    private String symbol;
    private double price;
    private int availableQuantity;

    public Stock(String symbol, double price, int availableQuantity) {
        this.symbol = symbol;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void decreaseQuantity(int amount) {
        if (amount > 0 && amount <= availableQuantity) {
            this.availableQuantity -= amount;
        } else {
            throw new IllegalArgumentException("Invalid quantity to decrease");
        }
    }

    public void increaseQuantity(int amount) {
        if (amount > 0) {
            this.availableQuantity += amount;
        } else {
            throw new IllegalArgumentException("Invalid quantity to increase");
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
               "symbol='" + symbol + '\'' +
               ", price=" + price +
               ", availableQuantity=" + availableQuantity +
               '}';
    }
}


