package ui;

import javax.swing.*;
import java.sql.*;
import db.DBConnection;

 class AdminDashboard {

    public AdminDashboard() {

        JFrame frame = new JFrame("Admin Dashboard");

        // TEXT AREA (show complaints)
        JTextArea area = new JTextArea();
        area.setBounds(20, 20, 350, 180);

        // LOAD DATA
        loadComplaints(area);

        // INPUT FIELD (enter complaint ID)
        JLabel idLabel = new JLabel("Enter ID:");
        idLabel.setBounds(20, 210, 100, 30);

        JTextField idField = new JTextField();
        idField.setBounds(100, 210, 100, 30);

        // UPDATE BUTTON
        JButton updateBtn = new JButton("Mark Resolved");
        updateBtn.setBounds(220, 210, 150, 30);

        // LOGOUT BUTTON
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(140, 260, 100, 30);

        // UPDATE ACTION
        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                Connection con = DBConnection.getConnection();
                String query = "UPDATE complaints SET status='Resolved' WHERE id=?";

                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(frame, "Status Updated!");
                    area.setText(""); // clear
                    loadComplaints(area); // reload updated data
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid ID!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Enter valid number!");
            }
        });

        // LOGOUT ACTION
        logoutBtn.addActionListener(e -> {
            new LoginUI();
            frame.dispose();
        });

        // ADD COMPONENTS
        frame.add(area);
        frame.add(idLabel);
        frame.add(idField);
        frame.add(updateBtn);
        frame.add(logoutBtn);

        frame.setSize(420, 350);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // METHOD TO LOAD COMPLAINTS
    private void loadComplaints(JTextArea area) {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM complaints");

            while (rs.next()) {
                area.append(
                        "ID: " + rs.getInt("id") +
                                " | " + rs.getString("description") +
                                " | " + rs.getString("status") + "\n"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}