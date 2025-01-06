package stock_trading_App_UsingDSA;

public class ShortSellingItem {
    private Stock stock;
    private int quantity;
    private double shortPrice;

    public ShortSellingItem(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
        this.shortPrice = stock.getPrice();
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getShortPrice() {
        return shortPrice;
    }

    public void increaseQuantity(int amount) {
        if (amount > 0) {
            double totalValueBefore = this.shortPrice * this.quantity;
            this.quantity += amount;
            double totalValueAfter = totalValueBefore + (stock.getPrice() * amount);
            this.shortPrice = totalValueAfter / this.quantity;
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

    public double getPotentialProfit() {
        return (shortPrice - stock.getPrice()) * quantity;
    }

    @Override
    public String toString() {
        return "ShortSellingItem{" +
               "stock=" + stock +
               ", quantity=" + quantity +
               ", shortPrice=" + shortPrice +
               '}';
    }
}

