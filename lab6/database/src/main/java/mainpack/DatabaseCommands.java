package mainpack;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseCommands {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public DatabaseCommands() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            for (int i = 0; i < 3; ++i) {
                if (conn == null)
                    conn = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/skalandy",
                            "skalandy", "MynnMfGjzaF2WNnV");
                else
                    break;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> selectAllFromTable() {
        ArrayList<Book> books = new ArrayList<>();

        try {
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                String isbn = rs.getString(1);
                String title = rs.getString(2);
                String authorFullName = rs.getString(3);
                String year = rs.getString(4);

                books.add(new Book(isbn, title, authorFullName, Integer.parseInt(year)));
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }

        return books;
    }

    public ArrayList<Book> selectFromTableByAuthorSurname(String surname) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            String query = "SELECT * FROM books WHERE (author REGEXP '^.* .*" + surname + ".*')";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String isbn = rs.getString(1);
                String title = rs.getString(2);
                String authorFullName = rs.getString(3);
                String year = rs.getString(4);

                books.add(new Book(isbn, title, authorFullName, Integer.parseInt(year)));
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }

        return books;
    }

    public ArrayList<Book> selectFromTableByISBN(String isbnNum) {
        ArrayList<Book> books = new ArrayList<>();

        try {
            String query = "SELECT * FROM books WHERE (isbn REGEXP '^.*" + isbnNum + ".*')";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String isbn = rs.getString(1);
                String title = rs.getString(2);
                String authorFullName = rs.getString(3);
                String year = rs.getString(4);

                books.add(new Book(isbn, title, authorFullName, Integer.parseInt(year)));
            }
        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }

                stmt = null;
            }
        }

        return books;
    }

    public void addBook(Book book) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO books values('"
                            + book.getIsbn() + "','"
                            + book.getBookName() + "','"
                            + book.getAuthor() + "','"
                            + book.getYear() + "')");
        } catch (SQLException sqlEx) {
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
            }

            stmt = null;
        }
    }
}
