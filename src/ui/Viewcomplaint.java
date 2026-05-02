package ui;

import java.sql.*;
import db.DBConnection;

public class Viewcomplaint {
    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM complaints";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("--------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}