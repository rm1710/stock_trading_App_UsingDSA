package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class StockPage extends Panel {
    private StockTradingApp app;
    private List<Stock> stocks;
    private List<TextField> quantityFields;
    private Button portfolioButton;
    private Button fundButton;
    private Button transactionButton;
    private Button shortSellingButton;

    public StockPage(StockTradingApp app) {
        this.app = app;
        this.stocks = generateStocks();
        this.quantityFields = new ArrayList<>();

        setLayout(new BorderLayout());

        Panel stockListPanel = new Panel(new GridLayout(0, 6, 10, 10));
        for (Stock stock : stocks) {
            stockListPanel.add(new Label(stock.getSymbol()));
            stockListPanel.add(new Label("₹" + stock.getPrice()));
            TextField quantityField = new TextField(5);
            quantityFields.add(quantityField);
            stockListPanel.add(quantityField);
            stockListPanel.add(new Label("Qty: " + stock.getAvailableQuantity()));

            Button buyButton = new Button("Buy");
            Button sellButton = new Button("Sell");
            buyButton.setPreferredSize(new Dimension(80, 30));
            sellButton.setPreferredSize(new Dimension(80, 30));
            stockListPanel.add(buyButton);
            stockListPanel.add(sellButton);

            buyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String quantityText = quantityField.getText();
                    if (!quantityText.isEmpty()) {
                        try {
                            int quantity = Integer.parseInt(quantityText);
                            if (quantity > 0 && quantity <= stock.getAvailableQuantity()) {
                                app.addToPortfolio(stock, quantity);
                                stock.decreaseQuantity(quantity);
                                quantityField.setText("");
                                updateStockDisplay();
                                app.showTransactionPage(); // Show transaction page after buying
                                app.showPortfolioPage(); // Show portfolio page after buying
                            } else {
                                throw new IllegalArgumentException("Invalid quantity");
                            }
                        } catch (NumberFormatException ex) {
                            // Handle invalid number format
                            System.err.println("Invalid quantity format: " + quantityText);
                        }
                    }
                }
            });

            sellButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String quantityText = quantityField.getText();
                    if (!quantityText.isEmpty()) {
                        try {
                            int quantity = Integer.parseInt(quantityText);
                            if (quantity > 0) {
                                app.addToShortSelling(stock, quantity);
                                quantityField.setText("");
                            } else {
                                throw new IllegalArgumentException("Invalid quantity");
                            }
                        } catch (NumberFormatException ex) {
                            // Handle invalid number format
                            System.err.println("Invalid quantity format: " + quantityText);
                        }
                    }
                }
            });
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(stockListPanel);
        add(scrollPane, BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout());
        portfolioButton = new Button("Portfolio");
        fundButton = new Button("Funds");
        transactionButton = new Button("Transactions");
        shortSellingButton = new Button("Short Selling");

        buttonPanel.add(portfolioButton);
        buttonPanel.add(fundButton);
        buttonPanel.add(transactionButton);
        buttonPanel.add(shortSellingButton);

        add(buttonPanel, BorderLayout.SOUTH);

        portfolioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showPortfolioPage();
            }
        });

        fundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showFundPage();
            }
        });

        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showTransactionPage();
            }
        });

        shortSellingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showShortSellingPage();
            }
        });
    }

    private List<Stock> generateStocks() {
        List<Stock> stocks = new ArrayList<>();
        // Adding Indian stocks with prices in Indian Rupees
        stocks.add(new Stock("RELIANCE", 2500.50, 1000));
        stocks.add(new Stock("TCS", 3200.75, 800));
        stocks.add(new Stock("INFY", 1500.25, 1200));
        stocks.add(new Stock("HDFC", 2700.00, 900));
        stocks.add(new Stock("ICICI", 700.50, 1500));
        // Adding international stocks with prices in USD
        stocks.add(new Stock("AAPL", 150.00, 500));
        stocks.add(new Stock("GOOGL", 2800.00, 300));
        stocks.add(new Stock("AMZN", 3400.00, 200));
        stocks.add(new Stock("MSFT", 299.00, 400));
        stocks.add(new Stock("TSLA", 700.00, 600));
        // Adding more stocks
        for (int i = 1; i <= 90; i++) {
            stocks.add(new Stock("STOCK" + i, Math.random() * 1000, (int) (Math.random() * 1000)));
        }
        return stocks;
    }

    private void updateStockDisplay() {
        ScrollPane scrollPane = (ScrollPane) getComponent(0);
        Panel stockListPanel = (Panel) scrollPane.getComponent(0);
        Component[] components = stockListPanel.getComponents();

        for (int i = 0; i < components.length; i += 6) {
            if (components[i + 3] instanceof Label) {
                Label quantityLabel = (Label) components[i + 3];
                int stockIndex = i / 6;
                quantityLabel.setText("Qty: " + stocks.get(stockIndex).getAvailableQuantity());
            }
        }
        revalidate();
        repaint();
    }
}
