package ui;

import dao.ComplaintDAO;

public class Insertcomplaint {
    public static void main(String[] args) {
        ComplaintDAO dao = new ComplaintDAO();
        dao.addComplaint(2, "Test complaint from Java");
    }
}