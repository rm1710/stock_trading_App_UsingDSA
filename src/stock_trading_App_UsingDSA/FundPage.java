package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;

public class FundPage extends Panel {
    private StockTradingApp app;
    private TextField fundAmountField;
    private Label userDetailLabel;

    public FundPage(StockTradingApp app) {
        this.app = app;
        setLayout(new BorderLayout());

        Label titleLabel = new Label("Funds Management", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        Panel fundPanel = new Panel(new FlowLayout());
        fundPanel.add(new Label("Enter amount to add:"));
        fundAmountField = new TextField(10);
        fundPanel.add(fundAmountField);
        Button addFundButton = new Button("Add Funds");
        fundPanel.add(addFundButton);
        add(fundPanel, BorderLayout.CENTER);

        userDetailLabel = new Label("", Label.CENTER);
        add(userDetailLabel, BorderLayout.SOUTH);

        addFundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountText = fundAmountField.getText();
                if (!amountText.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(amountText);
                        if (amount > 0) {
                            app.addFunds(amount);
                            fundAmountField.setText("");
                            // Show confirmation message with username and added amount
                            String username = app.getUsername();
                            userDetailLabel.setText("User: " + username + " | Funds added: ₹" + amount);
                        } else {
                            throw new IllegalArgumentException("Invalid amount");
                        }
                    } catch (NumberFormatException ex) {
                        // Handle invalid number format
                        System.err.println("Invalid amount format: " + amountText);
                    }
                }
            }
        });

        Button backButton = new Button("Back to Stocks");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showStockPage();
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }
}
