
package dao;

import java.sql.*;
import db.DBConnection;

public class ComplaintDAO {

    public void addComplaint(int userId, String description) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO complaints (user_id, description, status) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, description);
            ps.setString(3, "Pending");

            ps.executeUpdate();

            System.out.println("Complaint Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}