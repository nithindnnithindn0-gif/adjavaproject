package ui;

import javax.swing.*;
import java.awt.event.*;
import dao.UserDAO;

 class LoginUI {

    public LoginUI() {

        JFrame frame = new JFrame("Login");

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 30);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 50, 150, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 100, 150, 30);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 150, 100, 30);

        loginBtn.addActionListener(e -> {

            String email = emailField.getText();
            String password = new String(passField.getPassword());

            UserDAO dao = new UserDAO();
            String role = dao.login(email, password);

            if (role == null) {
                JOptionPane.showMessageDialog(frame, "Invalid Login!");
            } else if (role.equals("admin")) {
                new AdminDashboard();
                frame.dispose();
            } else {
                new UserDashboard();
                frame.dispose();
            }
        });

        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginBtn);

        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}