package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PortfolioPage extends Panel {
    private StockTradingApp app;
    private Panel portfolioListPanel;

    public PortfolioPage(StockTradingApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        Label titleLabel = new Label("Portfolio", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        portfolioListPanel = new Panel(new GridLayout(0, 3, 10, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(portfolioListPanel);
        add(scrollPane, BorderLayout.CENTER);

        Button backButton = new Button("Back to Stocks");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showStockPage();
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void updatePortfolio(List<stock_trading_App_UsingDSA.PortfolioItem> portfolio) {
        portfolioListPanel.removeAll();
        for (stock_trading_App_UsingDSA.PortfolioItem item : portfolio) {
            portfolioListPanel.add(new Label(item.getStock().getSymbol()));
            portfolioListPanel.add(new Label("$" + item.getStock().getPrice()));
            portfolioListPanel.add(new Label("Qty: " + item.getQuantity()));
        }
        revalidate();
        repaint();
    }

    class PortfolioItem {
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
}



