package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class TransactionPage extends Panel {
    private StockTradingApp app;
    private Panel transactionListPanel;

    public TransactionPage(StockTradingApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        // Title
        Label titleLabel = new Label("Transaction History", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Header row
        Panel headerPanel = new Panel(new GridLayout(1, 4, 10, 10));
        headerPanel.add(new Label("Stock Symbol", Label.CENTER));
        headerPanel.add(new Label("Type", Label.CENTER));
        headerPanel.add(new Label("Quantity", Label.CENTER));
        headerPanel.add(new Label("Price", Label.CENTER));
        add(headerPanel, BorderLayout.NORTH);

        // Transaction list panel within a scroll pane
        transactionListPanel = new Panel(new GridLayout(0, 4, 10, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(transactionListPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Back button
        Button backButton = new Button("Back to Stocks");
        backButton.addActionListener(e -> app.showStockPage());
        add(backButton, BorderLayout.SOUTH);
    }

    public void updateTransactions(List<Transaction> transactions) {
        transactionListPanel.removeAll();
        Map<String, List<Transaction>> sortedTransactions = new TreeMap<>();

        // Group transactions by stock symbol
        for (Transaction transaction : transactions) {
            sortedTransactions.computeIfAbsent(transaction.getStock().getSymbol(), k -> new ArrayList<>()).add(transaction);
        }

        // Add grouped transactions to the panel
        for (Map.Entry<String, List<Transaction>> entry : sortedTransactions.entrySet()) {
            for (Transaction transaction : entry.getValue()) {
                transactionListPanel.add(new Label(transaction.getStock().getSymbol(), Label.CENTER));
                transactionListPanel.add(new Label(transaction.getType(), Label.CENTER));
                transactionListPanel.add(new Label("Qty: " + transaction.getQuantity(), Label.CENTER));
                transactionListPanel.add(new Label("Price: ₹" + transaction.getPrice(), Label.CENTER));
            }
        }

        transactionListPanel.setPreferredSize(new Dimension(600, transactions.size() * 30));
        transactionListPanel.revalidate();
        transactionListPanel.repaint();
    }
}
