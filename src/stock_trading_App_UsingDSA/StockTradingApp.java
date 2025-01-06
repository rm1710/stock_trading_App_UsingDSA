package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class StockTradingApp {
    private Frame mainFrame;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private StockPage stockPage;
    private PortfolioPage portfolioPage;
    private FundPage fundPage;
    private TransactionPage transactionPage;
    private ShortSellingPage shortSellingPage;

    private List<PortfolioItem> portfolio;
    private List<ShortSellingItem> shortSellingItems;
    private List<Transaction> transactions;
    private double funds;
    private String username;

    public StockTradingApp() {
        mainFrame = new Frame("Stock Trading App");
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new CardLayout());

        portfolio = new ArrayList<>();
        shortSellingItems = new ArrayList<>();
        transactions = new ArrayList<>();

        loginPage = new LoginPage(this);
        registerPage = new RegisterPage(this);
        stockPage = new StockPage(this);
        portfolioPage = new PortfolioPage(this);
        fundPage = new FundPage(this);
        transactionPage = new TransactionPage(this);
        shortSellingPage = new ShortSellingPage(this);

        mainFrame.add(loginPage, "login");
        mainFrame.add(registerPage, "register");
        mainFrame.add(stockPage, "stock");
        mainFrame.add(portfolioPage, "portfolio");
        mainFrame.add(fundPage, "fund");
        mainFrame.add(transactionPage, "transaction");
        mainFrame.add(shortSellingPage, "shortSelling");

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        funds = 0.0;
        username = "defaultUser"; // Replace with actual username logic

        showLoginPage();
    }

    public void showLoginPage() {
        CardLayout cardLayout = (CardLayout) mainFrame.getLayout();
        cardLayout.show(mainFrame, "login");
        mainFrame.setVisible(true);
    }

    public void showRegisterPage() {
        CardLayout cardLayout = (CardLayout) mainFrame.getLayout();
        cardLayout.show(mainFrame, "register");
    }

    public void showStockPage() {
        CardLayout cardLayout = (CardLayout) mainFrame.getLayout();
        cardLayout.show(mainFrame, "stock");
    }

    public void showPortfolioPage() {
        CardLayout cardLayout = (CardLayout) mainFrame.getLayout();
        cardLayout.show(mainFrame, "portfolio");
        portfolioPage.updatePortfolio(portfolio);
    }

    public void showFundPage() {
        CardLayout cardLayout = (CardLayout) mainFrame.getLayout();
        cardLayout.show(mainFrame, "fund");
    }

    public void showTransactionPage() {
        CardLayout cardLayout = (CardLayout) mainFrame.getLayout();
        cardLayout.show(mainFrame, "transaction");
        transactionPage.updateTransactions(transactions);
    }

    public void showShortSellingPage() {
        CardLayout cardLayout = (CardLayout) mainFrame.getLayout();
        cardLayout.show(mainFrame, "shortSelling");
        shortSellingPage.updateShortSelling(shortSellingItems);
    }

    public void addToPortfolio(Stock stock, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        PortfolioItem existingItem = findPortfolioItem(stock);
        if (existingItem != null) {
            existingItem.increaseQuantity(quantity);
        } else {
            portfolio.add(new PortfolioItem(stock, quantity));
        }
        transactions.add(new Transaction(stock, "Buy", quantity, stock.getPrice()));
        portfolioPage.updatePortfolio(portfolio);
    }

    public void addToShortSelling(Stock stock, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        ShortSellingItem existingItem = findShortSellingItem(stock);
        if (existingItem != null) {
            existingItem.increaseQuantity(quantity);
        } else {
            shortSellingItems.add(new ShortSellingItem(stock, quantity));
        }
        transactions.add(new Transaction(stock, "Sell", quantity, stock.getPrice()));
        shortSellingPage.updateShortSelling(shortSellingItems);
    }

    private PortfolioItem findPortfolioItem(Stock stock) {
        for (PortfolioItem item : portfolio) {
            if (item.getStock().getSymbol().equals(stock.getSymbol())) {
                return item;
            }
        }
        return null;
    }

    private ShortSellingItem findShortSellingItem(Stock stock) {
        for (ShortSellingItem item : shortSellingItems) {
            if (item.getStock().getSymbol().equals(stock.getSymbol())) {
                return item;
            }
        }
        return null;
    }

    public List<PortfolioItem> getPortfolio() {
        return portfolio;
    }

    public List<ShortSellingItem> getShortSellingItems() {
        return shortSellingItems;
    }

    public void addFunds(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        funds += amount;
    }

    public String getUsername() {
        return username;
    }

    public static void main(String[] args) {
        new StockTradingApp();
    }
}

