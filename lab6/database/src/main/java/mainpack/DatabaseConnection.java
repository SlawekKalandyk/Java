package mainpack;

import java.sql.*;

public class DatabaseConnection {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/skalandy",
                    "skalandy", "MynnMfGjzaF2WNnV");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectAllFromTable() {
        try {
            connect();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                String isbn = rs.getString(1);
                String title = rs.getString(2);
                String authorName = rs.getString(3).split(" ")[0];
                String authorSurname = rs.getString(3).split(" ")[1];
                String year = rs.getString(4);

                System.out.println(isbn + " | "
                        + title + " | "
                        + authorName + " " + authorSurname + " | "
                        + year);
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {}
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {}

                stmt = null;
            }
        }
    }

    public void selectAllFromTableByAuthorSurname() {
        try {
            connect();
            String query = "SELECT * FROM books WHERE "
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String isbn = rs.getString(1);
                String title = rs.getString(2);
                String authorName = rs.getString(3).split(" ")[0];
                String authorSurname = rs.getString(3).split(" ")[1];
                String year = rs.getString(4);

                System.out.println(isbn + " | "
                        + title + " | "
                        + authorName + " " + authorSurname + " | "
                        + year);
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {}
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {}

                stmt = null;
            }
        }
    }
}
