package stock_trading_App_UsingDSA;

import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends Panel {
    private StockTradingApp app;
    private TextField usernameField;
    private TextField emailField;
    private TextField passwordField;
    private TextField confirmPasswordField;
    private Button registerButton;
    private Button backToLoginButton;

    public RegisterPage(StockTradingApp app) {
        this.app = app;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        Label titleLabel = new Label("Register for Stock Trading App");
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

        Label emailLabel = new Label("Email:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        emailField = new TextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(emailField, gbc);

        Label passwordLabel = new Label("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);

        passwordField = new TextField(20);
        passwordField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(passwordField, gbc);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(confirmPasswordLabel, gbc);

        confirmPasswordField = new TextField(20);
        confirmPasswordField.setEchoChar('*');
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(confirmPasswordField, gbc);

        registerButton = new Button("Register");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(registerButton, gbc);

        backToLoginButton = new Button("Back to Login");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(backToLoginButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    // Show error message: All fields are required
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    // Show error message: Passwords do not match
                    return;
                }

                // TODO: Implement registration logic (e.g., save user data, validate email format, etc.)

                app.showLoginPage();
            }
        });

        backToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.showLoginPage();
            }
        });
    }
}


