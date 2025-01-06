package stock_trading_App_UsingDSA;

public class Transaction {
    private Stock stock;
    private String type;
    private int quantity;
    private double price;

    public Transaction(Stock stock, String type, int quantity, double price) {
        this.stock = stock;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }


    public Stock getStock() {
        return stock;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "stock=" + stock +
               ", type='" + type + '\'' +
               ", quantity=" + quantity +
               ", price=" + price +
               '}';
    }
}
