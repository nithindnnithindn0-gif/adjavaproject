package ui;

import javax.swing.*;
import java.awt.event.*;
import dao.ComplaintDAO;

public class UserDashboard {

    public UserDashboard() {

        // Create Frame
        JFrame frame = new JFrame("User Dashboard");

        // Label
        JLabel label = new JLabel("Enter Complaint:");
        label.setBounds(50, 30, 200, 30);

        // Text Field
        JTextField textField = new JTextField();
        textField.setBounds(50, 60, 250, 30);

        // Submit Button
        JButton submitBtn = new JButton("Submit Complaint");
        submitBtn.setBounds(50, 110, 180, 30);

        // Logout Button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(240, 110, 100, 30);

        // Action: Submit Complaint
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String complaint = textField.getText().trim();

                // Validation
                if (complaint.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a complaint!");
                    return;
                }

                // Save to DB
                ComplaintDAO dao = new ComplaintDAO();
                dao.addComplaint(2, complaint);  // user_id = 2 (for now)

                JOptionPane.showMessageDialog(frame, "Complaint Submitted!");

                textField.setText(""); // clear field
            }
        });

        // Action: Logout
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginUI();  // go back to login
                frame.dispose();
            }
        });

        // Add components
        frame.add(label);
        frame.add(textField);
        frame.add(submitBtn);
        frame.add(logoutBtn);

        // Frame settings
        frame.setSize(400, 220);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}