package dao;
import java.sql.*;
import db.DBConnection;
public class UserDAO {

        public String login(String email, String password) {
            try {
                Connection con = DBConnection.getConnection();

                String query = "SELECT role FROM users WHERE email=? AND password=?";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, email);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getString("role"); // returns "admin" or "user"
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
