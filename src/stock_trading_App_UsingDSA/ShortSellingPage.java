package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ShortSellingPage extends Panel {
    private StockTradingApp app;
    private Panel shortSellingListPanel;

    public ShortSellingPage(StockTradingApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        Label titleLabel = new Label("Short Selling", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        shortSellingListPanel = new Panel(new GridLayout(0, 3, 10, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(shortSellingListPanel);
        add(scrollPane, BorderLayout.CENTER);

        Button backButton = new Button("Back to Stocks");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showStockPage();
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void updateShortSelling(List<stock_trading_App_UsingDSA.ShortSellingItem> shortSellingItems) {
        shortSellingListPanel.removeAll();
        for (stock_trading_App_UsingDSA.ShortSellingItem item : shortSellingItems) {
            shortSellingListPanel.add(new Label(item.getStock().getSymbol()));
            shortSellingListPanel.add(new Label("$" + item.getStock().getPrice()));
            shortSellingListPanel.add(new Label("Qty: " + item.getQuantity()));
        }
        revalidate();
        repaint();
    }

    class ShortSellingItem {
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
}




