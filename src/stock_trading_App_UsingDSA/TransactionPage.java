package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;

public class TransactionPage extends Panel {
    private StockTradingApp app;
    private Panel transactionListPanel;

    public TransactionPage(StockTradingApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        Label titleLabel = new Label("Transaction History", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        Panel headerPanel = new Panel(new GridLayout(1, 4, 10, 10));
        headerPanel.add(new Label("Stock Symbol", Label.CENTER));
        headerPanel.add(new Label("Type", Label.CENTER));
        headerPanel.add(new Label("Quantity", Label.CENTER));
        headerPanel.add(new Label("Price", Label.CENTER));
        add(headerPanel, BorderLayout.NORTH);

        transactionListPanel = new Panel(new GridLayout(0, 4, 10, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(transactionListPanel);
        add(scrollPane, BorderLayout.CENTER);

        Button backButton = new Button("Back to Stocks");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showStockPage();
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void updateTransactions(List<stock_trading_App_UsingDSA.Transaction> transactions) {
        transactionListPanel.removeAll();
        Map<String, List<Transaction>> sortedTransactions = new TreeMap<>();
        for (stock_trading_App_UsingDSA.Transaction transaction : transactions) {
            sortedTransactions.computeIfAbsent(transaction.getStock().getSymbol(), k -> new ArrayList<>()).addAll((Collection<? extends Transaction>) transaction);
        }
        for (Map.Entry<String, List<Transaction>> entry : sortedTransactions.entrySet()) {
            for (Transaction transaction : entry.getValue()) {
                transactionListPanel.add(new Label(transaction.getStock().getSymbol()));
                transactionListPanel.add(new Label(transaction.getType()));
                transactionListPanel.add(new Label("Qty: " + transaction.getQuantity()));
                transactionListPanel.add(new Label("Price: ₹" + transaction.getPrice()));
            }
        }
        revalidate();
        repaint();
    }

    class Transaction {
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
}


