package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;

public class LoginPage extends Panel {
    private StockTradingApp app;
    private TextField usernameField;
    private TextField passwordField;
    private Button loginButton;
    private Button registerButton;

    public LoginPage(StockTradingApp app) {
        this.app = app;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        Label titleLabel = new Label("Login to Stock Trading App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        Label usernameLabel = new Label("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        usernameField = new TextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);

        Label passwordLabel = new Label("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passwordLabel, gbc);

        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passwordField, gbc);

        loginButton = new Button("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        registerButton = new Button("Register");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(registerButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement login logic
                app.showStockPage();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showRegisterPage();
            }
        });
    }
}

