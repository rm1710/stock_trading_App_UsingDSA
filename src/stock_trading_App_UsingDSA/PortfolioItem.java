package stock_trading_App_UsingDSA;

public class PortfolioItem {
    private Stock stock;
    private int quantity;

    public PortfolioItem(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        } else {
            throw new IllegalArgumentException("Invalid quantity to increase");
        }
    }

    public void decreaseQuantity(int amount) {
        if (amount > 0 && amount <= quantity) {
            this.quantity -= amount;
        } else {
            throw new IllegalArgumentException("Invalid quantity to decrease");
        }
    }

    public double getTotalValue() {
        return stock.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "PortfolioItem{" +
                "stock=" + stock +
                ", quantity=" + quantity +
                '}';
    }
}
